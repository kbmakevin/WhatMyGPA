package com.whatmygpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import com.whatmygpa.models.Users;

public class UsersService {
	// PERSISTENCE_UNIT_NAME is the name recorded in the persistence.xml file
	private static final String PERSISTENCE_UNIT_NAME = "WhatMyGPA_JPA";
	private static EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
			.createEntityManager();
	private static EntityTransaction et = em.getTransaction();

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

}
