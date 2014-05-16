package com.mike.londonmet.service;

import com.mike.londonmet.dao.ProductDao;
import com.mike.londonmet.entity.Product;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
public class ProductService {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Inject
	private ProductDao productDao;

	public List<Product> getAllProducts() {
		logger.log(Level.FINE, "Retrieving all products");
		return productDao.getAllProducts();
	}

	public Product getProduct(Product product) {
		return new Product();
	}

	public Long getsProductsCount() {
		return productDao.getProductsCount();
	}

	public boolean addNewProduct(Product product) {
		logger.log(Level.FINE, "Adding new product to database");

		return productDao.addProduct(product);
	}

	// This is a good candidate for a singleton?
	public Set<ConstraintViolation<Product>> validateProduct(Product product) {
		Set<ConstraintViolation<Product>> violations;
		ValidatorFactory validatorFactory;
		Validator validator;

		// Avoid the overhead by having this available
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		violations = null;

		violations = validator.validate(product);
		return violations;
	}
}
