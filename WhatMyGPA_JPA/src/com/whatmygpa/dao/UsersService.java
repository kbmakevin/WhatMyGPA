package com.whatmygpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.whatmygpa.models.Users;

public class UsersService {
	// PERSISTENCE_UNIT_NAME is the name recorded in the persistence.xml file
		private static final String PERSISTENCE_UNIT_NAME = "WhatMyGPA_JPA";
		private static EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
				.createEntityManager();
		private static EntityTransaction et = em.getTransaction();
		
		public static Users authenticateUsers(String username, String password) {
			try {
				return em.createNamedQuery("Users.authenticate", Users.class)
				.setParameter("username", username)
				.setParameter("password", password).getSingleResult();			
			} 
			catch(Exception e) {
				return null;
			}
			
		}
		
		public static void registerUsers(Users user) {
			EntityTransaction et = em.getTransaction();
			try {
				et.begin();
				em.persist(user);
				em.flush();
				et.commit();
			}
			catch(Exception e)
			{
				
			}
			
		}

}