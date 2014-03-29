package com.mike.londonmet.servlet;

import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
@WebServlet("/dashboard")
public class DashboardServlet extends ThymeleafEnabledServlet {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	public void init() {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Requesting Dashboard page");

		WebContext context = new WebContext(request, response, this.getServletContext());

		logger.info("Rendering Dashboard page");
		thymeleaf.process("dashboard", context, response.getWriter());
	}
}
