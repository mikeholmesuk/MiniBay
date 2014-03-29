package com.mike.londonmet.entity;

import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import java.util.logging.Logger;

@Entity
public class Category {
	@Transient
	private Logger logger = Logger.getLogger(this.getClass().getName());

	// Properties
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@NotBlank(message = "Category name cannot be blank")
	@Max(value = 75, message = "Name cannot be more than 75 characters in length")
	private String name;
	@NotNull
	@Max(value = 4096, message = "Category description cannot be more than 4096 characters in length")
	private String description;
	private boolean active;

	public Category() {}

	public Category(String name, String description, boolean active) {
		this.name = name;
		this.description = description;
		this.active = active;
	}

	// Getters and Setters
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
}
