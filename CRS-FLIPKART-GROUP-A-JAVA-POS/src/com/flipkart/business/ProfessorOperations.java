package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDaoOps;
import com.flipkart.exception.DuplicateGradeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ProfessorOperations implements ProfessorOperationsInterface {
	private ProfessorDaoOps professorDaoOps;

	public ProfessorOperations() {
		professorDaoOps = new ProfessorDaoOps();
	}

	@Override
	public void viewEnrolledStudents(Integer profId) {
		// Fetch the course by courseID
		professorDaoOps.viewEnrolledStudents(profId);
	}

	@Override
	public void courseSelection(Integer profId) {
		professorDaoOps.courseSelection(profId);
	}

	@Override
	public boolean addGradesForCourse(int professorId, String courseId) {
		// Verify that the professor teaches the course
		if (!professorDaoOps.isCourseTaughtByProfessor(professorId, courseId)) {
			System.out.println("You are not assigned to teach this course.");
			return false;
		}

		// Get the list of students enrolled in the course
		List<Integer> studentIds = professorDaoOps.getStudentsInCourse(courseId);

		if (studentIds.isEmpty()) {
			System.out.println("No students are enrolled in this course.");
			return false;
		}
		// Create a Scanner object once
		Scanner scanner = new Scanner(System.in);

		// Loop through each student and prompt for a grade using forEach
		studentIds.forEach(studentId -> {
			System.out.print("Enter grade for student ID " + studentId + ": ");
			String grade = scanner.nextLine();

            try {
                if (!professorDaoOps.addGrade(studentId, courseId, grade)) {
                    System.out.println("Failed to add grade for student ID " + studentId);
                    throw new RuntimeException("Grade addition failed");
                }
            } catch (DuplicateGradeException e) {
				System.out.println(e.getMessage());
            }
        });

		System.out.println("All grades have been successfully added for course " + courseId);
		return true;
	}

		@Override
		public List<Course> getCoursesTaughtByProfessor ( int professorId){
			return professorDaoOps.getCoursesTaughtByProfessor(professorId);
		}
	}

