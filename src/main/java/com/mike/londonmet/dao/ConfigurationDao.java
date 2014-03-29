package com.mike.londonmet.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
public class ConfigurationDao {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@PersistenceContext
	private EntityManager entityManager;
}
