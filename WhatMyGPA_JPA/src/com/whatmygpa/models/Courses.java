package com.whatmygpa.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the courses database table.
 * 
 */
@Entity
@Table(name = "courses")
@NamedQuery(name = "Courses.findAll", query = "SELECT c FROM Courses c")
public class Courses implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	private int credits;
	private List<Users> users;

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

	// bi-directional many-to-many association to User
	@ManyToMany(mappedBy = "courses")
	public List<Users> getUsers() {
		return this.users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

}