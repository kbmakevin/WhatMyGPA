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
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name = "users")
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.authenticate", query = "SELECT u FROM User u WHERE u.username = :username and u.password = :password") })

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private double gpa;
	private String name;
	private String password;
	private String type;
	private String username;
	private List<CourseEnrollment> courseEnrollments;

	public User() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getGpa() {
		return this.gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// bi-directional many-to-one association to CourseEnrollment
	@OneToMany(mappedBy = "user")
	public List<CourseEnrollment> getCourseEnrollments() {
		return this.courseEnrollments;
	}

	public void setCourseEnrollments(List<CourseEnrollment> courseEnrollments) {
		this.courseEnrollments = courseEnrollments;
	}

	public CourseEnrollment addCourseEnrollment(CourseEnrollment courseEnrollment) {
		getCourseEnrollments().add(courseEnrollment);
		courseEnrollment.setUser(this);

		return courseEnrollment;
	}

	public CourseEnrollment removeCourseEnrollment(CourseEnrollment courseEnrollment) {
		getCourseEnrollments().remove(courseEnrollment);
		courseEnrollment.setUser(null);

		return courseEnrollment;
	}

}