package com.mike.londonmet.servlet;

import com.mike.londonmet.dto.ClientResponse;
import com.mike.londonmet.dto.Status;
import org.codehaus.jackson.map.ObjectMapper;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
@WebServlet("/logout")
public class LogoutServlet extends ThymeleafEnabledServlet {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	public void init() {}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Logging out user " + request.getSession().getAttribute("user"));

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		WebContext context = new WebContext(request, response, this.getServletContext());
		ObjectMapper mapper = new ObjectMapper();
		ClientResponse json = new ClientResponse();
		json.setStatus(Status.SUCCESS);
		json.setMessage("You have successfully logged out");

		mapper.writeValue(response.getOutputStream(), json);
	}
}

