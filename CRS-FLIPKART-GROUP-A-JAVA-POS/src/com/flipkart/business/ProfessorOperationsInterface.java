package com.flipkart.business;

import com.flipkart.bean.Course;

import java.util.List;

public interface ProfessorOperationsInterface {

    /**
     * Views the list of students enrolled in the courses taught by the professor.
     *
     * @param profId The ID of the professor.
     */
    void viewEnrolledStudents(Integer profId);

    /**
     * Allows the professor to select or manage the courses they are assigned to teach.
     *
     * @param profId The ID of the professor.
     */
    void courseSelection(Integer profId);

    /**
     * Adds grades for students enrolled in a specific course.
     *
     * @param professorId The ID of the professor adding the grades.
     * @param courseId The ID of the course for which grades are being added.
     * @return true if grades are added successfully, false otherwise.
     */
    boolean addGradesForCourse(int professorId, String courseId);

    /**
     * Retrieves the list of courses that are taught by the professor.
     *
     * @param professorId The ID of the professor.
     * @return A list of Course objects that the professor is teaching.
     */
    List<Course> getCoursesTaughtByProfessor(int professorId);
}
