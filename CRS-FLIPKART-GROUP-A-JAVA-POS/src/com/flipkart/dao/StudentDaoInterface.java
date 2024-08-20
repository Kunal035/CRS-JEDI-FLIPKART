package com.flipkart.dao;

import com.flipkart.bean.GradeCard;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import com.flipkart.exception.CourseLimitExceededException;
>>>>>>> e9b59a0 (Added exception and debugged)
=======
import com.flipkart.exception.CourseLimitExceededException;
>>>>>>> dd79919 (Final Push xD)
import com.flipkart.exception.CourseNotFoundException;


import java.util.List;

public interface StudentDaoInterface  {

    /**
     * Registers a new student by inserting their information into the User and Student tables.
     *
     * @param username The username of the new student.
     * @param password The password for the new student's account.
     * @param role The role of the user, should be "Student".
     * @param name The name of the student.
     * @param department The department of the student.
     * @return The generated student ID if the registration is successful, -1 if registration fails.
     */
    int registerNewStudent(String username, String password, String role, String name, String department);

    /**
     * Displays the course catalog, including course IDs, course names, and professor names.
     */
    void displayCourseCatalog();

    /**
     * Generates a bill for a student by inserting a new payment record.
     *
     * @param studentId The ID of the student for whom the bill is generated.
     * @return true if the bill was successfully generated, false otherwise.
     */
    boolean generateBill(int studentId);

    /**
     * Registers a student for a specific course if seats are available.
     *
     * @param studentId The ID of the student.
     * @param courseId The ID of the course to register for.
     * @return true if the registration is successful, false otherwise.
     */
<<<<<<< HEAD
<<<<<<< HEAD
    boolean registerStudentForCourse(int studentId, String courseId) throws CourseNotFoundException;
=======
    boolean registerStudentForCourse(int studentId, String courseId) throws CourseNotFoundException, CourseLimitExceededException;
>>>>>>> e9b59a0 (Added exception and debugged)
=======
    boolean registerStudentForCourse(int studentId, String courseId) throws CourseNotFoundException, CourseLimitExceededException;
>>>>>>> dd79919 (Final Push xD)

    /**
     * Removes a student from a specific course.
     *
     * @param studentId The ID of the student.
     * @param courseId The ID of the course to remove from.
     * @return true if the removal is successful, false otherwise.
     */
    boolean removeStudentFromCourse(int studentId, String courseId) ;

    /**
     * Checks if a given course ID is valid and exists in the system.
     *
     * @param courseId The ID of the course to check.
     * @return true if the course ID is valid, false otherwise.
     */
    boolean isValidCourseId(String courseId);

    /**
     * Checks if a student is already registered in any course.
     *
     * @param studentId The ID of the student to check.
     * @return true if the student is registered in any course, false otherwise.
     */
    boolean isStudentAlreadyRegistered(int studentId);

    /**
     * Views all courses that a student is currently registered in.
     *
     * @param studentID The ID of the student whose courses to view.
     */
    void viewRegisteredCourses(int studentID);

    /**
     * Checks if the add/drop window for course registrations is open.
     *
     * @return true if the add/drop window is open, false otherwise.
     */
    boolean isAddDropWindowOpen();

    /**
     * Checks if a given username is already taken by another user.
     *
     * @param username The username to check.
     * @return true if the username is already taken, false otherwise.
     */
    boolean isUsernameTaken(String username);

    /**
     * Retrieves the grades for a student across all courses they are enrolled in.
     *
     * @param studentId The ID of the student whose grades to retrieve.
     * @return A list of GradeCard objects representing the student's grades.
     */
    List<GradeCard> getGradesForStudent(int studentId);

    /**
     * Updates the payment status for a student.
     *
     * @param studentId The ID of the student whose payment status to update.
     * @param amountDue The amount due for the student.
     */
    void updatePaymentStatus(int studentId, double amountDue);

    /**
     * Saves the card details for a student.
     *
     * @param studentId The ID of the student.
     * @param cardNumber The card number.
     * @param cardExpiry The card expiry date in YYYY-MM-DD format.
     * @param cardCVV The card CVV.
     * @return true if the card details were successfully saved, false otherwise.
     */
    boolean saveCardDetails(int studentId, String cardNumber, String cardExpiry, int cardCVV);

    /**
     * Checks if card details are already saved for a student.
     *
     * @param studentId The ID of the student to check.
     * @return true if card details are saved, false otherwise.
     */
    boolean areCardDetailsSaved(int studentId);

    /**
     * Retrieves the amount due for a student.
     *
     * @param studentId The ID of the student whose amount due to retrieve.
     * @return The amount due for the student.
     */
    double getAmountDue(int studentId);
}
