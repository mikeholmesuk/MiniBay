package com.mike.londonmet.entity;

import com.mike.londonmet.entity.listener.ProductListener;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Logger;

@Entity
@EntityListeners(ProductListener.class)
public class Product {
	@Transient
	private Logger logger = Logger.getLogger(this.getClass().getName());

	// Properties
	@Id
	@GeneratedValue
	private Long id;

	@NotNull(message = "Title cannot be null")
	@Size(min = 2, max = 50, message = "Title should be between 2 & 50 characters in length")
	private String title;

	@NotNull(message = "Description cannot be null")
	@Size(min = 5, max = 4096, message = "Description should be between 5 & 4096 characters in length")
	private String description;

	@NotBlank
	private String imageUrl;

	// Audit properties
	@NotNull
	@Column(name="created_on")
	private Timestamp createdOn;

	@Column(name="updated_on")
	private Timestamp updatedOn;

	// Custom attributes
	@OneToMany
	private List<CustomAttribute> customAttributes;

	public Product() {
		// Empty constructor
	}

	public Product(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<CustomAttribute> getCustomAttributes() {
		return customAttributes;
	}

	public void setCustomAttributes(List<CustomAttribute> customAttributes) {
		this.customAttributes = customAttributes;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
