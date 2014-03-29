package com.mike.londonmet.servlet;

import com.mike.londonmet.config.Thymeleaf;
import com.mike.londonmet.entity.Product;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
public abstract class ThymeleafEnabledServlet extends HttpServlet {

	@Inject
	protected Thymeleaf thymeleaf;

	public boolean isAjax(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}
}
