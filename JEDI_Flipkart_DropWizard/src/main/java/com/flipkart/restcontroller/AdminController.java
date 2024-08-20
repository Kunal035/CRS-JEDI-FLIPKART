package com.flipkart.restcontroller;

import com.flipkart.business.AdminOperations;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Course;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	private AdminOperations adminOps= new AdminOperations();

	@POST
	@Path("/approveStudentRegistration")
	public Response approveStudentRegistration(@QueryParam("choice") int choice) {
		try {
			if (choice == 1) {
				adminOps.showUnapprovedStudents();
			} else if (choice == 2) {
				adminOps.approveAllUnapprovedStudents();
			} else {
				return Response.status(Response.Status.BAD_REQUEST).entity("Invalid choice").build();
			}
			return Response.ok("Student(s) approved successfully").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}


	@POST
	@Path("/addProfessor")
	public Response addProfessor(Professor professor) {
		try {
			int profId = adminOps.addProfessor(professor.getUserName(), professor.getName(), "professor", professor.getPassword(), professor.getDepartment(), professor.getDesignation());
			if (profId > 0) {
				return Response.ok("Professor added successfully. Professor ID: " + profId).build();
			} else {
				return Response.status(Response.Status.CONFLICT).entity("Professor already exists").build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/addCourse")
	public Response addCourse(@QueryParam("course_name") String courseName,
							  @QueryParam("courseID") String courseId,
							  @QueryParam("isOffered") boolean isOffered) {

		try{

			adminOps.addCourse(courseName, courseId, isOffered);

		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
		return Response.status(201).entity("Course Added sucessfully!!! ").build();
	}

	@DELETE
	@Path("/removeProfessor")
	public Response removeProfessor(@QueryParam("professorId") int professorId) {
		try {
			adminOps.removeProfessor(professorId);
			return Response.ok("Professor removed successfully").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/removeCourse")
	public Response removeCourse(@QueryParam("courseId") String courseId) {
		try {
			adminOps.removeCourse(courseId);
			return Response.ok("Course removed successfully").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/viewApprovedStudents")
	public Response viewApprovedStudents() {
		try {
			// Call the void method (you might need to adjust how you handle this in real implementation)
			adminOps.viewApprovedStudents();

			// Since the method is void, you need a way to fetch or show results, e.g., from a static field, or logs
			// Assuming the results are somehow available, otherwise, you may need to refactor AdminOperations

			// Example placeholder response
			return Response.ok("Results have been processed").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	@POST
	@Path("/sendFeePayNotification")
	public Response sendFeePayNotification(@QueryParam("adminId") int adminId) {
		try {
			adminOps.sendFeePayNotification();
			return Response.ok("Fee payment notifications sent").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/addDropWindow")
	public Response addDropWindow(@QueryParam("choice") String choice) {
		try {
			switch (choice.toLowerCase()) {
				case "enable":
					adminOps.setAddDropWindow(true);
					return Response.ok("The add/drop course window has been enabled.").build();
				case "disable":
					adminOps.setAddDropWindow(false);
					return Response.ok("The add/drop course window has been disabled.").build();
				default:
					return Response.status(Response.Status.BAD_REQUEST).entity("Invalid choice. Please enter 'enable' or 'disable'.").build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
}
