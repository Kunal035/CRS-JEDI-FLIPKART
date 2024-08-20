package com.flipkart.business;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

public class UserOperations {

	private AdminOperations adminOps;
	private ProfessorOperations professorOps;
	private StudentOperations studentOps;

	public UserOperations() {
		adminOps = new AdminOperations();
		professorOps = new ProfessorOperations();
		studentOps = new StudentOperations();
	}
	boolean loginUser(String userID, String password, String role) {
        return switch (role.toLowerCase()) {
            case "admin" -> {
                Admin admin = adminOps.findAdminByUsername(userID);
                yield admin != null && admin.getPassword().equals(password);
            }
            case "student" -> {
                Student student = studentOps.findStudentByUsername(userID);
                yield student != null && student.getPassword().equals(password);
            }
            case "professor" -> {
                Professor professor = professorOps.findProfessorByUsername(userID);
                yield professor != null && professor.getPassword().equals(password);
            }
            default -> false;
        };
	}
	void updateStudentPassword(String userID, String newPassword) {
		Student student = studentOps.findStudentByUsername(userID);
		if (student != null) {
			student.setPassword(newPassword);
			System.out.println("Student password updated successfully.");
		} else {
			System.out.println("Student not found.");
		}
	}
	void updateAdminPassword(String userID, String newPassword) {
		Admin admin = adminOps.findAdminByUsername(userID);
		if (admin != null) {
			admin.setPassword(newPassword);
			System.out.println("Admin password updated successfully.");
		} else {
			System.out.println("Admin not found.");
		}
	}
	void updateProfPassword(String userID, String newPassword) {
		Professor professor = professorOps.findProfessorByUsername(userID);
		if (professor != null) {
			professor.setPassword(newPassword);
			System.out.println("Professor password updated successfully.");
		} else {
			System.out.println("Professor not found.");
		}
	}
}
