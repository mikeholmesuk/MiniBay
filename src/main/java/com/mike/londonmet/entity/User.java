package com.mike.londonmet.entity;

import com.mike.londonmet.entity.listener.UserListener;
import com.sun.istack.internal.NotNull;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
@Entity
@EntityListeners(UserListener.class)
public class User {
	@Transient
	private Logger logger = Logger.getLogger(this.getClass().getName());

	// Properties
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@NotBlank
	@Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters in length")
	@Column(name = "username")
	private String username;

	@NotNull
	@NotBlank
	@Size(min = 5, max = 200, message = "User password must be less than 200 characters in length")
	private String password;

	// Audit properties
	@Column(name="created_on")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createdOn;
	@Column(name="updated_on")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updatedOn;

	// Relationships (1:1 - UserDetails, 1:n - product)
	@OneToOne(cascade = CascadeType.ALL)
	@JsonProperty("user_details")
	private UserDetails userDetails;

	public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String uername) {
		this.username = uername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	@Override
	public String toString() {
		return "User: " + this.getUsername() + " - " + this.getUserDetails().toString();
	}
}
