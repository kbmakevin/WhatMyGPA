package com.whatmygpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.whatmygpa.models.CourseEnrollment;
import com.whatmygpa.models.CourseEnrollmentPK;
import com.whatmygpa.models.Courses;
import com.whatmygpa.models.Users;

public class CourseEnrollmentServices {
	// PERSISTENCE_UNIT_NAME is the name recorded in the persistence.xml file
	private static final String PERSISTENCE_UNIT_NAME = "WhatMyGPA_JPA";
	private static EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
			.createEntityManager();
	private static EntityTransaction et = em.getTransaction();

	public static List<CourseEnrollment> getAllCourseEnrollmentsWithUser(Users user) {
		TypedQuery<CourseEnrollment> query = em.createNamedQuery("CourseEnrollment.findAllForUser",
				CourseEnrollment.class);
		query.setParameter("user", user);

		return query.getResultList();
	}

	public static CourseEnrollment getOneCourseEnrollment(Users user, Courses course) {
		TypedQuery<CourseEnrollment> query = em.createNamedQuery("CourseEnrollment.findSpecific",
				CourseEnrollment.class);
		query.setParameter("user", user);
		query.setParameter("course", course);
		return query.getSingleResult();
	}

	private static double calculateEarnedGrade(int credits, int grade) {
		return getScale(grade) * credits;
	}

	public static double calculateOverallGPA(Users user) {
		// public static void calculateOverallGPA(Users user) {

		TypedQuery<CourseEnrollment> query = em.createNamedQuery("CourseEnrollment.findByUser", CourseEnrollment.class);
		query.setParameter("user", user);

		List<CourseEnrollment> ce = query.getResultList();

		// sum all earnedGpa for course enrollments with this user.id
		double sumEarnedGpa = 0;

		// sum all credits for course enrollments.course with this user.id
		int sumCredits = 0;

		for (CourseEnrollment courseEnrollment : ce) {
			sumEarnedGpa += courseEnrollment.getEarnedGPA();
			sumCredits += courseEnrollment.getCourse().getCredits();
		}

		System.out.println("sumearnedgpa " + sumEarnedGpa);
		System.out.println("sumCredits " + sumCredits);

		// divide 1/2 = overallgpa
		double overallGpa = sumEarnedGpa / sumCredits;

		System.out.println("overallGpa " + overallGpa);

		return overallGpa;

		// try {
		// et.begin();
		// Users updatedUser = user;
		// updatedUser.setGpa(overallGpa);
		// // updatedCourse.setCredits(credits);
		// em.persist(updatedUser);
		// // automatic rollback on SQL Exception
		// et.commit();
		// } catch (RollbackException re) {
		// re.printStackTrace(System.err);
		// }

		// et.begin();
		// try {
		//
		// // update user overall gpa
		// // user.setGpa(calculateOverallGPA(user));
		//
		// Query query2 = em.createQuery("UPDATE users u SET gpa = " + overallGpa +
		// "WHERE u.id = :id");
		// query2.setParameter("id", user.getId());
		// query2.executeUpdate();
		//
		// // persist course enrollment
		// // em.persist(user);
		//
		// // update user overall gpa - this will become virtual field, not stored in
		// user
		// // table
		// // em.flush();
		// et.commit();
		// } catch (Exception e) {
		// et.rollback();
		// }
	}

	public static void addCourseEnrollment(Users user, Courses course, int gradeReceived) {

		et.begin();
		try {
			// create new course enrollment
			CourseEnrollment enrollment = new CourseEnrollment();

			// user . add course enrollment (enrollment)
			user.addCourseEnrollment(enrollment);

			// course . add course enrollment (enrollment)
			course.addCourseEnrollment(enrollment);

			// calculate earnedGPA, set for enrollment
			// scale * credits = earned GPA for this course
			// e.g. A+ for 4.0 credits = 18.0 earned gpa
			// overall GPA = sum of earnedGPA/sum of credits
			// enrollment.setEarnedGPA(getScale(gradeReceived) * course.getCredits());
			enrollment.setGradeReceived(gradeReceived);
			CourseEnrollmentPK pk = new CourseEnrollmentPK();
			pk.setCourseId(course.getCode());
			pk.setUserId(user.getId());
			enrollment.setId(pk);
			enrollment.setEarnedGPA(calculateEarnedGrade(course.getCredits(), gradeReceived));

			em.persist(enrollment);

			// update user overall gpa
			// user.setGpa(calculateOverallGPA(user));

			// Query query = em.createQuery("UPDATE users u SET gpa = " +
			// calculateOverallGPA(user) + "WHERE u.id = :id");
			// query.setParameter("id", user.getId());
			// query.executeUpdate();

			// persist course enrollment
			// em.persist(user);

			// update user overall gpa - this will become virtual field, not stored in user
			// table
			// em.flush();
			et.commit();
		} catch (Exception e) {
			et.rollback();
		}
	}

	public static boolean removeCourseEnrollment(Courses course, Users user) {
		TypedQuery<CourseEnrollment> query = em.createNamedQuery("CourseEnrollment.removeOne", CourseEnrollment.class);
		et.begin();

		CourseEnrollment ce = getOneCourseEnrollment(user, course);
		user.removeCourseEnrollment(ce);
		course.removeCourseEnrollment(ce);

		int deletedCount = query.setParameter("user", user).setParameter("course", course).executeUpdate();
		em.flush();
		et.commit();
		return deletedCount > 0;
	}

	public static void updateCourseEnrollment(CourseEnrollment ce, int gradeReceived) {
		et.begin();

		ce.setGradeReceived(gradeReceived);
		ce.setEarnedGPA(calculateEarnedGrade(ce.getCourse().getCredits(), gradeReceived));
		em.persist(ce);

		et.commit();
	}

	private static double getScale(int percentage) {
		// A+ 90-100
		if (percentage >= 90)
			return 4.5;
		// A 80-89
		if (percentage >= 80)
			return 4;
		// B+ 75-79
		if (percentage >= 75)
			return 3.5;
		// B 70-74
		if (percentage >= 70)
			return 3;
		// C+ 65-69
		if (percentage >= 65)
			return 2.5;
		// C 60-64
		if (percentage >= 60)
			return 2;
		// D+ 55-59
		if (percentage >= 55)
			return 1.5;
		// D 50-54
		if (percentage >= 50)
			return 1;
		// F 0-49
		else
			return 0;
	}
}
