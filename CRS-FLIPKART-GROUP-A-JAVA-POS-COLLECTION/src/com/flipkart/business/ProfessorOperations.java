package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProfessorOperations {
	//	private List<Professor> professors;
	private static List<Professor> professors = new ArrayList<>();
	private AdminOperations adminOps;
	private StudentOperations studentOps;
	private List<Grade> grades;
	public ProfessorOperations() {
		//professors = new ArrayList<>();
		professors.add(new Professor("parth1", "parth", "professor", "pass", 1, "CS", "Senior"));
		professors.add(new Professor("ansh1", "ansh", "professor", "pass", 2, "CS", "Senior"));
		grades = new ArrayList<>();
		adminOps = new AdminOperations();
		studentOps = new StudentOperations();
	}
	private String identifier;
	// Constructor with an argument
	public ProfessorOperations(String identifier) {
		this.identifier = identifier;

	}

	public List<Professor> getProfessors() {
		return professors;
	}

	public boolean addProfessor(String userName, String name, String role, String password, Integer professorId, String department, String designation) {
		if (findProfessorByUsername(userName) == null) {
			professors.add(new Professor(userName, name, role, password, professorId, department, designation));
			return true;
		}
		return false;
	}
	public Professor findProfessorByUsername(String userName) {
		for (Professor professor : professors) {
			if (professor.getUserName().equals(userName)) {
				return professor;
			}
		}
		return null;
	}
	public void getCourses(String profID) {
		List<Course> courses = adminOps.getCourseCatalogue().stream()
				.filter(course -> course.getprofessorId() != null && course.getprofessorId().equals(profID))
				.toList();
		System.out.println("Courses taught by Professor with ID " + profID + ":");
		for (Course course : courses) {
			System.out.println(course.getCourseID() + " - " + course.getCoursename());
		}
	}

	public void addGrade(Integer studentID, String courseID, String alphaGrade) {
//		grades.add(new Grade(studentID, courseID, alphaGrade));
//		System.out.println("Grade added successfully for student ID: " + studentID);
	}
	//	public void viewEnrolledStudents(String courseID) {
//		List<Student> students = adminOps.getCourseCatalogue().stream()
//				.filter(course -> course.getCourseID().equals(courseID))
//				.flatMap(course -> course.getEnrolledStudents().stream()
//						.map(studentId -> adminOps.findStudentById(studentId)))
//				.toList();
//
//		System.out.println("Students enrolled in course " + courseID + ":");
//		for (Student student : students) {
//			System.out.println(student.getStudentID() + " - " + student.getName());
//		}
//	}
	public void viewEnrolledStudents(String courseID) {
		// Fetch the course by courseID
		Course course = adminOps.getCourseCatalogue().stream()
				.filter(c -> c.getCourseID().equals(courseID))
				.findFirst()
				.orElse(null);

		if (course == null) {
			System.out.println("Course with ID " + courseID + " not found.");
			return;
		}

		// Get the list of student IDs enrolled in the course
		List<Integer> studentIDs = course.getEnrolledStudents();

		// Fetch the students by their IDs
		List<Student> students = studentIDs.stream()
				.map(studentID -> studentOps.findStudentById(studentID)) // Assuming you have a StudentOperations instance named studentOps
				.filter(Objects::nonNull) // Filter out null values if any
				.toList();

		System.out.println("Students enrolled in course " + courseID + ":");
		for (Student student : students) {
			System.out.println(student.getStudentID() + " - " + student.getName());
		}
	}

	public void courseSelection(Integer professorId, String courseID) {
		Professor professor = findProfessorByID(professorId);
		if (professor != null) {
			Course course = adminOps.findCourseById(courseID);
			if (course != null) {
				course.setprofessorId(professor.getUserName()); // Set professorId to professor's username
				List<Course> x= adminOps.getCourseCatalogue();
				for(Course course1: x){
					Boolean ismatch = course1.getCourseID().equals(courseID);
					if(ismatch){
						course1.setprofessorId(Integer.toString(professorId));
					}
				}
				System.out.println("Course " + courseID + " assigned to Professor " + professor.getName());
			} else {
				System.out.println("Course not found.");
			}
		} else {
			System.out.println("Course not found.");
		}
	}
	public void viewProfessors() {
		for (Professor professor : professors) {
			System.out.println(professor.getprofessorId() + " " + professor.getDepartment() + " " + professor.getName() + " " + professor.getUserName()+ " "+ professor.getPassword());
		}
	}
	private Professor findProfessorByID(Integer professorId) {
		for (Professor professor : professors) {
			if (professor.getprofessorId().equals(professorId)) {
				return professor;
			}
		}
		return null;
	}
}

