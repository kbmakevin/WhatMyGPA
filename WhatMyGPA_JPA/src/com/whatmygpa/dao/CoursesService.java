package com.whatmygpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import com.whatmygpa.models.Courses;

public class CoursesService {
	// PERSISTENCE_UNIT_NAME is the name recorded in the persistence.xml file
	private static final String PERSISTENCE_UNIT_NAME = "WhatMyGPA_JPA";
	private static EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
			.createEntityManager();
	private static EntityTransaction et = em.getTransaction();

	public static int getNumberCourses() {
		return getAllCourses().size();
	}

	public static List<Courses> getAllCourses() {
		TypedQuery<Courses> query = em.createNamedQuery("Courses.findAll", Courses.class);
		return getQueryResults(query);
	}

	public static Courses getCourse(String courseCode) {
		TypedQuery<Courses> query = em.createNamedQuery("Courses.findOne", Courses.class);
		query.setParameter("code", courseCode);
		List<Courses> result = getQueryResults(query);
		if (result == null) {
			return null;
		}
		return result.get(0);
	}

	public static void updateCourse(Courses c, int credits) {

		try {
			et.begin();
			Courses updatedCourse = getCourse(c.getCode());
			updatedCourse.setCredits(credits);
			em.persist(updatedCourse);
			// automatic rollback on SQL Exception
			et.commit();
		} catch (RollbackException re) {
			re.printStackTrace(System.err);
		}

	}

	public static void addCourse(String courseCode, int credits) {
		Courses newCourse = new Courses();
		newCourse.setCode(courseCode);
		newCourse.setCredits(credits);
		et.begin();
		try {
			em.persist(newCourse);
			em.flush();
			et.commit();
		} catch (Exception e) {
			et.rollback();
		}
	}

	public static boolean removeOneCourse(String courseCode) {
		TypedQuery<Courses> query = em.createNamedQuery("Courses.removeOne", Courses.class);
		et.begin();
		int deletedCount = query.setParameter("code", courseCode).executeUpdate();
		em.flush();
		et.commit();
		return deletedCount > 0;
	}

	// Helper functions
	private static List<Courses> getQueryResults(TypedQuery<Courses> query) {
		List<Courses> courseList = query.getResultList();
		if (courseList != null && courseList.size() > 0) {
			return courseList;
		} else {
			return null;
		}
	}
}
