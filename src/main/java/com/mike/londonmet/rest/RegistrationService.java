package com.mike.londonmet.rest;

import com.mike.londonmet.dto.ClientResponse;
import com.mike.londonmet.dto.Status;
import com.mike.londonmet.entity.User;
import com.mike.londonmet.service.UserService;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
@Path("/registration")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegistrationService {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Inject
	private UserService userService;

	@POST
	public ClientResponse registerUser(User user) {
		logger.info("In registerUser method");

		ClientResponse response = new ClientResponse();

		// echo results back for now
		logger.info("User: " + user.toString());

		if (userService.createUser(user)) {
			response.setStatus(Status.SUCCESS);
			response.setBody(user);
		}
		else {
			response.setStatus(Status.FAILURE);
			if (userService.isUserValid(user)) {
				response.setBody(userService.getUserValidationErrors(user));
			}
		}

		response.setResponseTime(new DateTime());

		return response;
	}
}
