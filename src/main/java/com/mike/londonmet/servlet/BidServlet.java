package com.mike.londonmet.servlet;

import com.mike.londonmet.service.BidService;
import org.thymeleaf.context.WebContext;

import javax.inject.Inject;
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
@WebServlet("/bid")
public class BidServlet extends ThymeleafEnabledServlet {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Inject
	private BidService bidService;


	public void init() {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebContext context = new WebContext(request, response, this.getServletContext());
		context.setVariable("test", "This is a string that will be mapped to the variable 'test'");

		//templateEngine.process("/index.html", context, response.getWriter());
		logger.info("Rendering page");
		thymeleaf.process("index", context, response.getWriter());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("In toPost method");

	}
}
