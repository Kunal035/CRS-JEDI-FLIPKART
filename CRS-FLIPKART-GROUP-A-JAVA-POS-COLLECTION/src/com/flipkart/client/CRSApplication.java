/**
 *
 */
package com.flipkart.client;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.business.ProfessorOperations;
import com.flipkart.business.StudentOperations;

import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class CRSApplication {
	private StudentOperations studentOps ;
	private ProfessorOperations profOps ;
	private AdminOperations adminOps;
	private Scanner sc ;

	public CRSApplication(){

		studentOps = new StudentOperations();
		profOps = new ProfessorOperations();
		adminOps=new AdminOperations();

		sc= new Scanner(System.in);
	}
	public static void main(String[] args) {
		CRSApplication newUser = new CRSApplication();
		newUser.showMenu();
	}
	private void showMenu() {

		while (true) {
			System.out.println("\n************* Welcome to CRS Application *************\n");
			System.out.println("\nChoose an option from the menu: ");
			System.out.println("---------------------------------------");
			System.out.println("Press 1: Login");
			System.out.println("Press 2: View Courses List");
			System.out.println("Press 3: Update Password");
			System.out.println("Press 4: Add Student");
			System.out.println("Press 5: Show all students");
			System.out.println("Press 6: Add Professor");
			System.out.println("Press 7: Show all professors");
			System.out.println("Press 8: Exit ");
			System.out.println("*********************************************************");
			int menuOption = sc.nextInt();
			sc.nextLine();
			switch (menuOption) {
				case 1:
					login();
					break;

				case 2:
					courseByProfList();
					break;

				case 3:
					updatePassword();
					break;

				case 4:
					addStudent();
					break;
				case 5:
					showAllStudents();
					break;
				case 6:
					addProfessor();
					break;
				case 7:
					showAllProfessors();
					break;
				case 8:
					sc.close();
					System.out.println("Exited Successfully!");
					return;
				default:
					System.out.println("Invalid input");
					break;
			}
		}
	}

	private void showAllStudents(){
		studentOps.viewStudents();
	}
	private void showAllProfessors(){
		profOps.viewProfessors();
	}


	private void login() {

		String username=null;
		String password=null;
		String role = null;

		System.out.println("********************************");
		System.out.println("Enter your Username: ");
		username = sc.nextLine();
		System.out.println("Enter your Password: ");
		password = sc.nextLine();
		System.out.println("Choose your Role: ");
		System.out.println("Press 1 for Student");
		System.out.println("Press 2 for Professor");
		System.out.println("Press 3 for Admin");

		int roleOption = sc.nextInt();
		switch (roleOption) {
			case 1:
				role = "student";
				break;

			case 2:
				role = "professor";
				break;

			case 3:
				role = "admin";
				break;
			default:
				System.out.println("Invalid option");
		}
		switch (role) {
			case "student":
				System.out.println("********************************");
				System.out.println("Logged In Successfully as a Student");
				System.out.println("Welcome " + username + " !!");

				CRSStudentMenu stud = new CRSStudentMenu();

				Integer StudID= studentOps.getStudentIdByUsername(username);
				stud.CreateStudentMenu(StudID);
				System.out.println("Welcome " + username + " !!");
				break;

			case "professor":
				System.out.println("********************************");
				System.out.println("Logged In Successfully as a Professor");
				System.out.println("Welcome " + username +" Sir!");
				CRSProfessorMenu prof = new CRSProfessorMenu();
				prof.CreateProfessorMenu(username);
				System.out.println("Welcome " + username + " Sir!");
				break;

			case "admin":
				System.out.println("********************************");
				System.out.println("Logged In Successfully as an Admin");
				System.out.println("Welcome " + username + " !!");
				CRSAdminMenu adm = new CRSAdminMenu();
				adm.CreateAdminMenu(username);
				System.out.println("Welcome " + username + " Sir!");
				break;

			default:
				System.out.println("Invalid Role");
				System.out.println("********************************");
		}
	}
	void courseByProfList() {
		// Retrieve the list of all courses from adminOps
		List<Course> courseCatalogue = adminOps.getCourseCatalogue();

		// Check if courseCatalogue is null or empty
		if (courseCatalogue == null || courseCatalogue.isEmpty()) {
			System.out.println("No courses available.");
			return;
		}

		// Print each course with its Course ID and Instructor ID
		System.out.println("Courses and their Instructor IDs:");
		for (Course course : courseCatalogue) {
			String courseID = course.getCourseID();
			String professorId = course.getprofessorId(); // Assuming this is the ID of the instructor

			// Check if professorId is null and print appropriate message
			String instructorMessage = (professorId != null && !professorId.isEmpty()) ? professorId : "No instructor assigned";

			System.out.println("Course ID: " + courseID + ", Instructor ID: " + instructorMessage);
		}

		System.out.println("End of course list.");
	}




	void addStudent() {
		System.out.println("enter Username");
		String username = sc.nextLine();
		System.out.println("enter Password");
		String password = sc.nextLine();
		System.out.println("enter name");
		String name = sc.nextLine();
		System.out.println("enter Department");
		String department = sc.nextLine();
		System.out.println("enter studentID");
		int studentID = sc.nextInt();

		if(studentOps.addStudent(username,name,"student",password,studentID,department)){
			System.out.println("Student Added Successfully");
		}else{
			System.out.println("student already exists");
		}
	}
	void addProfessor() {
		System.out.println("enter Username");
		String username = sc.nextLine();
		System.out.println("enter Password");
		String password = sc.nextLine();
		System.out.println("enter name");
		String name = sc.nextLine();
		System.out.println("enter Department");
		String department = sc.nextLine();
		System.out.println("enter designation");
		String designation = sc.nextLine();
		System.out.println("enter professorId");
		int professorId = sc.nextInt();

		if(profOps.addProfessor(username,name,"professor",password,professorId,department,designation)){
			System.out.println("Professor Added Successfully");
		}else{
			System.out.println("Professor already exists");
		}
	}
	void updatePassword() {
		System.out.println("in update");
		System.out.println("Enter your role: ");
		String role = sc.nextLine();
		switch (role) {
			case "student":
				System.out.println("Enter your Username: ");
				String username = sc.nextLine();
				System.out.println("Enter your current Password: ");
				String password = sc.nextLine();

				Student currStudent= studentOps.findStudentByUsername(username);

				if(currStudent!=null){
					if (currStudent.getPassword().equals(password)) {
						System.out.println("Enter your New Password: ");
						String newPassword = sc.nextLine();
						currStudent.setPassword(newPassword);
						System.out.println("Successfully updated password");
					}
					else {
						System.out.println("Invalid Password");
					}
				}
				break;

			case "professor":
				System.out.println("Enter your Username: ");
				String username1 = sc.nextLine();
				System.out.println("Enter your Password: ");
				String password1 = sc.nextLine();

				Professor currProf = profOps.findProfessorByUsername(username1);
				if(currProf!=null){
					if (currProf.getPassword().equals(password1)) {
						System.out.println("Enter your New Password: ");
						String newPassword = sc.nextLine();
						currProf.setPassword(newPassword);
						System.out.println("Successfully updated password"+ currProf.getPassword());
					}
					else{
						System.out.println("Invalid Password");
					}
				}

				break;
			case "admin":
				System.out.println("Enter your Username: ");
				String username2 = sc.nextLine();
				System.out.println("Enter your Password: ");
				String password2 = sc.nextLine();

				Admin currAdmin= adminOps.findAdminByUsername(username2);

				if (currAdmin!=null) {
					if (currAdmin.getPassword().equals(password2)) {
						System.out.println("Enter your New Password: ");
						String newPassword2 = sc.nextLine();
						currAdmin.setPassword(newPassword2);
						System.out.println("Successfully updated password");
						System.out.println("Your new Password is: " + currAdmin.getPassword());
					} else {
						System.out.println("Invalid Password");
					}
				}
				break;

			default:
				return;
		}

	}

}
