package com.flipkart.bean;

import java.util.ArrayList;
import java.util.List;

public class Course {

	private String courseID;
	private String coursename;
	private String professorId;
	private Integer totalSeats;
	private Integer availableSeats;
	private boolean isOffered;
	private List<Integer> enrolledStudents;

	public String getCourseID() {
		return courseID;
	}
	
	public static final int MAX_SEATS = 10;
	
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getprofessorId() {
		return professorId;
	}

	public void setprofessorId(String professorId) {
		this.professorId = professorId;
	}

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public boolean isOffered() {
		return isOffered;
	}

	public void setOffered(boolean isOffered) {
		this.isOffered = isOffered;
	}
	public List<Integer> getEnrolledStudents() {
		return enrolledStudents;
	}
	public void addStudent(Integer studentID) {
		if (!enrolledStudents.contains(studentID)) {
			enrolledStudents.add(studentID);
			availableSeats--; // Decrease available seats
		}
	}
	public void removeStudent(Integer studentID) {
		if (enrolledStudents.remove(studentID)) {
			availableSeats++; // Increase available seats
		}
	}

	public Course(String courseID, String coursename, String professorId, Integer totalSeats, Integer availableSeats,
			boolean isOffered) {
		super();
		this.courseID = courseID;
		this.coursename = coursename;
		this.professorId = professorId;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.isOffered = isOffered;
		this.enrolledStudents = new ArrayList<>();
	}

	public Course() {
		super();
		this.enrolledStudents = new ArrayList<>(); // Initialize the list
	}

}
