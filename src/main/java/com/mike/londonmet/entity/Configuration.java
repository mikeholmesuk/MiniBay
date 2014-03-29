package com.mike.londonmet.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
public class Configuration {
	@Transient
	private Logger logger = Logger.getLogger(this.getClass().getName());

	// Properties
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@NotBlank(message = "Configuration key cannot be blank")
	@Max(value = 50, message = "Configuration key cannot be longer than 50 characters in length")
	private String key;
	@NotNull
	@NotBlank(message = "Configuration value cannot be blank")
	@Max(value = 100, message = "Configuration value cannot be longer than 100 characters in length")
	private String value;
	private boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
