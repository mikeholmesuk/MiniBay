package com.mike.londonmet.entity;

import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.logging.Logger;

@Entity
public class CustomAttribute {
	@Transient
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Id
	@GeneratedValue
	private Long id;

	// Product ID
	@ManyToOne(fetch=FetchType.EAGER)
	private Product product;

	@NotNull
	@NotBlank(message = "Key cannot be blank")
	@Max(value = 50, message = "Key must contain less than 50 characters")
	private String key;
	@NotNull
	@NotBlank(message = "Value cannot be blank")
	@Max(value = 2048, message = "Value must contain less than 2048 characters")
	private String value;

	private boolean active;
	private boolean visible;

	// Public Constructors
	public CustomAttribute() {
		// No argument constructor
	}
	public CustomAttribute(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
