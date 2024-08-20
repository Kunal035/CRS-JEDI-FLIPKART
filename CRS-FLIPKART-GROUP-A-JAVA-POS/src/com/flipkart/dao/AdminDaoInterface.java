package com.flipkart.dao;

public interface AdminDaoInterface {

    /**
     * Sets the add/drop window status in the system settings.
     * @param open true if the window should be opened, false otherwise.
     */
    void setAddDropWindow(boolean open);

    /**
     * Adds a new professor to the system.
     * @param username The username of the professor.
     * @param professorName The name of the professor.
     * @param role The role of the professor.
     * @param password The password for the professor's account.
     * @param department The department of the professor.
     * @param designation The designation of the professor.
     * @return The ID of the newly added professor, or -1 if the operation failed.
     */
    Integer addProfessor(String username, String professorName, String role, String password, String department, String designation);

    /**
     * Prints the IDs of all unapproved students.
     * @return true if there are unapproved students, false otherwise.
     */
    boolean printUnapprovedStudents();

    /**
     * Approves a student by their ID.
     * @param student_id The ID of the student to approve.
     */
    void approveOneStudent(Integer student_id);

    /**
     * Approves all unapproved students if the given choice is true.
     * @param choice If true, all unapproved students are approved.
     */
    void approveAllUnapprovedStudents(Boolean choice);

    /**
     * Adds a new course to the system.
     * @param course_id The ID of the course.
     * @param course_name The name of the course.
     * @param isOffered Whether the course is offered or not.
     */
    void addCourse(String course_id, String course_name, Boolean isOffered);

    /**
     * Displays all professors in the system.
     */
    void showAllProfs();

    /**
     * Displays all courses in the system.
     */
    void showAllCourses();

    /**
     * Removes a professor by their ID.
     * @param instructor_id The ID of the professor to remove.
     */
    void removeProf(Integer instructor_id);

    /**
     * Removes a course by its ID.
     * @param course_id The ID of the course to remove.
     */
    void removeCourse(String course_id);

    /**
     * Displays all approved students in the system.
     */
    void viewApprovedStudents();

    /**
     * Checks if a course exists by its ID.
     * @param courseId The ID of the course to check.
     * @return true if the course exists, false otherwise.
     */
    boolean isCourseExists(String courseId);
}
