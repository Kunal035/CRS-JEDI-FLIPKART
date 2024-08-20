package com.flipkart.business;

import com.flipkart.bean.Admin;

public interface AdminOperationsInterface {

    /**
     * Finds and returns an Admin object based on the provided username.
     *
     * @param userName The username of the admin to be found.
     * @return The Admin object if found, or null if no such admin exists.
     */
    Admin findAdminByUsername(String userName);

    /**
     * Adds a new course to the system. Prompts the admin for necessary course details.
     */
    void addCourse(String courseName, String courseId, boolean active);

    /**
     * Displays a list of all professors.
     */
    void showAllProfs();

    /**
     * Adds a new professor to the system.
     *
     * @param username The username for the new professor.
     * @param professorName The name of the professor.
     * @param role The role of the professor (e.g., Professor, Assistant Professor).
     * @param password The password for the professor's account.
     * @param department The department where the professor will be assigned.
     * @param designation The designation of the professor (e.g., Associate Professor).
     * @return The ID of the newly added professor.
     */
    Integer addProfessor(String username, String professorName, String role, String password, String department, String designation);

    /**
     * Removes a professor from the system based on their ID.
     *
     * @param professorID The ID of the professor to be removed.
     */
    void removeProfessor(Integer professorID);

    /**
     * Sends a notification to all students regarding fee payment.
     */
    void sendFeePayNotification();

    /**
     * Displays a list of students who have been approved.
     */
    void viewApprovedStudents();

    /**
     * Displays a list of students who are not yet approved.
     */
    void showUnapprovedStudents();

    /**
     * Displays a list of all courses available in the system.
     */
    void showAllCourses();

    /**
     * Removes a course from the system based on the course ID.
     *
     * @param course_id The ID of the course to be removed.
     */
    void removeCourse(String course_id);

    /**
     * Sets the status of the add/drop window for course registrations.
     *
     * @param open true to open the add/drop window, false to close it.
     */
    void setAddDropWindow(boolean open);

    /**
     * Approves all students who are currently pending approval.
     */
    void approveAllUnapprovedStudents();
}
