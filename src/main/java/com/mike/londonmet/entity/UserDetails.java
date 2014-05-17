package com.mike.londonmet.entity;

import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
@Entity
public class UserDetails {
	@Transient
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

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

	@Column(name = "bio")
	@Size(min = 0, max = 2048, message = "Biography must be less than 2048 characters in length")
	private String bio;

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

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	@Override
	public String toString() {
		return this.getFirstname() + " " + this.getLastname();
	}
}
