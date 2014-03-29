package com.mike.londonmet.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;

import javax.persistence.Entity;
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
@Entity
public class Role {
	@Transient
	private Logger logger = Logger.getLogger(this.getClass().getName());

	// Properties
	@Id
	@GeneratedValue
	private Long id;

	@NotNull(message = "Role name cannot be null")
	@NotBlank(message = "Role name cannot be blank")
	@Max(value = 20, message = "Role name must be a maximum of 20 characters")
	private String name;

	@NotNull(message = "Role description cannot be null")
	@NotBlank(message = "Role description cannot be blank")
	@Max(value = 1024, message = "Role description must be a maximum of 1024 characters")
	private String description;
	private boolean active;

	// Audit properties
	@NotNull(message = "Role created on property cannot be null")
	private DateTime createdOn;
	@NotNull(message = "Role updated on property cannot be null")
	private DateTime updatedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public DateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(DateTime createdOn) {
		this.createdOn = createdOn;
	}

	public DateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(DateTime updatedOn) {
		this.updatedOn = updatedOn;
	}
}
