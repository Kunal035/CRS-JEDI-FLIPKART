package com.flipkart.business;

import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Payment;
import com.flipkart.bean.Student;

import java.util.List;

public interface StudentOperationsInterface {

    /**
     * Retrieves the list of all students.
     *
     * @return A list of Student objects.
     */
    List<Student> getStudents();

    /**
     * Adds a new student to the system.
     *
     * @param userName The username of the new student.
     * @param name The name of the new student.
     * @param role The role of the new student.
     * @param password The password for the new student.
     * @param department The department of the new student.
     * @return The ID of the newly added student.
     */
    int addStudent(String userName, String name, String role, String password, String department);

    /**
     * Finds a student by their username.
     *
     * @param userName The username of the student.
     * @return The Student object if found, otherwise null.
     */
    Student findStudentByUsername(String userName);

    /**
     * Registers courses for a student.
     *
     * @param studentId The ID of the student.
     * @param primaryCourses A list of primary course IDs.
     * @param alternateCourses A list of alternate course IDs.
     */
    void registerCourses(int studentId, List<String> primaryCourses, List<String> alternateCourses);

    /**
     * Adds a single course for a student.
     *
     * @param studentId The ID of the student.
     * @param courseId The ID of the course to add.
     */
    void addCourse(int studentId, String courseId);

    /**
     * Drops a single course for a student.
     *
     * @param studentId The ID of the student.
     * @param courseId The ID of the course to drop.
     */
    void dropCourse(int studentId, String courseId);

    /**
     * Checks if the payment window is currently open.
     *
     * @param studentID The ID of the student.
     * @return true if the payment window is open, false otherwise.
     */
    Boolean checkPaymentWindow(int studentID);

    /**
     * Processes a payment for a student.
     *
     * @param payment The Payment object containing payment details.
     */
    void DoPayment(Payment payment);

    /**
     * Views the list of courses registered by a student.
     *
     * @param studentID The ID of the student.
     */
    void viewRegisteredCourses(int studentID);

    /**
     * Finds a student by their ID.
     *
     * @param studentId The ID of the student.
     * @return The Student object if found, otherwise null.
     */
    Student findStudentById(int studentId);

    /**
     * Shows the course catalog.
     */
    void showCourseCatalog();

    /**
     * Checks if a course ID is valid.
     *
     * @param courseId The ID of the course to check.
     * @return true if the course ID is valid, false otherwise.
     */
    boolean isValidCourseId(String courseId);

    /**
     * Checks if a student is already registered in any course.
     *
     * @param studentId The ID of the student.
     * @return true if the student is already registered, false otherwise.
     */
    boolean isStudentAlreadyRegistered(int studentId);

    /**
     * Checks if the add/drop window is currently open.
     *
     * @return true if the add/drop window is open, false otherwise.
     */
    boolean isAddDropWindowOpen();

    /**
     * Checks if the username is already taken.
     *
     * @param username The username to check.
     * @return true if the username is already taken, false otherwise.
     */
    boolean isUsernameTaken(String username);

    /**
     * Retrieves the grade card for a student.
     *
     * @param studentId The ID of the student.
     * @return A list of GradeCard objects for the student.
     */
    List<GradeCard> getGradeCard(int studentId);

    /**
     * Processes payment for a student.
     *
     * @param studentId The ID of the student.
     * @param amountDue The amount due for payment.
     * @return true if the payment is successful, false otherwise.
     */
    boolean processPayment(int studentId, double amountDue);

    /**
     * Updates the payment status for a student.
     *
     * @param studentId The ID of the student.
     * @param amountDue The amount due that was paid.
     */
    void updatePaymentStatus(int studentId, double amountDue);

    /**
     * Saves card details for a student.
     *
     * @param studentId The ID of the student.
     * @param cardNumber The card number.
     * @param cardExpiry The card expiry date.
     * @param cardCVV The card CVV.
     * @return true if the card details are saved successfully, false otherwise.
     */
    boolean saveCardDetails(int studentId, String cardNumber, String cardExpiry, int cardCVV);

    /**
     * Checks if card details are already saved for a student.
     *
     * @param studentId The ID of the student.
     * @return true if card details are saved, false otherwise.
     */
    boolean areCardDetailsSaved(int studentId);

    /**
     * Retrieves the amount due for a student.
     *
     * @param studentId The ID of the student.
     * @return The amount due for the student.
     */
    double getAmountDue(int studentId);
}
