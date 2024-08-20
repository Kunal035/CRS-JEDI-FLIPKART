package com.flipkart.client;

import com.flipkart.bean.Payment;
import com.flipkart.bean.GradeCard;
import com.flipkart.business.StudentOperations;

import java.util.Scanner;

public class CRSStudentMenu {
	private StudentOperations studentOperations;
	public CRSStudentMenu() {
		studentOperations = new StudentOperations();
	}

	public void CreateStudentMenu(Integer studentId) {
		// TODO Auto-generated method stub
		System.out.println("in student menu!");

		Scanner sc = new Scanner(System.in);

		int input = 0;
		while (true) {
			System.out.println("\n************* Welcome Student *************\n");
			System.out.println("\nChoose an option from the menu: ");
			System.out.println("---------------------------------------");
			System.out.println("Press 1: Course Registeration");
			System.out.println("Press 2: Add Course to cart");
			System.out.println("Press 3: Drop Course");
			System.out.println("Press 4: Finish registration ");
			System.out.println("Press 5: View Registered Courses");
			System.out.println("Press 6: View Report Card");
			System.out.println("Press 7: Check Payment Window");
			System.out.println("Press 8: Do Payment");
			System.out.println("Press 9: Logout");
			System.out.println("*********************************************************");
			input =sc.nextInt();
			switch (input) {
				case 1:
					registerCourses(studentId);
					break;
				case 2:
					addCourse(studentId);
					break;
				case 3:
					dropCourse(studentId);
					break;

				case 4:
					finishRegistration(studentId);
					break;
				case 5:
					viewRegisteredCourses(studentId);
					break;
				case 6:
					viewReportCard(studentId);
					break;
				case 7:
					checkPaymentWindow(studentId);
					break;
				case 8:
					doPayment(studentId);
					break;
				case 9:
//				System.exit(0);
					return;
				default:
					System.out.println("***** Wrong Choice *****");
			}
		}
	}

	private void doPayment(int studentId) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Payment Amount: ");
		Integer amount = sc.nextInt();
		System.out.println("Enter Payment ID: ");
		Integer paymentID = sc.nextInt();
		// Assuming payment status is true for successful payments
		Boolean paymentStatus = true;

		Payment payment = new Payment(studentId, paymentID, paymentStatus, amount);
		studentOperations.DoPayment(payment);
		System.out.println("Payment processed.");
	}

	private void checkPaymentWindow(int studentId) {
		Boolean isOpen = studentOperations.checkPaymentWindow(studentId);
		if (isOpen) {
			System.out.println("Payment window is open.");
		} else {
			System.out.println("Payment window is closed.");
		}
	}

	private void viewReportCard(int studentId) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Semester ID: ");
		int semesterId = sc.nextInt();
//		GradeCard gradeCard = studentOperations.viewReportCard(studentId, semesterId);
//		if (gradeCard != null) {
//			// Print or process the GradeCard details
//			System.out.println("Grade Card Details:");
//			// Assuming GradeCard has a method to display details
//			// System.out.println(gradeCard.getDetails());
//		} else {
//			System.out.println("Report Card not found.");
//		}
	}

	private void viewRegisteredCourses(int studentId) {
		studentOperations.viewRegisteredCourses(studentId); // Assuming semesterId 0 or as needed
	}

	private void finishRegistration(int studentId) {
		studentOperations.finishRegistration();
	}

	private void dropCourse(int studentId) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Course ID to drop: ");
		String courseId = sc.next();

		studentOperations.dropCourse(studentId, courseId);
	}

	private void addCourse(Integer studentId) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Course ID to add: ");
		String courseId = sc.next();
		studentOperations.addCourse(studentId, courseId);

	}

	private void registerCourses(int studentId) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Course ID to register: ");
		String courseId = sc.next();
		studentOperations.registerCourses(studentId, courseId);
	}

}
