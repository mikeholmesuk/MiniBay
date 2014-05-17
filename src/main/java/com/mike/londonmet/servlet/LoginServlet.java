package com.mike.londonmet.servlet;

import com.mike.londonmet.dto.AuthRequest;
import com.mike.londonmet.dto.ClientResponse;
import com.mike.londonmet.dto.Status;
import com.mike.londonmet.entity.User;
import com.mike.londonmet.service.UserService;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
@WebServlet("/login")
public class LoginServlet extends ThymeleafEnabledServlet {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Inject
	private UserService userService;

	public void init() {}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Authenticating user");

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader;
		String line = null;
		ObjectMapper mapper = new ObjectMapper();

		AuthRequest auth;

		try {
			reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		}
		catch (Exception e) {
			logger.info("Exception caught while reading POST data");
		}

		try {
			auth = mapper.readValue(buffer.toString(), AuthRequest.class);
			logger.info("AuthRequest: " + auth.toString());

			response.setContentType("application/json");

			User user = userService.findUserbyUsernameAndPassword(auth.getUsername(), auth.getPassword());

			ClientResponse json = new ClientResponse();
			if(user != null) {
				json.setStatus(Status.SUCCESS);
				json.setMessage("Found user " + user.toString());
				json.setBody(user);

				HttpSession session = request.getSession();
				session.setAttribute("user", user);

				response.addCookie(new Cookie("user", user.getUsername()));
			}
			else {
				json.setStatus(Status.FAILURE);
				json.setMessage("Failed to find user " + auth.toString());  // Shouldn't do this as this will print the password but is useful for debugging
			}

			mapper.writeValue(response.getOutputStream(), json);
		}
		catch (IOException ioe) {
			logger.warning("IO Exception caught when authenticating");
			ioe.printStackTrace();
		}
		catch (Exception e) {
			logger.warning("Exception caught when authenticating");
			e.printStackTrace();
		}
	}
}
