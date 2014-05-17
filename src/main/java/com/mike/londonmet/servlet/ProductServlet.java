package com.mike.londonmet.servlet;

import com.mike.londonmet.dto.ClientResponse;
import com.mike.londonmet.entity.Product;
import com.mike.londonmet.dto.Status;
import com.mike.londonmet.service.ProductService;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;
import org.thymeleaf.context.WebContext;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
@WebServlet("/product")
public class ProductServlet extends ThymeleafEnabledServlet {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Inject
	private ProductService productService;

	public void init() {
		logger.info("In init method of ProductServlet");
	}

	// Handle GET requests
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("In servlet");
		// Get Products from Database

		WebContext context = new WebContext(request, response, this.getServletContext());

		context.setVariable("products", productService.getAllProducts());

		logger.info("Rendering page");
		thymeleaf.process("products", context, response.getWriter());
	}

	// Handle POST requests
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("In post method");

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader;
		String line = null;
		ObjectMapper mapper = new ObjectMapper();

		Product product;

		try {
			reader = request.getReader();
			while ((line = reader.readLine()) != null)
				buffer.append(line);
		}
		catch (Exception e) {
			logger.log(Level.FINE, "Exception caught when reading request body");
		}

		logger.info("POST contents - " + buffer.toString());

		try {
			product = mapper.readValue(buffer.toString(), Product.class);
			logger.info("Product class - " + product.toString());

			response.setContentType("application/json");

			productService.addNewProduct(product);

			ClientResponse json = new ClientResponse();
			json.setStatus(Status.SUCCESS);
			json.setBody(mapper.writeValueAsString(product));
			json.setResponseTime(new DateTime());
			mapper.writeValue(response.getOutputStream(), json);
		}
		catch (IOException ioe) {
			logger.warning("IO Exception caught when parsing product");
			ioe.printStackTrace();
		}
		catch (Exception e) {
			logger.warning("Exception caught when parsing product");
			e.printStackTrace();
		}
	}

	private String readRequestBodyAsString(HttpServletRequest request) {
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader;
		String line = null;

		try {
			reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			return buffer.toString();
		}
		catch (IOException ioe) {
			logger.log(Level.SEVERE, "IO Exception caught when reading HTTP Request Body");
			ioe.printStackTrace();
		}
		return null;
	}

	private Product mapStringToProduct(String productString) {
		return null;
	}
}
