package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.exception.DuplicateGradeException;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Scanner;
import java.util.*;

public class ProfessorDaoOps implements ProfessorDaoInterface{
    @Override
    public void showAvailableCourses() {
        String sql = "SELECT * FROM course WHERE professor_id IS NULL AND is_offered = true";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Table header with borders
            System.out.println("+-----------+--------------------------------+");
            System.out.printf("| %-9s | %-30s |%n", "Course ID", "Course Name");
            System.out.println("+-----------+--------------------------------+");

            // Print each row of the result set with borders
            while (rs.next()) {
                String courseId = rs.getString("course_id");
                String courseName = rs.getString("course_name");

                // Print course_id and course_name in table format
                System.out.printf("| %-9s | %-30s |%n", courseId, courseName);
            }

            // Table footer
            System.out.println("+-----------+--------------------------------+");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void courseSelection(Integer profId) {
        showAvailableCourses();

        // Prompt the professor to select a course by entering the Course ID
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Course ID to select the course to teach: ");
        String selectedCourseId = scanner.nextLine(); // Read as String

        // Update the course table to assign the professor to the selected course
        String updateSql = "UPDATE course SET professor_id = ? WHERE course_id = ? AND professor_id IS NULL AND is_offered = true";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement updatePstmt = conn.prepareStatement(updateSql)) {

            // Set the professor_id and course_id in the prepared statement
            updatePstmt.setInt(1, profId);
            updatePstmt.setString(2, selectedCourseId); // Use String for course_id

            // Execute the update
            int rowsAffected = updatePstmt.executeUpdate();

            // Check if the update was successful
            if (rowsAffected > 0) {
                System.out.println("Course successfully assigned to the professor.");
            } else {
                System.out.println("Failed to assign the course. Please check the Course ID.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Method to get enrolled students for a given course
    @Override
    public void viewEnrolledStudents(String courseID) {
        String sql = "SELECT ce.student_id, u.name " +
                "FROM CourseEnrollment ce " +
                "JOIN user u ON ce.student_id = u.user_id " +
                "WHERE ce.course_id = ?";
        ;

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the course_id in the prepared statement
            pstmt.setString(1, courseID);

            try (ResultSet rs = pstmt.executeQuery()) {

                System.out.println("Enrolled Students for Course ID: " + courseID);
                System.out.println("+------------+----------------------+");
                System.out.printf("| %-10s | %-20s |%n", "Student ID", "Student Name");
                System.out.println("+------------+----------------------+");

                while (rs.next()) {
                    String studentId = rs.getString("student_id");
                    String studentName = rs.getString("name");
                    System.out.printf("| %-10s | %-20s |%n", studentId, studentName);
                }

                System.out.println("+------------+----------------------+");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void viewEnrolledStudents(Integer profId) {

        // First, get the list of courses taught by the professor
        String coursesSql = "SELECT course_id FROM course WHERE professor_id = ?";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(coursesSql)) {

            // Set the professor_id in the prepared statement
            pstmt.setInt(1, profId);

            try (ResultSet rs = pstmt.executeQuery()) {

                boolean hasCourses = false;

                // Iterate through the courses taught by the professor
                while (rs.next()) {
                    hasCourses = true;
                    String courseId = rs.getString("course_id");

                    // Call the method to view enrolled students for each course
                    viewEnrolledStudents(courseId);
                    System.out.println(); // Print a newline for better readability
                }

                if (!hasCourses) {
                    System.out.println("The professor is not teaching any courses.");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @Override
    public boolean isCourseTaughtByProfessor(int professorId, String courseId) {
        String sql = "SELECT COUNT(*) FROM Course WHERE course_id = ? AND professor_id = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, courseId);
            pstmt.setInt(2, professorId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public List<Integer> getStudentsInCourse(String courseId) {
        String sql = "SELECT student_id FROM CourseEnrollment WHERE course_id = ?";
        List<Integer> studentIds = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                studentIds.add(rs.getInt("student_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentIds;
    }
    @Override
    public boolean addGrade(int studentId, String courseId, String grade) throws DuplicateGradeException {
        String sql = "INSERT INTO GradeCard (student_id, course_id, grade) VALUES (?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setString(2, courseId);
            pstmt.setString(3, grade);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLIntegrityConstraintViolationException e) {
            // Throw custom exception for duplicate entry
            throw new DuplicateGradeException("Duplicate entry for course ID " + courseId + " and student ID " + studentId);
        }
            catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public List<Course> getCoursesTaughtByProfessor(int professorId) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT course_id, course_name FROM Course WHERE professor_id = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, professorId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String courseId = rs.getString("course_id");
                    String courseName = rs.getString("course_name");
                    courses.add(new Course(courseId, courseName)); // Assuming Course class has a constructor
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return courses;
    }
}