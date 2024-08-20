package com.flipkart.business;

import java.util.ArrayList;
import java.util.List;
import com.flipkart.bean.*;
import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Payment;
import com.flipkart.bean.Student;
import com.flipkart.dao.studentDaoOps;
import com.flipkart.exception.*;
import com.flipkart.exception.CourseLimitExceededException;
import com.flipkart.exception.CourseNotFoundException;

public class StudentOperations implements StudentOperationsInterface {
    private List<Student> students;
    private studentDaoOps studentDaoOps;

    public StudentOperations() {
        students = new ArrayList<>();
        studentDaoOps = new studentDaoOps();
    }
    @Override
    public List<Student> getStudents() {
        return students;
    }
    @Override
    public int addStudent(String userName, String name, String role, String password, String department) {
        int sId = studentDaoOps.registerNewStudent(userName, password, role, name, department);
        return sId;
    }
    @Override
    public Student findStudentByUsername(String userName) {
        for (Student student : students) {
            if (student.getUserName().equals(userName)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public void registerCourses(int studentId, List<String> primaryCourses, List<String> alternateCourses) throws CourseNotFoundException, CourseLimitExceededException {
        int registeredCount = 0;
        // Try registering for primary courses

        for (String courseId : primaryCourses) {
            boolean success=false;
            try {
                success  = studentDaoOps.registerStudentForCourse(studentId, courseId);
            }
            catch (CourseNotFoundException | CourseLimitExceededException e) {
                e.getMessage();
            }
            if (success) {
                registeredCount++;
            }
            if (registeredCount == 4) {
                break;
            }
        }

        // If not all primary courses were available, try alternate courses
        if (registeredCount < 4) {
            for (String courseId : alternateCourses) {

                boolean success=false;
                try {
                    success  = studentDaoOps.registerStudentForCourse(studentId, courseId);
                    registeredCount++;
                }
                catch (CourseNotFoundException | CourseLimitExceededException e) {
                    e.getMessage();
                }
                if (registeredCount == 4) {
                    break;
                }
            }
        }

        // Output the result
        if (registeredCount >= 4) {
            studentDaoOps.generateBill(studentId);
            System.out.println("Courses successfully registered.");
        } else {
            System.out.println("Unable to register in 4 courses. Registered in " + registeredCount + " courses.");
        }
    }
    @Override
    public void addCourse(int studentId, String courseId) throws CourseNotFoundException, CourseLimitExceededException{
        try {
            // Attempt to add the course and handle possible exceptions
            boolean success = studentDaoOps.registerStudentForCourse(studentId, courseId);

        } catch (CourseLimitExceededException e) {
            // Handle CourseLimitExceededException
            System.out.println("Error: " + e.getMessage());
        } catch (CourseNotFoundException e) {
            // Handle CourseNotFoundException
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            // Handle other unexpected exceptions
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    @Override
    public void dropCourse(int studentId, String courseId) {
        studentDaoOps.removeStudentFromCourse(studentId, courseId);
    }
    @Override
    public Boolean checkPaymentWindow(int StudentID) {
        System.out.println("Payment window status checked.");
        return true;
    }
    @Override
    public void DoPayment(Payment payment) throws InvalidPaymentAmountException {
        System.out.println("Payment processed successfully.");
    }

    @Override
    public List<Course> viewRegisteredCourses(int studentID) {
        List<Course> registeredCourses = studentDaoOps.viewRegisteredCourses(studentID);

//        if (registeredCourses.isEmpty()) {
//            System.out.println("You are not registered for any courses.");
//
//        }
//        else {
//            System.out.println("Registered Courses for student " + studentID + ":");
//            System.out.println("+-----------+--------------------------------+");
//            System.out.printf("| %-9s | %-30s |%n", "Course ID", "Course Name");
//            System.out.println("+-----------+--------------------------------+");
//
//            for (String[] course : registeredCourses) {
//                System.out.printf("| %-9s | %-30s |%n", course[0], course[1]);
//            }
//
//            System.out.println("+-----------+--------------------------------+");
//        }
        return registeredCourses;

    }
    @Override
    public Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getStudentID() == studentId) {
                return student;
            }
        }
        return null;
    }
    @Override
    public void showCourseCatalog() {
        List<Course> courses = studentDaoOps.displayCourseCatalog();

        if (courses.isEmpty()) {
            System.out.println("No courses are currently being offered.");
        } else {
            System.out.println("+-----------+--------------------------------+-------------------------+");
            System.out.printf("| %-9s | %-30s | %-15s |%n", "Course ID", "Course Name", "Professor");
            System.out.println("+-----------+--------------------------------+-------------------------+");

            for (Course course : courses) {
                System.out.printf("| %-9s | %-30s | %-15d |%n", course.getCourseID(), course.getCoursename(), course.getprofessorId());
            }

            System.out.println("+-----------+--------------------------------+-------------------------+");
        }
    }
//    public void showCourseCatalog() {
//        studentDaoOps.displayCourseCatalog();
//    }
    @Override
    public boolean isValidCourseId(String courseId) {
        boolean flag = studentDaoOps.isValidCourseId(courseId);
        return flag;
    }
    @Override
    public boolean isStudentAlreadyRegistered(int studentId) {
        boolean flag = studentDaoOps.isStudentAlreadyRegistered(studentId);
        return flag;
    }
    @Override
    public boolean isAddDropWindowOpen() {
        return studentDaoOps.isAddDropWindowOpen();
    }
    @Override
    public boolean isUsernameTaken(String username) {
        return studentDaoOps.isUsernameTaken(username);
    }
    @Override
    public List<GradeCard> getGradeCard(int studentId) {
        return studentDaoOps.getGradesForStudent(studentId);
    }
    @Override
    public boolean processPayment(int studentId, double amountDue) throws InvalidPaymentAmountException{
        return true;  // Simulate successful payment
    }
    @Override
    public void updatePaymentStatus(int studentId, double amountDue) {
        studentDaoOps.updatePaymentStatus(studentId, amountDue);
    }
    @Override
    public boolean saveCardDetails(int studentId, String cardNumber, String cardExpiry, int cardCVV) {
        return studentDaoOps.saveCardDetails(studentId, cardNumber, cardExpiry, cardCVV);
    }
    @Override
    public boolean areCardDetailsSaved(int studentId) {
        return studentDaoOps.areCardDetailsSaved(studentId);
    }
    @Override
    public double getAmountDue(int studentId) {
        return studentDaoOps.getAmountDue(studentId);
    }
}
