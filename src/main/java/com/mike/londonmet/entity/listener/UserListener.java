package com.mike.londonmet.entity.listener;

import com.mike.londonmet.entity.User;
import org.joda.time.DateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
public class UserListener {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@PrePersist
	@PreUpdate
	public void onUserSave(User user) {
		logger.info("In save listener for User object");
		if (user.getCreatedOn() == null) {
			logger.info("Created on is null for user");
			user.setCreatedOn(new DateTime());
		}
		user.setUpdatedOn(new DateTime());
	}
}
