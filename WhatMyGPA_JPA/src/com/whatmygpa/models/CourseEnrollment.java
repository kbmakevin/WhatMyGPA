package com.whatmygpa.models;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the course_enrollments database table.
 * 
 */
@Entity
@Table(name = "course_enrollments")

@NamedQueries({ @NamedQuery(name = "CourseEnrollment.findAll", query = "SELECT c FROM CourseEnrollment c"),
		@NamedQuery(name = "CourseEnrollment.findAllForUser", query = "SELECT c FROM CourseEnrollment c WHERE c.user = :user"),
		@NamedQuery(name = "CourseEnrollment.findSpecific", query = "SELECT c FROM CourseEnrollment c WHERE c.user = :user AND c.course = :course"),
		@NamedQuery(name = "CourseEnrollment.removeOne", query = "DELETE FROM CourseEnrollment c WHERE c.user = :user AND c.course = :course") })
public class CourseEnrollment implements Serializable {
	private static final long serialVersionUID = 1L;
	private CourseEnrollmentPK id;
	private int gradeReceived;
	private Users user;
	private Courses course;

	public CourseEnrollment() {
	}

	@EmbeddedId
	public CourseEnrollmentPK getId() {
		return this.id;
	}

	public void setId(CourseEnrollmentPK id) {
		this.id = id;
	}

	public int getGradeReceived() {
		return this.gradeReceived;
	}

	public void setGradeReceived(int gradeReceived) {
		this.gradeReceived = gradeReceived;
	}

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	public Users getUser() {
		return this.user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	// bi-directional many-to-one association to Course
	@ManyToOne
	@JoinColumn(name = "course_id", insertable = false, updatable = false)
	public Courses getCourse() {
		return this.course;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}
}