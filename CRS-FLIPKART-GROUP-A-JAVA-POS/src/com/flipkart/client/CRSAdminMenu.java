package com.flipkart.client;

import com.flipkart.business.AdminOperations;

import java.util.Scanner;

public class CRSAdminMenu {

    AdminOperations adminOps;

    public void CreateAdminMenu(Integer AdminId) {
        adminOps = new AdminOperations();
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);

        int input = 0;
        while (true) {
            System.out.println("\n************* Welcome Admin *************\n");
            System.out.println("\nChoose an option from the menu: ");
            System.out.println("---------------------------------------");
            System.out.println("Press 1: Approve Student Registration");
            System.out.println("Press 2: Add Professor");
            System.out.println("Press 3: Add Course");
            System.out.println("Press 4: Remove Professor");
            System.out.println("Press 5: Remove Course");
            System.out.println("Press 6: View approved Students");
            System.out.println("Press 7: Send Payment Notification");
            System.out.println("Press 8: Enable/Disable Add/Drop Course");
            System.out.println("Press 9: Logout");
            System.out.println("*********************************************************");
            input = sc.nextInt();
            switch (input) {
                case 1:
                    approveStudentRegistration();
                    break;
                case 2:
                    addProfessor();
                    break;
                case 3:
                    addCourse();
                    break;
                case 4:
                    removeProfessor();
                    break;
                case 5:
                    removeCourse();
                    break;
                case 6:
                    viewApprovedStudents();
                    break;
                case 7:
                    sendFeePayNotification(AdminId);
                    break;
                case 8:
                    addDropWindow();
                    break;
                case 9:
                    return;
                //	System.exit(0);
                default:
                    System.out.println("***** Wrong Choice *****");
            }
        }
    }

    private void addDropWindow() {
        // Prompt the admin for their choice
        System.out.println("Would you like to enable or disable the add/drop course window?");
        System.out.println("Enter 'enable' to enable or 'disable' to disable:");

        // Read the user's input
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine().trim().toLowerCase();

        // Process the input
        switch (choice) {
            case "enable":
                adminOps.setAddDropWindow(true);
                System.out.println("The add/drop course window has been enabled.");
                break;
            case "disable":
                adminOps.setAddDropWindow(false);
                System.out.println("The add/drop course window has been disabled.");
                break;
            default:
                System.out.println("Invalid choice. Please enter 'enable' or 'disable'.");
                break;
        }
    }

    private void viewApprovedStudents() {
        adminOps.viewApprovedStudents();
    }

    private void sendFeePayNotification(Integer adminId) {
        // TODO Auto-generated method stub
        adminOps.sendFeePayNotification();
        System.out.println("Fee payment notifications sent.");

    }


    private void removeProfessor() {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("The list of Professors is:");
        adminOps.showAllProfs();
        System.out.println("Enter Professor ID to remove: ");
        int professorId = sc.nextInt();
        adminOps.removeProfessor(professorId);
    }

    private void addProfessor() {

        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Professor Username: ");
        String username = sc.nextLine();
        System.out.println("Enter Professor name");
        String profName = sc.nextLine();
        System.out.println("Enter Professor Password");
        String profPass = sc.nextLine();
        System.out.println("Enter Professor Department");
        String profDept = sc.nextLine();
        System.out.println("Enter Professor Designation");
        String profDes = sc.nextLine();
        Integer userId = 0;

        int profId= adminOps.addProfessor(username, profName, "professor", profPass, profDept, profDes);
        if (profId >0) {
            System.out.println("Professor added successfully."+ "Professor ID: " + profId);
        } else {
            System.out.println("Professor already exists.");
        }
    }

    private void removeCourse() {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("The list of courses is:");
        adminOps.showAllCourses();
        System.out.println("Enter Course ID to remove: ");
        String courseId = sc.nextLine();
        adminOps.removeCourse(courseId);
    }

    private void addCourse() {
        adminOps.addCourse();
    }


    private void approveStudentRegistration() {
        // TODO Auto-generated method stub

        Scanner sc = new Scanner(System.in);
        System.out.println("Press 1: Approve one Student for Registration ");
        System.out.println("Press 2: Approve all unapproved students for Registration at once ");
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                adminOps.showUnapprovedStudents();
                break;
            case 2:
                adminOps.approveAllUnapprovedStudents();
                break;
        }

    }
}
