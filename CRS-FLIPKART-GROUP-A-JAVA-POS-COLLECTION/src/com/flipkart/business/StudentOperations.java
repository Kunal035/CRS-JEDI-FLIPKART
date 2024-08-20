package com.flipkart.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Payment;
import com.flipkart.bean.Student;

public class StudentOperations {
	private List<Student> students;
	private AdminOperations adminOps;

	public StudentOperations(){
		students = new ArrayList<>();
		adminOps= new AdminOperations();
		ArrayList<String> courses = new ArrayList<>();
		courses.add("C101");
		courses.add("C102");
		students.add(new Student("nikhil1", "nikhil", "student", "pass", 102, "CS", courses));
		students.add(new Student("ajey1","ajey","student","pass",101,"CS",null));
		students.add(new Student("kunal1","kunal","student","pass",103,"CS",null));
	}

	public List<Student> getStudents() {
		return students;
	}

	public boolean addStudent(String userName, String name, String role, String password,Integer studentID, String department) {
		if(findStudentByUsername(userName)==null){
			students.add(new Student(userName,name,role,password,studentID,department,null));
			return true;
		}
		return false;
	}
	public Student findStudentByUsername(String userName){
		for (Student student : students) {
			if(student.getUserName().equals(userName)){
				return student;
			}
		}
		return null;
	}

	//	public void registerCourses(int studentId, String courseId) {
//		Student student = findStudentById(studentId);
//		List<Course> availableCourses = adminOps.getCourseCatalogue();
//		if (student != null) {
//			for (Course course : availableCourses) {
//				if (course.getCourseID().equals(courseId) && course.isOffered() && course.getAvailableSeats() > 0) {
//					student.getRegisteredCourses().add(courseId);
//					course.setAvailableSeats(course.getAvailableSeats() - 1);
//					System.out.println("Course registered successfully.");
//					return;
//				}
//			}
//			System.out.println("Course not found.");
//		} else {
//			System.out.println("Student not found.");
//		}
//
//	}
	public void registerCourses(int studentId, String courseId) {
		Student student = findStudentById(studentId);
		Course course = findCourseById(courseId);
		if (student != null && course != null) {
			if (course.isOffered() && course.getAvailableSeats() > 0) {
				student.getRegisteredCourses().add(courseId);
				course.addStudent(studentId); // Add student to course
				System.out.println("Course registered successfully.");
			} else {
				System.out.println("Course not available.");
			}
		} else {
			System.out.println("Student or Course not found.");
		}
	}
	//	public boolean addCourse(int studentId, String courseId) {
//		Student student = findStudentById(studentId);
//		List<Course> availableCourses = adminOps.getCourseCatalogue();
//		if (student != null) {
//			for (Course course : availableCourses) {
//				if (course.getCourseID().equals(courseId) && course.isOffered() && course.getAvailableSeats() > 0) {
//					student.getRegisteredCourses().add(courseId);
//					course.setAvailableSeats(course.getAvailableSeats() - 1);
//					System.out.println("Course added successfully.");
//					return true;
//				}
//			}
//			System.out.println("Course not found.");
//		} else {
//			System.out.println("Student not found.");
//		}
//		return false;
//	}
	public void addCourse(int studentId, String courseId) {
		Student student = findStudentById(studentId);
		Course course = findCourseById(courseId);

		if (student != null && course != null) {
			if (course.isOffered() && course.getAvailableSeats() > 0) {
				if (!student.getRegisteredCourses().contains(courseId)) {
					student.getRegisteredCourses().add(courseId);
					course.addStudent(studentId); // Add student to course
					System.out.println("Course added successfully.");
				} else {
					System.out.println("Student is already registered for this course.");
				}
			} else {
				System.out.println("Course not available or no seats left.");
			}
		} else {
			System.out.println("Student or Course not found.");
		}
	}

	//	public boolean dropCourse(int studentId, String courseId) {
//		Student student = findStudentById(studentId);
//
//		if (student != null) {
//			System.out.println(student.getUserName());
//			for (String it: student.getRegisteredCourses()) {
//				if (it.equals(courseId)) {
//					student.getRegisteredCourses().remove(it);
//					Course courseObj = findCourseById(courseId);
//					if (courseObj != null) {
//						courseObj.setAvailableSeats(courseObj.getAvailableSeats() + 1);
//						System.out.println("Course dropped successfully.");
//						return true;
//					}
//				}
//			}
//			System.out.println("Course not found in Student list.");
//		} else {
//			System.out.println("Student not found.");
//		}
//		return false;
//	}
	public void dropCourse(int studentId, String courseId) {
		Student student = findStudentById(studentId);
		Course course = findCourseById(courseId);

		if (student != null && course != null) {
			if (student.getRegisteredCourses().remove(courseId)) {
				course.removeStudent(studentId); // Remove student from course
				System.out.println("Course dropped successfully.");
			} else {
				System.out.println("Course not found in student's list.");
			}
		} else {
			System.out.println("Student or Course not found.");
		}
	}

	public boolean finishRegistration() {
		System.out.println("Registration complete.");
		return true;
	}
	public List<Course> viewAvailableCourses(){
		List<Course> availableCourses = adminOps.getCourseCatalogue();
		List<Course> newAvailableCourses = new ArrayList<>();
		for (Course course : availableCourses) {
			if (course.isOffered() && course.getAvailableSeats() > 0) {
				newAvailableCourses.add(course);
			}
		}
		return newAvailableCourses;
	}
	public void viewReportCard(int studentID, int semesterId) {
//		Student student = findStudentById(studentID);
//		if (student != null) {
//			return student.getGradeCard();
//		}
//		System.out.println("Student not found.");
//		return null;
		return;
	}
	public Boolean checkPaymentWindow(int StudentID) {
		System.out.println("Payment window status checked.");
		return true;
	}
	public void DoPayment(Payment payment) {
		System.out.println("Payment processed successfully.");
	}
	public void viewRegisteredCourses(int studentID) {
		Student student = findStudentById(studentID);
		if (student != null) {
			System.out.println("Registered Courses for student " + studentID + ":");
			for (String course : student.getRegisteredCourses()) {
				System.out.println(course);
				//add course name too (pair bnana pdega in student class)
			}
		} else {
			System.out.println("Student not found.");
		}
	}
	public Student findStudentById(int studentId) {
		for (Student student : students) {
			if (student.getStudentID() == studentId) {
				return student;
			}
		}
		return null;
	}
	public Integer getStudentIdByUsername(String username) {
		for (Student student : students) {
			if (student.getUserName().equals(username)) {
				return student.getStudentID();
			}
		}
		// Return a special value or throw an exception if username is not found
		return -1; // Indicating that the username was not found
	}
	public Course findCourseById(String courseId) {
		List<Course> availableCourses = adminOps.getCourseCatalogue();
		for (Course course : availableCourses) {
			if (course.getCourseID().equals(courseId)) {
				return course;
			}
		}
		return null;
	}
	public void viewStudents() {
		for (Student student : students) {
			System.out.println(student.getStudentID()+" "+student.getDepartment()+" "+student.getName()+" "+student.getUserName()+" "+ student.getPassword());
		}
	}
}
