package com.mike.londonmet.entity;

import com.mike.londonmet.entity.listener.ProductListener;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
	private String shortDescription;

	@NotNull(message = "Description cannot be null")
	@Size(min = 5, max = 4096, message = "Description should be between 5 & 4096 characters in length")
	private String longDescription;

	@NotBlank
	private String imageUrl;

	// Audit properties
	@NotNull
	@Column(name="created_on")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createdOn;

	@Column(name="updated_on")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updatedOn;

	// Custom attributes
	@OneToMany
	private List<CustomAttribute> customAttributes;

	@ManyToOne
	private User user;

	public Product() {
		// Empty constructor
	}

	public Product(String title, String description) {
		this.title = title;
		this.longDescription = description;
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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public List<CustomAttribute> getCustomAttributes() {
		return customAttributes;
	}

	public void setCustomAttributes(List<CustomAttribute> customAttributes) {
		this.customAttributes = customAttributes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String description) {
		this.longDescription = description;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
