package com.mike.londonmet.entity.listener;

import javax.persistence.PrePersist;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
public class TimestampListener {

	@PrePersist
	public void onSave(Object obj) {

	}
}
