package com.whatmygpa.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name = "users")
@NamedQueries({
@NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
@NamedQuery(name = "Users.authenticate", query="SELECT u FROM Users u WHERE u.username = :username and u.password = :password")
})
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private double gpa;
	private String name;
	private String password;
	private String type;
	private String username;
	private List<Courses> courses;

	public Users() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	// bi-directional many-to-many association to Cours
	@ManyToMany
	@JoinTable(name = "course_enrollments", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "course_id") })
	public List<Courses> getCourses() {
		return this.courses;
	}

	public void setCourses(List<Courses> courses) {
		this.courses = courses;
	}

}