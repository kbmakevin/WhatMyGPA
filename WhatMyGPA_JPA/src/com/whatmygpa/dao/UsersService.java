package com.whatmygpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.whatmygpa.models.Users;

public class UsersService {
	// PERSISTENCE_UNIT_NAME is the name recorded in the persistence.xml file
	private static final String PERSISTENCE_UNIT_NAME = "WhatMyGPA_JPA";
	private static EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
			.createEntityManager();
	private static EntityTransaction et = em.getTransaction();

	public static int getNumberUsers() {
		TypedQuery<Users> query = em.createNamedQuery("Users.findAll", Users.class);
		return query.getResultList().size();
	}

	public static Users authenticateUsers(String username, String password) {
		try {
			return em.createNamedQuery("Users.authenticate", Users.class).setParameter("username", username)
					.setParameter("password", password).getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	public static void registerUsers(String name, String username, String password) {
		Users user = new Users();
		user.setName(name);
		user.setUsername(username);
		user.setPassword(password);
		user.setType("user");

		et.begin();
		try {
			em.persist(user);
			em.flush();
			et.commit();
		} catch (Exception e) {
			System.out.println("catching in usersservice");
			et.rollback();
			throw new PersistenceException();
		}
	}

	public static void updateOverallGPA(Users user) {
		// get sum of credits
		// double creditsSum;

		// get sum of earnedGPA (scale * credits)

		// calculate overall gpa = sum of earnedGPA/sum of credits

		// persist user

	}

}
