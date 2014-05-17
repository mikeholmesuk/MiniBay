package com.mike.londonmet.service;

import com.mike.londonmet.dao.UserDao;
import com.mike.londonmet.entity.User;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
public class UserService {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Inject
	private UserDao userDao;

	public List<User> getAllUsers() {
		return Collections.emptyList();
	}

	public User getUser(Long id) {
		return userDao.getUserById(id);
	}

	public boolean createUser(User user) {
		logger.info("In createUser method");
		if (isUserValid(user)) {
			return userDao.createUser(user);
		}
		else {
			for (ConstraintViolation violation : getUserValidationErrors(user)) {
				logger.info("Validation failed: " + violation);
			}
			return false;
		}
	}

	public void updateUser(User user) {

	}

	public void deleteUser(Long id) {

	}

	public User findUserbyUsernameAndPassword(String username, String password) {
		logger.info("Finding user with username " + username);
		return userDao.getUserByUsernameAndPassword(username, password);
	}

	public boolean isUserValid(User user) {
		Set<ConstraintViolation<User>> violations = getUserValidationErrors(user);
		if (violations.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}

	public Set<ConstraintViolation<User>> getUserValidationErrors(User user) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		return validator.validate(user);
	}
}
