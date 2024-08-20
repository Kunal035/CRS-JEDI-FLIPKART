package com.flipkart.restcontroller;

import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.business.StudentOperations;
import com.flipkart.business.UserOperations;
import com.flipkart.exception.InvalidCredentialsException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
	private UserOperations userOps;
	private StudentOperations studOps;

	public UserController() {
		
	}

	@POST
	@Path("/login")
	public Response login(User user) {
		this.userOps = new UserOperations();
		try {
			userOps.validateCredentials(user.getUserName(), user.getPassword());
			userOps.getRolebyLogin(user);

			if ("Student".equals(user.getRole())) {
				if (userOps.isApproved(user.getUserName())) {
					return Response.ok("Logged In Successfully as a Student. Welcome " + user.getUserName() + " !!").build();
				} else {
					return Response.status(Response.Status.UNAUTHORIZED).entity("You have not been approved").build();
				}
			} else if ("Professor".equals(user.getRole())) {
				return Response.ok("Logged In Successfully as a Professor. Welcome " + user.getUserName() + " !!").build();
			} else if ("Admin".equals(user.getRole())) {
				return Response.ok("Logged In Successfully as an Admin. Welcome " + user.getUserName() + " !!").build();
			} else {
				return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Role").build();
			}
		} catch (InvalidCredentialsException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
		}
	}
	@GET
	@Path("/hello")
	public String helloget(){
		return "Hello World";
	}
	@POST
	@Path("/register")
	public Response registerNewStudent(Student user) {
		try {
			this.studOps=  new StudentOperations();
			if (studOps.isUsernameTaken(user.getUserName())) {
				return Response.status(Response.Status.CONFLICT).entity("Username already exists. Please enter a different username.").build();
			}

			int sId = studOps.addStudent(user.getUserName(), user.getName(), "Student", user.getPassword(), user.getDepartment());
			if (sId > 0) {
				return Response.ok("Congratulations!! " + user.getUserName() + "\nYou have been added successfully \nYour Student Id is : " + sId).build();
			} else {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Registration Failed").build();
			}
		}
		catch (Exception e){
			e.printStackTrace();
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/updatePassword")
	public Response updatePassword(@QueryParam("username") String username, @QueryParam("currentPassword") String currentPassword, @QueryParam("newPassword") String newPassword) {
		try{
			this.userOps = new UserOperations();
			if (userOps.checkCredentials(username, currentPassword)) {
				if (userOps.updatePassword(username, newPassword)) {
					return Response.ok("Password updated successfully").build();
				} else {
					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to update password").build();
				}
			} else {
				return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid Credentials").build();
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
}
