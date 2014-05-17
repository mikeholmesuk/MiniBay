package com.mike.londonmet.rest;

import com.mike.londonmet.dto.ClientResponse;
import com.mike.londonmet.dto.Status;
import com.mike.londonmet.entity.User;
import com.mike.londonmet.service.UserService;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
@Path("/registration")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegistrationWebService {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Inject
	private UserService userService;

	@GET
	@Path("{user_id}")
	public ClientResponse getUserById(Long id) {
		User user = userService.getUser(id);

		ClientResponse response = new ClientResponse();

		if (user != null) {
			response.setStatus(Status.SUCCESS);
			response.setMessage("Found user with ID " + id);
			response.setBody(user);

		}
		else {
			response.setStatus(Status.FAILURE);
			response.setMessage("Failed to find user with ID " + id);
			response.setBody(user);
		}
		response.setResponseTime(new DateTime());
		return response;
	}

	@POST
	public ClientResponse registerUser(User user) {
		logger.info("In registerUser method");

		ClientResponse response = new ClientResponse();

		// echo results back for now
		logger.info("User: " + user.toString());

		if (userService.createUser(user)) {
			response.setStatus(Status.SUCCESS);
			response.setMessage("Successfully registered new User (" + user.getUserDetails().toString() + ")");
			response.setBody(user);
		}
		else {
			response.setStatus(Status.FAILURE);
			response.setMessage("Failed to register new User. PLease try again later.");
			if (userService.isUserValid(user)) {
				response.setBody(userService.getUserValidationErrors(user));
			}
		}

		response.setResponseTime(new DateTime());

		return response;
	}
}
