package com.flipkart.client;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

import java.util.Scanner;

public class CRSAdminMenu {

	AdminOperations adminOps;

	public void CreateAdminMenu(String AdminId) {
		adminOps = new AdminOperations();
		// TODO Auto-generated method stub
		System.out.println("in admin menu!");
		Scanner sc = new Scanner(System.in);

		int input = 0;
		while (true) {
			System.out.println("\n************* Welcome Admin *************\n");
			System.out.println("\nChoose an option from the menu: ");
			System.out.println("---------------------------------------");
			System.out.println("Press 1: Approve Student Registration");
			System.out.println("Press 2: Add Grade");
			System.out.println("Press 3: Remove Course");
			System.out.println("Press 4: Add Professor");
			System.out.println("Press 5: Remove Professor");
			System.out.println("Press 6: Send Pay Notification");
			System.out.println("Press 7: View approved Students");
			System.out.println("Press 8: Logout");
			System.out.println("*********************************************************");
			input =sc.nextInt();
			switch (input) {
				case 1:
					approveStudentRegistration(AdminId);
					break;
				case 2:
					addCourse(AdminId);
					break;
				case 3:
					removeCourse(AdminId);
					break;
				case 4:
					addProfessor(AdminId);
					break;
				case 5:
					removeProfessor(AdminId);
					break;
				case 6:
					sendFeePayNotification(AdminId);
					break;
				case 7:
					viewApprovedStudents(AdminId);
					break;
				case 8:
					return;
//				System.exit(0);
				default:
					System.out.println("***** Wrong Choice *****");
			}
		}
	}

	private void viewApprovedStudents(String adminId) {
		adminOps.viewApprovedStudents();
	}

	private void sendFeePayNotification(String adminId) {
		// TODO Auto-generated method stub
		adminOps.sendFeePayNotification();
		System.out.println("Fee payment notifications sent.");

	}

//	private void viewReportCard(String adminId) {
//
//
//	}

	private void removeProfessor(String adminId) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Professor ID to remove: ");
		int professorId = sc.nextInt();
		adminOps.removeProfessor(professorId);
	}

	private void addProfessor(String adminId) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Professor Username: ");
		String username = sc.nextLine();
		System.out.println("\nEnter Professor name");
		String profName = sc.nextLine();
		System.out.println("\nEnter Professor Password");
		String profPass = sc.nextLine();
		System.out.println("\nEnter Professor Department");
		String profDept = sc.nextLine();
		System.out.println("\nEnter Professor Designation");
		String profDes = sc.nextLine();
		System.out.println("\nEnter Professor ID");
		Integer profId = Integer.parseInt(sc.nextLine());

		adminOps.addProfessor(new Professor(username,profName,"professor",profPass,profId,profDept,profDes));

	}

	private void removeCourse(String adminId) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Course ID to remove: ");
		String courseId = sc.nextLine();
		adminOps.removeCourse(courseId);
	}

	private void addCourse(String adminId) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Course ID to add: ");
		String courseId = sc.nextLine();
		System.out.println("Enter Course Name: ");
		String courseName = sc.nextLine();

		adminOps.addCourse(courseName, courseId);


	}


	private void approveStudentRegistration(String adminId) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Student ID to approve: ");
		int studentId = sc.nextInt();
		adminOps.approveStudentRegistration(studentId);
	}
}
