package com.mike.londonmet.dao;

import com.mike.londonmet.entity.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
@Stateless
public class UserDao {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@PersistenceContext
	private EntityManager entityManager;

	public boolean createUser(User user) {
		logger.info("Adding User " + user.toString());

		try {
			//entityManager.persist(user.getUserDetails());     // Needed to ensure that values are saved
			entityManager.persist(user);
			return true;
		}
		catch (EntityExistsException eee) {
			logger.log(Level.SEVERE, "Entity already exists in the Data-Store");
		}
		catch (IllegalArgumentException iae) {
			logger.log(Level.SEVERE, "Illegal argument exception caught when persisting " + user.toString());
		}
		return false;
	}

	public User getUserByUsernameAndPassword(String username, String password) {
		logger.info("Finding user by username: " + username);
		Query query = entityManager.createQuery("SELECT OBJECT(user) FROM User user WHERE user.username = :username AND user.password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);

		return (User) query.getSingleResult();
	}

	public User getUserById(Long userId) {
		return entityManager.find(User.class, userId);
	}

	public List<User> getAllUsers() {
		Query query = entityManager.createQuery("SELECT u from User u");

		List<User> users = query.getResultList();
		logger.info("Users: " + users);

		return users;
	}
}
