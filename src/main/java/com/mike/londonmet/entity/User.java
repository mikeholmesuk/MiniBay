package com.mike.londonmet.entity;

import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.Email;
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
public class User {
	@Transient
	private Logger logger = Logger.getLogger(this.getClass().getName());

	// Properties
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(min = 2, max = 100, message = "First name must be between 2 & 100 characters in length")
	@Column(name="first_name")
	private String firstname;
	@NotNull
	@Size(min = 2, max = 100, message = "Last name must be between 2 & 100 characters in length")
	@Column(name="last_name")
	private String lastname;

	@NotNull
	@NotBlank(message = "User email cannot be blank")
	@Email(message = "User must have a valid email")
	private String email;
	@NotNull
	@NotBlank
	@Max(value = 200, message = "User password must be less than 200 characters in length")
	private String password;

	// Audit properties
	@Column(name="created_on")
	private DateTime createdOn;
	@Column(name="updated_on")
	private DateTime updatedOn;

	// Relationships (1:n - product)

	public User() {}
	public User(String firstname, String lastname, String email, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User: " + this.getFirstname() + ", " + this.getLastname();
	}
}
