package com.whatmygpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.whatmygpa.models.Courses;

public class CoursesService {
	// PERSISTENCE_UNIT_NAME is the name recorded in the persistence.xml file
	private static final String PERSISTENCE_UNIT_NAME = "WhatMyGPA_JPA";
	private static EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
			.createEntityManager();
	private static EntityTransaction et = em.getTransaction();

	public static List<Courses> getAllCourses() {
		TypedQuery<Courses> query = em.createNamedQuery("Courses.findAll", Courses.class);
		return getQueryResults(query);
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
