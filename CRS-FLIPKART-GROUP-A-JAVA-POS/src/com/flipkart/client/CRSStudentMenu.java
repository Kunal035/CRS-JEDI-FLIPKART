package com.flipkart.client;

import com.flipkart.bean.Payment;
import com.flipkart.bean.GradeCard;
import com.flipkart.business.StudentOperations;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import com.flipkart.exception.CourseLimitExceededException;
>>>>>>> e9b59a0 (Added exception and debugged)
=======
import com.flipkart.exception.CourseLimitExceededException;
>>>>>>> dd79919 (Final Push xD)
import com.flipkart.exception.InvalidPaymentAmountException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;
import java.util.stream.IntStream;

public class CRSStudentMenu {
	private StudentOperations studentOperations;
	public CRSStudentMenu() {
		studentOperations = new StudentOperations();
	}

	public void CreateStudentMenu(Integer studentId) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int input = 0;
		while (true) {
			System.out.println("\n************* Welcome Student *************\n");
			System.out.println("\nChoose an option from the menu: ");
			System.out.println("---------------------------------------");
			System.out.println("Press 1: Register Courses");
			System.out.println("Press 2: Add Course");
			System.out.println("Press 3: Drop Course");
			System.out.println("Press 4: View Registered Courses");
			System.out.println("Press 5: View Report Card");
			System.out.println("Press 6: DoPayment");
			System.out.println("Press 7: Logout");
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
					viewRegisteredCourses(studentId);
					break;
				case 5:
					viewReportCard(studentId);
					break;
				case 6:
					doPayment(studentId);
					break;
				case 7:
//				System.exit(0);
					return;
				default:
					System.out.println("***** Wrong Choice *****");
			}
		}
	}

	private void doPayment(int studentId) {
		try {
			// Check if student is registered in any courses
			if (!studentOperations.isStudentAlreadyRegistered(studentId)) {
				System.out.println("Student is not registered in any courses.");
				return;
			}

			double amountDue = studentOperations.getAmountDue(studentId);
			if (amountDue == 0) {
				System.out.println("You have already paid. Thank you!");
				return;
			}

			// Display the billing amount
			System.out.println("The billing amount is: " + amountDue);

			if (!studentOperations.areCardDetailsSaved(studentId)) {
				getCardDetails(studentId);
			}

			Scanner sc = new Scanner(System.in);

			// Get the amount from the user
			System.out.println("Please enter the amount you would like to pay: ");
			double amount = sc.nextDouble();

			// Validate the payment amount
			if (amount != amountDue) {
				throw new InvalidPaymentAmountException("Entered amount does not match the amount due. Please enter the correct amount.");
			}

			System.out.println("Please enter true to do the payment.");
			boolean choice = sc.nextBoolean();

			if (choice) {
				if (studentOperations.processPayment(studentId, amountDue)) {
					studentOperations.updatePaymentStatus(studentId, 0);
					System.out.println("Payment successful. Amount due: 0");
				} else {
					System.out.println("Payment failed.");
				}
			}
		} catch (InvalidPaymentAmountException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}

	private void getCardDetails(int studentId) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter card number:");
		String cardNumber = scanner.nextLine();

		System.out.println("Enter card expiry date (YYYY-MM-DD):");
		String cardExpiry = scanner.nextLine();

		System.out.println("Enter card CVV:");
		int cardCVV = scanner.nextInt();

		if (studentOperations.saveCardDetails(studentId, cardNumber, cardExpiry, cardCVV)) {
			System.out.println("Card details saved successfully.");
			doPayment(studentId);
		} else {
			System.out.println("Failed to save card details.");
		}
	}

	private void viewRegisteredCourses(int studentId) {
		studentOperations.viewRegisteredCourses(studentId); // Assuming semesterId 0 or as needed
	}

	private void addCourse(Integer studentId) {
		if (!(studentOperations.isAddDropWindowOpen())) {
			System.out.println("Course addition is currently disabled.");
			return;
		}
		Scanner sc = new Scanner(System.in);
		studentOperations.showCourseCatalog();
		String courseId;
		while (true) {
			System.out.print("Enter Course ID to add: ");
			courseId = sc.nextLine();
			if (studentOperations.isValidCourseId(courseId)) {
				// Attempt to add the course and handle possible exceptions
				studentOperations.addCourse(studentId, courseId);
				// System.out.println("Course added successfully.");
				break;
			} else {
				System.out.println("Invalid Course ID. Please enter a valid Course ID.");
			}
		}
	}
	private void dropCourse(int studentId) {
		if (!(studentOperations.isAddDropWindowOpen())) {
			System.out.println("Course Dropping is currently disabled.");
			return;
		}
		Scanner sc = new Scanner(System.in);
		studentOperations.viewRegisteredCourses(studentId);
		String courseId;
		while (true) {
			System.out.print("Enter Course ID to drop course: ");
			courseId = sc.nextLine();
			if (studentOperations.isValidCourseId(courseId)) {
				studentOperations.dropCourse(studentId, courseId);
				break;
			} else {
				System.out.println("Invalid Course ID. Please enter a valid Course ID.");
			}
		}
	}



	private void registerCourses(int studentId) {
		// Check if the student is already registered in any courses
		if (studentOperations.isStudentAlreadyRegistered(studentId)) {
			System.out.println("You are already registered in courses.");
			return; // Exit the method if the student is already registered
		}

		Scanner scanner = new Scanner(System.in);

		// Display available courses
		studentOperations.showCourseCatalog();

		// Set to track selected courses and avoid duplicates
		Set<String> selectedCourses = new HashSet<>();

		// Prompt the student to select 4 primary courses
		List<String> primaryCourses = new ArrayList<>();
		System.out.println("Select 4 primary courses:");
		IntStream.rangeClosed(1, 4).forEach(i -> {
			String courseId;
			while (true) {
				System.out.print("Enter Course ID for primary course " + i + ": ");
				courseId = scanner.nextLine();
				// if (studentOperations.isValidCourseId(courseId) && !selectedCourses.contains(courseId)) {
				if (!selectedCourses.contains(courseId)) {
					primaryCourses.add(courseId);
					selectedCourses.add(courseId); // Add to the set to track the selection
					break;
				} else {
					System.out.println("You have already selected this course. Please choose a different Course ID.");
				}
				// } else {
				//     System.out.println("Invalid Course ID. Please enter a valid Course ID.");
				// }
			}
		});

		// Prompt the student to select 2 alternate courses
		List<String> alternateCourses = new ArrayList<>();
		System.out.println("Select 2 alternate courses:");
		IntStream.rangeClosed(1, 2).forEach(i -> {
			String courseId;
			while (true) {
				System.out.print("Enter Course ID for alternate course " + i + ": ");
				courseId = scanner.nextLine();
				// if (studentOperations.isValidCourseId(courseId) && !selectedCourses.contains(courseId)) {
				if (!selectedCourses.contains(courseId)) {
					alternateCourses.add(courseId);
					selectedCourses.add(courseId); // Add to the set to track the selection
					break;
				} else {
					System.out.println("You have already selected this course. Please choose a different Course ID.");
				}
				// } else {
				//     System.out.println("Invalid Course ID. Please enter a valid Course ID.");
				// }
			}
		});

		// Call StudentOperations to attempt course registration
		studentOperations.registerCourses(studentId, primaryCourses, alternateCourses);
	}

	// In StudentMenu.java
	public void viewReportCard(int studentId) {
		List<GradeCard> gradeCards = studentOperations.getGradeCard(studentId);

		if (gradeCards.isEmpty()) {
			System.out.println("No grades found for Student ID: " + studentId);
			return;
		}

		// Define column widths
		int courseIdWidth = 15;
		int courseNameWidth = 50;
		int gradeWidth = 10;
		int totalWidth = courseIdWidth + courseNameWidth + gradeWidth + 5; // Adjust total width with borders and separators

		// Print the report card header
		String border = "+".repeat(totalWidth);
		String centerText = "REPORT CARD";
		String studentInfo = String.format("Student ID: %d", studentId);

		// Print the top border
		System.out.println(border);

		// Print the centered title
		int padding = (totalWidth - centerText.length()) / 2;
		System.out.printf("|%" + padding + "s%s%" + padding + "s|%n", "", centerText, "");

		// Print the student information
		System.out.printf("|%-" + (totalWidth - 2) + "s |%n", studentInfo);

		// Print the separator
		System.out.println("+" + "-".repeat(courseIdWidth) + "+" + "-".repeat(courseNameWidth) + "+" + "-".repeat(gradeWidth) + "+");

		// Print the table header
		System.out.printf("|%-" + courseIdWidth + "s| %-" + courseNameWidth + "s| %-" + gradeWidth + "s|%n", "Course ID", "Course Name", "Grade");
		System.out.println("|" + "-".repeat(courseIdWidth) + "+" + "-".repeat(courseNameWidth) + "+" + "-".repeat(gradeWidth) + "  |");

		// Print the course details
		gradeCards.forEach(card ->
				System.out.printf("|%-" + courseIdWidth + "s| %-" + courseNameWidth + "s| %-" + gradeWidth + "s|%n",
						card.getCourseId(), card.getCourseName(), card.getGrade())
		);


		// Print the bottom border
		System.out.println("+" + "-".repeat(courseIdWidth) + "+" + "-".repeat(courseNameWidth) + "+" + "-".repeat(gradeWidth) + "+");
	}

}
