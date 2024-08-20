package com.flipkart.dao;
import com.flipkart.utils.DBUtils;

import java.sql.*;

public class AdminDaoOps implements AdminDaoInterface {

    //    private DBUtils DBUtils = new DBUtils();
    @Override
    public void setAddDropWindow(boolean open) {
        String sql = "UPDATE SystemSettings SET is_add_drop_window_open = ? WHERE id = 1";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, open);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public Integer addProfessor(String username, String professorName, String role, String password, String department, String designation) {

        String usersql = "INSERT INTO user (username, name, role, password) VALUES (?, ?, ?, ?)";
        String profsql = "INSERT INTO professor (professor_id, department, designation) VALUES (?, ?, ?)";
        int pId = 0;
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(usersql, Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setString(1, username);
            pstmt.setString(2, professorName);
            pstmt.setString(3, role);
            pstmt.setString(4, password);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        pId = generatedKeys.getInt(1);

                        // Insert into professor table
                        try (PreparedStatement insertProfessorStatement = conn.prepareStatement(profsql)) {
                            insertProfessorStatement.setInt(1, pId);
                            insertProfessorStatement.setString(2, department);
                            insertProfessorStatement.setString(3, designation);

                            int professorRows = insertProfessorStatement.executeUpdate();
                            if (professorRows > 0) {
                                System.out.println("Professor inserted successfully");
                            } else {
                                System.out.println("Professor insertion failed");
                                return -1;
                            }
                        }
                        return pId;
                    } else {
                        System.out.println("Professor insertion failed, no ID obtained");
                        return -1;
                    }
                }
            } else {
                System.out.println("Professor insertion failed");
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pId;
    }
    @Override
    public boolean printUnapprovedStudents() {
        String sql = "SELECT student_id FROM Student WHERE isApproved = FALSE";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            boolean foundUnapproved = false;

            while (rs.next()) {
                foundUnapproved = true;
                System.out.println(rs.getInt("student_id"));
            }

            if (!foundUnapproved) {
                System.out.println("No unapproved students found.");
            }

            return foundUnapproved;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void approveOneStudent(Integer student_id) {
        String sql = "UPDATE Student SET isApproved = 1 WHERE student_id = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, student_id);  // Use setInt for Integer values
            int affectedRows = pstmt.executeUpdate();

            // If at least one row was updated, the operation was successful
            if (affectedRows > 0) {
                System.out.println("StudentID: " + student_id + " Approved Successfully");
            } else {
                System.out.println("StudentID: " + student_id + " Not Found or Not Updated");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
    @Override
    public void approveAllUnapprovedStudents(Boolean choice) {
        if(choice){
            String sql = "UPDATE Student SET isApproved = 1 WHERE isApproved = 0";
            try (Connection conn = DBUtils.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                int affectedRows = pstmt.executeUpdate();

                // If at least one row was updated, the operation was successful
                if (affectedRows > 0) {
                    System.out.println(affectedRows + " students approved successfully.");
                } else {
                    System.out.println("No unapproved students found.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
        else{
            System.out.println("No student approved as per your choice");
        }
    }

    @Override
    public void addCourse(String course_id, String course_name, Boolean isOffered) {
        String userSql = "INSERT INTO Course (course_id, course_name, professor_id,total_seats,available_seats,is_offered) VALUES (?, ?, null,10,10,?)";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement userPstmt = conn.prepareStatement(userSql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Set parameters          the User table insertion
            userPstmt.setString(1, course_id);
            userPstmt.setString(2, course_name);
            userPstmt.setBoolean(3, isOffered);


            // Execute the User insertion
            int affectedRows = userPstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Course added succesfully\n" + "Course_ID: " + course_id);

            } else {
                System.out.println("Course insertion failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void showAllProfs() {
        String sql = "SELECT p.professor_id, u.username, u.name, p.department " +
                "FROM Professor p " +
                "JOIN User u ON p.professor_id = u.user_id";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int professorId = rs.getInt("professor_id");
                String username = rs.getString("username");
                String name = rs.getString("name");
                String department = rs.getString("department");

                System.out.println("Professor ID: " + professorId + ", Username: " + username + ", Name: " + name + ", Department: " + department);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showAllCourses() {
        String sql = "SELECT course_id FROM Course";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {


            while (rs.next()) {
                System.out.println(rs.getString("course_id")); // Use column name instead of index for clarity
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void removeProf(Integer instructor_id) {
        String sql = "DELETE FROM Professor WHERE professor_id = ?";
        String sql2 = "DELETE FROM User WHERE user_id = ?";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement userPstmt = conn.prepareStatement(sql);
             PreparedStatement Pstmt2 = conn.prepareStatement(sql2)) {

            // Set parameters for the User table insertion
            userPstmt.setInt(1, instructor_id);
            Pstmt2.setInt(1, instructor_id);

            try{Pstmt2.executeUpdate();}
            catch(SQLException e){
                System.out.println("professor is already teaching courses");
            }
            // Execute the User insertion
            int affectedRows = userPstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Professor with ID:" + instructor_id + " Removed Successfully");

            } else {
                System.out.println("Prof not found ");
            }
        } catch (Exception e) {
            System.out.println("Try removing professor from courses first!");
        }
    }
    @Override
    public void removeCourse(String course_id) {
        String sql = "DELETE FROM Course WHERE course_id = ?";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement userPstmt = conn.prepareStatement(sql)) {

            // Set parameters for the User table insertion
            userPstmt.setString(1, course_id);


            // Execute the User insertion
            int affectedRows = userPstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Course with ID:" + course_id + " Removed Successfully");

            } else {
                System.out.println("Course ID is incorrect.. ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void viewApprovedStudents() {
        String sql = "SELECT s.student_id, u.name, s.department " +
                "FROM Student s " +
                "JOIN User u ON s.student_id = u.user_id " +
                "WHERE s.isApproved = true";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                String name = rs.getString("name");
                String department = rs.getString("department");

                System.out.println("Student ID: " + studentId + ", Name: " + name + ", Department: " + department);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isCourseExists(String courseId) {
        String sql = "SELECT 1 FROM Course WHERE course_id = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, courseId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // If there's a result, the course exists
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}