package com.flipkart.restcontroller;

import com.flipkart.business.StudentOperations;
import com.flipkart.bean.GradeCard;
import com.flipkart.exception.CourseLimitExceededException;
import com.flipkart.exception.InvalidPaymentAmountException;
import com.flipkart.bean.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
public class StudentController {

	private StudentOperations studentOperations = new StudentOperations();



	@POST
	@Path("/registerCourses")
	public Response registerCourses(
			@QueryParam("studentId") int studentId,
			@QueryParam("primaryCourses") List<String> primaryCourses,
			@QueryParam("alternateCourses") List<String> alternateCourses) {
		try {
			studentOperations.registerCourses(studentId, primaryCourses, alternateCourses);
			return Response.ok().entity("Courses registered successfully").build();
		} catch (CourseLimitExceededException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to register courses").build();
		}
	}

	@POST
	@Path("/addCourse")
	public Response addCourse(
			@QueryParam("studentId") int studentId,
			@QueryParam("courseId") String courseId) {
		try {
			studentOperations.addCourse(studentId, courseId);
			return Response.ok().entity("Course added successfully").build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Failed to add course").build();
		}
	}

	@POST
	@Path("/dropCourse")
	public Response dropCourse(
			@QueryParam("studentId") int studentId,
			@QueryParam("courseId") String courseId) {
		try {
			studentOperations.dropCourse(studentId, courseId);
			return Response.ok().entity("Course dropped successfully").build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Failed to drop course").build();
		}
	}

	@GET
	@Path("/viewRegisteredCourses")
	public Response viewRegisteredCourses(@QueryParam("studentId") int studentId) {
		List<Course> registeredCourses = studentOperations.viewRegisteredCourses(studentId);
		return Response.ok().entity(registeredCourses).build();
	}

	@GET
	@Path("/viewReportCard")
	public Response viewReportCard(@QueryParam("studentId") int studentId) {
		List<GradeCard> gradeCards = studentOperations.getGradeCard(studentId);
		return Response.ok().entity(gradeCards).build();
	}

	@POST
	@Path("/doPayment")
	public Response doPayment(
			@QueryParam("studentId") int studentId,
			@QueryParam("amount") double amount) {
		try {
			studentOperations.processPayment(studentId, amount);
			return Response.ok().entity("Payment processed successfully").build();
		} catch (InvalidPaymentAmountException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to process payment").build();
		}
	}

	@POST
	@Path("/saveCardDetails")
	public Response saveCardDetails(
			@QueryParam("studentId") int studentId,
			@QueryParam("cardNumber") String cardNumber,
			@QueryParam("cardExpiry") String cardExpiry,
			@QueryParam("cardCVV") int cardCVV) {
		if (studentOperations.saveCardDetails(studentId, cardNumber, cardExpiry, cardCVV)) {
			return Response.ok().entity("Card details saved successfully").build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity("Failed to save card details").build();
		}
	}
}
