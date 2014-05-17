package com.mike.londonmet.entity.listener;

import com.mike.londonmet.entity.Product;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
public class ProductListener {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@PrePersist
	@PreUpdate
	public void onProductSave(Product product) {
		logger.info("In onProduct EntityListener");
		if (product.getCreatedOn() == null) {
			logger.info("Created on is null.");
			product.setCreatedOn(new DateTime());
		}
		product.setUpdatedOn(new DateTime());

		// Temp for image url
		product.setImageUrl("fake string");
	}
}
