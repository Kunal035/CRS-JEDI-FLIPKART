package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.business.ProfessorOperations;

import java.util.List;
import java.util.Scanner;

public class CRSProfessorMenu {
	private ProfessorOperations professorOps;

	public CRSProfessorMenu() {
		professorOps = new ProfessorOperations();
	}
	public void CreateProfessorMenu(String profId) {

		System.out.println("in professor menu!");
		Scanner sc=new Scanner(System.in);

		int input = 0;
		while(true)
		{

			System.out.println("\n************* Welcome Professor *************\n");
			System.out.println("\nChoose an option from the menu: ");
			System.out.println("---------------------------------------");
			System.out.println("Press 1: Course Selection");
			System.out.println("Press 2: Add Grade");
			System.out.println("Press 3: View Enrolled Students");
			System.out.println("Press 4: Logout ");
			System.out.println("*********************************************************");
			input =sc.nextInt();
			switch(input)
			{
				case 1:
					//view all the courses taught by the professor
					courseSelection(profId);
					break;
				case 2:
					//view all the enrolled students for the course
					addGrade(profId);
					break;

				case 3:
					//add grade for a student
					viewEnrolledStudents(profId);
					break;
				case 4:
					//logout from the system
					return;
//					System.exit(0);
				default:
					System.out.println("***** Wrong Choice *****");
			}
		}

	}
	public void viewEnrolledStudents(String profId)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Course ID:");
		String courseID = sc.nextLine();
		professorOps.viewEnrolledStudents(courseID);
	}
	public void addGrade(String profId) {
		System.out.println("Adding grades");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Student ID:");
		Integer studentID = sc.nextInt();
		sc.nextLine();  // consume the newline
		System.out.println("Enter Course ID:");
		String courseID = sc.nextLine();
		System.out.println("Enter Grade:");
		String alphaGrade = sc.nextLine();
		professorOps.addGrade(studentID, courseID, alphaGrade);
	}
	public void courseSelection(String profId) {
		System.out.println("Viewing courses for selection");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Instructor ID:");
		Integer professorId = sc.nextInt();
		sc.nextLine();  // consume the newline
		System.out.println("Enter Course ID:");
		String courseID = sc.nextLine();
		professorOps.courseSelection(professorId, courseID);
	}

}

