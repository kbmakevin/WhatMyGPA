package com.whatmygpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import com.whatmygpa.models.Course;

public class CoursesService {
	// PERSISTENCE_UNIT_NAME is the name recorded in the persistence.xml file
	private static final String PERSISTENCE_UNIT_NAME = "WhatMyGPA_JPA";
	private static EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
			.createEntityManager();
	private static EntityTransaction et = em.getTransaction();

	public static int getNumberCourses() {
		return getAllCourses().size();
	}

	public static List<Course> getAllCourses() {
		TypedQuery<Course> query = em.createNamedQuery("Course.findAll", Course.class);
		return getQueryResults(query);
	}

	public static Course getCourse(String courseCode) {
		TypedQuery<Course> query = em.createNamedQuery("Course.findOne", Course.class);
		query.setParameter("code", courseCode);
		List<Course> result = getQueryResults(query);
		if (result == null) {
			return null;
		}
		return result.get(0);
	}

	public static void updateCourse(Course c, int credits) {

		try {
			et.begin();
			Course updatedCourse = getCourse(c.getCode());
			updatedCourse.setCredits(credits);
			em.persist(updatedCourse);
			// automatic rollback on SQL Exception
			et.commit();
		} catch (RollbackException re) {
			re.printStackTrace(System.err);
		}

	}

	public static void addCourse(String courseCode, int credits) {
		Course newCourse = new Course();
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
		TypedQuery<Course> query = em.createNamedQuery("Courses.removeOne", Course.class);
		et.begin();
		int deletedCount = query.setParameter("code", courseCode).executeUpdate();
		em.flush();
		et.commit();
		return deletedCount > 0;
	}

	// Helper functions
	private static List<Course> getQueryResults(TypedQuery<Course> query) {
		List<Course> courseList = query.getResultList();
		if (courseList != null && courseList.size() > 0) {
			return courseList;
		} else {
			return null;
		}
	}
}
