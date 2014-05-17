package com.mike.londonmet.rest;

import com.mike.londonmet.dto.AuthRequest;
import com.mike.londonmet.dto.ClientResponse;
import com.mike.londonmet.dto.Status;
import com.mike.londonmet.entity.User;
import com.mike.londonmet.service.UserService;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
@Path("/user")
public class AuthenticationService {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Inject
	private UserService userService;

	@POST
	public ClientResponse authenticateUser(AuthRequest authRequest) {
		ClientResponse response = new ClientResponse();

		User user = userService.findUserbyUsernameAndPassword(authRequest.getUsername(), authRequest.getPassword());

		if (user != null) {
			response.setStatus(Status.SUCCESS);
			response.setMessage("Successfully logged in");
			response.setBody(user);

		}
		else {
			response.setStatus(Status.FAILURE);
			response.setMessage("Failed to authenticate - try again");
		}

		response.setResponseTime(new DateTime());
		return response;
	}
}
