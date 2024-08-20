package com.flipkart.restcontroller;

import com.flipkart.bean.*;
import com.flipkart.bean.Student;
import com.flipkart.bean.Course;
import com.flipkart.business.ProfessorOperations;
import io.dropwizard.jersey.params.LongParam;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/professor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfessorController {

	private ProfessorOperations professorOps = new ProfessorOperations();



	@GET
	@Path("/viewEnrolledStudents")
	public Response viewEnrolledStudents(@QueryParam("courseID") String courseID) {
		try {
			List<Student> students = professorOps.viewEnrolledStudents(courseID);
			return Response.ok(students).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/addGrade")
	public Response addGrade(@QueryParam("professorId") Integer professorId,
							 @QueryParam("courseId") String courseId) {
		try {
			boolean success = professorOps.addGradesForCourse(professorId, courseId);
			if (success) {
				return Response.ok("Grades successfully added for all students in course " + courseId).build();
			} else {
				return Response.status(Response.Status.BAD_REQUEST).entity("Failed to add grades. Please check the course ID and try again.").build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/courseSelection")
	public Response courseSelection(@QueryParam("professorId") Integer professorId) {
		try {
			professorOps.courseSelection(professorId);
			return Response.ok("Course selection successful").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/coursesTaught")
	public Response getCoursesTaughtByProfessor(@QueryParam("professorId") Integer professorId) {
		try {
			List<Course> courses = professorOps.getCoursesTaughtByProfessor(professorId);
			return Response.ok(courses).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
}

