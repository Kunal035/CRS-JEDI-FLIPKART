/**
 *
 */
package com.flipkart.client;

import com.flipkart.bean.*;
import com.flipkart.business.AdminOperations;
import com.flipkart.business.ProfessorOperations;
import com.flipkart.business.StudentOperations;
import com.flipkart.business.UserOperations;
import com.flipkart.exception.InvalidCredentialsException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
//import com.flipkart.dao.UserDaoOps;

import java.util.Scanner;

/**
 *
 */
public class CRSApplication {
    private StudentOperations studentOps;
    private ProfessorOperations profOps;
    private AdminOperations adminOps;
    private Scanner sc;
    private UserOperations userOps;

    public CRSApplication() {

        studentOps = new StudentOperations();
        profOps = new ProfessorOperations();
        adminOps = new AdminOperations();
        userOps = new UserOperations();
        sc = new Scanner(System.in);
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
            System.out.println("Press 2: Register Student");
            System.out.println("Press 3: Update Password");
            System.out.println("Press 4: Exit");
            System.out.println("*********************************************************");
            int menuOption = sc.nextInt();
            sc.nextLine();
            switch (menuOption) {
                case 1:
                    login();
                    break;
                case 2:
                    registerNewStudent();
                    break;
                case 3:
                    updatePassword();
                    break;
                case 4:
                    sc.close();
                    System.out.println("Exited Successfully!");
                    return;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    private void login() {
        User user = new User();
        System.out.println("********************************");
        System.out.println("Enter your Username: ");
        String username = sc.nextLine();
        System.out.println("Enter your Password: ");
        String password = sc.nextLine();

        try {
            // Validate credentials and throw an exception if invalid
            userOps.validateCredentials(username, password);

            user.setUserName(username);
            user.setPassword(password);
            userOps.getRolebyLogin(user);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            switch (user.getRole()) {
                case "Student":
                    Student stud = new Student();
                    stud.setUserName(user.getUserName());
                    stud.setPassword(user.getPassword());
                    stud.setRole("Student");
                    stud.setStudentID(user.getUserId());
                    if (userOps.isApproved(stud.getUserName())) {
                        System.out.println("********************************");
                        System.out.println("Logged In Successfully as a Student");
                        System.out.println("Welcome " + stud.getUserName() + " !!");
                        System.out.println("Login Time: " + dtf.format(now));
                        CRSStudentMenu studCrs = new CRSStudentMenu();
                        studCrs.CreateStudentMenu(stud.getStudentID());
                    } else {
                        System.out.println("You have not been approved");
                    }
                    break;

                case "Professor":
                    Professor prof = new Professor();
                    prof.setUserName(user.getUserName());
                    prof.setPassword(user.getPassword());
                    prof.setRole("Professor");
                    prof.setProfessorID(user.getUserId());

                    System.out.println("********************************");
                    System.out.println("Logged In Successfully as a Professor");
                    System.out.println("Welcome " + prof.getUserName() + " !!");
                    System.out.println("Login Time: " + dtf.format(now));
                    CRSProfessorMenu profCrs = new CRSProfessorMenu();
                    profCrs.CreateProfessorMenu(prof.getProfessorId());
                    break;

                case "Admin":
                    Admin admin = new Admin();
                    admin.setUserId(user.getUserId());
                    admin.setUserName(user.getUserName());

                    System.out.println("********************************");
                    System.out.println("Logged In Successfully as an Admin");
                    System.out.println("Welcome " + admin.getUserName() + " !!");
                    System.out.println("Login Time: " + dtf.format(now));
                    CRSAdminMenu admCrs = new CRSAdminMenu();
                    admCrs.CreateAdminMenu(admin.getUserId());
                    break;

                default:
                    System.out.println("Invalid Role");
                    System.out.println("********************************");
                    break;
            }
        } catch (InvalidCredentialsException e) {
            System.out.println(e.getMessage());
        }
    }


    void registerNewStudent() {
        String username;

        // Loop until a unique username is entered
        while (true) {
            System.out.println("Enter Username:");
            username = sc.nextLine();

            // Check if the username already exists in the user table
            if (studentOps.isUsernameTaken(username)) {
                System.out.println("Username already exists. Please enter a different username.");
            } else {
                break; // Exit the loop if the username is unique
            }
        }

        System.out.println("enter Password");
        String password = sc.nextLine();
        System.out.println("enter name");
        String name = sc.nextLine();
        System.out.println("enter Department");
        String department = sc.nextLine();

        int sId = studentOps.addStudent(username, name, "Student", password, department);
        if (sId > 0) {
            System.out.println("Congratulations!! " + username + "\nYou have been added successfully \nYour Student Id is : " + sId);
        } else {
            System.out.println("Registration Failed");
        }
    }

    void updatePassword() {
        System.out.println("IN update Password Menu");

        System.out.println("Enter your Username: ");
        String username = sc.nextLine();
        System.out.println("Enter your current Password: ");
        String password = sc.nextLine();

        Boolean result = userOps.checkCredentials(username, password);
        if (result) {
            System.out.println("Enter your New Password: ");
            String newPassword = sc.nextLine();

            if (userOps.updatePassword(username, newPassword)) {
                System.out.println("Password updated successfully");
            } else {
                System.out.println("Failed to update password");
            }
        } else {
            System.out.println("Invalid Credentials");
        }
    }

}

