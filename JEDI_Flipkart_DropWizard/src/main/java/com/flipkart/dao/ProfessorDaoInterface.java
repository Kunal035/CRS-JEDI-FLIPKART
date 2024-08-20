package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.exception.DuplicateGradeException;
import com.flipkart.bean.*;

import java.util.List;

public interface ProfessorDaoInterface {

    /**
     * Displays all available courses that are not yet assigned to any professor.
     */
    void showAvailableCourses();

    /**
     * Allows a professor to select a course to teach.
     * @param profId The ID of the professor selecting the course.
     */
    void courseSelection(Integer profId);

    /**
     * Displays enrolled students for a given course ID.
     * @param courseID The ID of the course.
     */
    List<Student> viewEnrolledStudents(String courseID);

    /**
     * Displays enrolled students for all courses taught by a given professor.
     * @param profId The ID of the professor.
     */
    List<Student> viewEnrolledStudents(Integer profId);

    /**
     * Checks if a specific course is taught by a given professor.
     * @param professorId The ID of the professor.
     * @param courseId The ID of the course.
     * @return true if the course is taught by the professor, false otherwise.
     */
    boolean isCourseTaughtByProfessor(int professorId, String courseId);

    /**
     * Retrieves a list of student IDs enrolled in a specific course.
     * @param courseId The ID of the course.
     * @return A list of student IDs.
     */
    List<Integer> getStudentsInCourse(String courseId);

    /**
     * Adds a grade for a student in a specific course.
     * @param studentId The ID of the student.
     * @param courseId The ID of the course.
     * @param grade The grade to be added.
     * @return true if the grade was successfully added, false otherwise.
     */
    boolean addGrade(int studentId, String courseId, String grade) throws DuplicateGradeException;

    /**
     * Retrieves a list of courses taught by a specific professor.
     * @param professorId The ID of the professor.
     * @return A list of courses taught by the professor.
     */
    List<Course> getCoursesTaughtByProfessor(int professorId);
}
