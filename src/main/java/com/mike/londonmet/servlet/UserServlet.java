package com.mike.londonmet.servlet;

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
@WebServlet("/user")
public class UserServlet extends ThymeleafEnabledServlet {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	public void init(){}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

	}

	//
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

	}
}
