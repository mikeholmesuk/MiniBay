package com.mike.londonmet.dao;

import com.mike.londonmet.entity.Product;

import javax.ejb.Stateful;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.TransactionRequiredException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateful
public class ProductDao {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@PersistenceContext
	private EntityManager entityManager;

	public boolean addProduct(Product product) {
		logger.info("Adding product: " + product.toString());
		try {
			entityManager.persist(product);
			return true;
		}
		catch (EntityExistsException eee) {
			logger.log(Level.SEVERE, "Entity already exists in the Data-Store");
			eee.printStackTrace();
		}
		catch (IllegalArgumentException iae) {
			logger.log(Level.SEVERE, "Illegal Argument Exception caught when persisting " + product.toString());
			iae.printStackTrace();
		}
		return false;
	}

	public void deleteProduct(Product product) {
		logger.info("Deleting product: " + product.getId());
		entityManager.remove(product);
	}

	public List<Product> getAllProducts() {
		Query query = entityManager.createQuery("SELECT p from Product p");

		List<Product> products = query.getResultList();
		logger.info("Products: " + products);

		return products;
	}

	public List<Product> getProductsByUser() {
		//Query query = entityManager.createQuery("SELECT p from Product p WHERE p.user = :user");

		return null;
	}

	public Product getProduct() {
		Query query = entityManager.createQuery("SELECT p from Product p");
		return (Product) query.getSingleResult();
	}

	public Long getProductsCount() {
		Query query = entityManager.createQuery("SELECT COUNT(p) FROM Product p");
		Long count = (Long) query.getSingleResult();
		logger.info("Count of " + count);
		return count;
	}

	public Product findProductById(Long id) {
		return entityManager.find(Product.class, id);
	}
}
