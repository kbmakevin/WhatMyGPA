package com.whatmygpa.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the course_enrollments database table.
 * 
 */
@Embeddable
public class CourseEnrollmentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String courseId;
	private int userId;

	public CourseEnrollmentPK() {
	}

	@Column(name="course_id")
	public String getCourseId() {
		return this.courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@Column(name="user_id")
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CourseEnrollmentPK)) {
			return false;
		}
		CourseEnrollmentPK castOther = (CourseEnrollmentPK)other;
		return 
			this.courseId.equals(castOther.courseId)
			&& (this.userId == castOther.userId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.courseId.hashCode();
		hash = hash * prime + this.userId;
		
		return hash;
	}
}