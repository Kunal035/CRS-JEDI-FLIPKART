package com.flipkart.business;
//Group A

import com.flipkart.bean.Admin;
import com.flipkart.dao.AdminDaoOps;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminOperations implements AdminOperationsInterface{
	private List<Admin> admins;
	private AdminDaoOps adminDaoOps;


	public AdminOperations() {


		admins = new ArrayList<>();
		adminDaoOps = new AdminDaoOps();
	}

	@Override
	public Admin findAdminByUsername(String userName) {
		for (Admin admin : admins) {
			if (admin.getUserName().equals(userName)) {
				return admin;
			}
		}
		return null;

	}
	@Override
	public void addCourse() {
		Scanner sc = new Scanner(System.in);
		String courseId;
		while (true){
			System.out.println("Enter Course ID: ");
			courseId= sc.nextLine();
			if(adminDaoOps.isCourseExists(courseId)){
				System.out.println("Course already exists!");
			}
			else{
				break;
			}
		}
		System.out.println("Enter Course Name: ");
		String course_name = sc.nextLine();
		System.out.println("Is the course Offered: ");
		boolean isOffered = sc.nextBoolean();
		adminDaoOps.addCourse(courseId,course_name,isOffered);
	}
	@Override
	public void showAllProfs(){
		adminDaoOps.showAllProfs();
	}
	@Override
	public Integer addProfessor(String username, String professorName, String role, String password, String department, String designation) {

		return adminDaoOps.addProfessor(username, professorName, role, password, department, designation);

	}
	@Override
	public void removeProfessor(Integer professorID) {
		adminDaoOps.removeProf(professorID);
	}
	@Override
	public void sendFeePayNotification() {
	}
	@Override
	public void viewApprovedStudents() {
		adminDaoOps.viewApprovedStudents();
	}
	@Override
	public void showUnapprovedStudents() {
		System.out.println("The list of unapproved students is:");
		boolean flag = adminDaoOps.printUnapprovedStudents();
		if(flag) {
			System.out.println("Enter the student id you wish to approve:");
			Scanner sc = new Scanner(System.in);
			int studentId = sc.nextInt();
			adminDaoOps.approveOneStudent(studentId);
		}
	}
	@Override
	public void showAllCourses(){
		adminDaoOps.showAllCourses();
	}
	@Override
	public void removeCourse(String course_id){
		adminDaoOps.removeCourse(course_id);
	}
	@Override
	public void setAddDropWindow(boolean open) {
		adminDaoOps.setAddDropWindow(open);
	}
	@Override
	public void approveAllUnapprovedStudents() {
		System.out.println("The list of unapproved students is:");
		boolean flag = adminDaoOps.printUnapprovedStudents();
		if(flag) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Type true to approve all!");
			Boolean choice = sc.nextBoolean();
			adminDaoOps.approveAllUnapprovedStudents(choice);
		}
	}
}
