package com.whatmygpa.models;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the course_enrollments database table.
 * 
 */
@Entity
@Table(name = "course_enrollments")
@NamedQuery(name = "CourseEnrollment.findAll", query = "SELECT c FROM CourseEnrollment c")
public class CourseEnrollment implements Serializable {
	private static final long serialVersionUID = 1L;
	private CourseEnrollmentPK id;
	private double earnedGPA;
	private User user;
	private Course course;

	public CourseEnrollment() {
	}

	@EmbeddedId
	public CourseEnrollmentPK getId() {
		return this.id;
	}

	public void setId(CourseEnrollmentPK id) {
		this.id = id;
	}

	public double getEarnedGPA() {
		return this.earnedGPA;
	}

	public void setEarnedGPA(double earnedGPA) {
		this.earnedGPA = earnedGPA;
	}

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// bi-directional many-to-one association to Course
	@ManyToOne
	@JoinColumn(name = "course_id", insertable = false, updatable = false)
	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}