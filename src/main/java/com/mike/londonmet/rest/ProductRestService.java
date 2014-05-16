package com.mike.londonmet.rest;

import com.mike.londonmet.entity.Product;
import com.mike.londonmet.service.ProductService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
@Path("/products")
//@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductRestService {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Inject
	private ProductService prodService;

	@GET
	public List<Product> getAllProducts() {
		logger.info("In getAllProducts method");
		return prodService.getAllProducts();
	}

	@GET
	@Path("count")
	public Long getProductCount() {
		logger.info("In getProductCount method");
		//return prodService.getProductsCount();
		return 1L;
	}
}
