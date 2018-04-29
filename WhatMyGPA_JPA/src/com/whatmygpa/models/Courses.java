package com.whatmygpa.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the courses database table.
 * 
 */
@Entity
@Table(name = "courses")
@NamedQueries({ @NamedQuery(name = "Courses.findAll", query = "SELECT c FROM Courses c"),
		@NamedQuery(name = "Courses.findOne", query = "SELECT c FROM Courses c WHERE c.code = :code"),
		@NamedQuery(name = "Courses.removeOne", query = "DELETE FROM Courses c WHERE c.code = :code") })
public class Courses implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	private int credits;
	private List<CourseEnrollment> courseEnrollments;

	public Courses() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCredits() {
		return this.credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	// bi-directional many-to-one association to CourseEnrollment
	@OneToMany(mappedBy = "course")
	public List<CourseEnrollment> getCourseEnrollments() {
		return this.courseEnrollments;
	}

	public void setCourseEnrollments(List<CourseEnrollment> courseEnrollments) {
		this.courseEnrollments = courseEnrollments;
	}

	public CourseEnrollment addCourseEnrollment(CourseEnrollment courseEnrollment) {
		getCourseEnrollments().add(courseEnrollment);
		courseEnrollment.setCourse(this);

		return courseEnrollment;
	}

	public CourseEnrollment removeCourseEnrollment(CourseEnrollment courseEnrollment) {
		getCourseEnrollments().remove(courseEnrollment);
		courseEnrollment.setCourse(null);

		return courseEnrollment;
	}

}