package com.mike.londonmet.entity;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
public class Bid {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private Double currentBid;

	@NotNull
	@JsonProperty("max_bid")
	private Double maxBid;

	@ManyToOne
	private Product product;

	@ManyToOne
	private User user;

	// Audit methods
	@NotNull
	private DateTime created_on;

	@NotNull
	private DateTime updated_on;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCurrentBid() {
		return currentBid;
	}

	public void setCurrentBid(Double currentBid) {
		this.currentBid = currentBid;
	}

	public Double getMaxBid() {
		return maxBid;
	}

	public void setMaxBid(Double maxBid) {
		this.maxBid = maxBid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public DateTime getCreated_on() {
		return created_on;
	}

	public void setCreated_on(DateTime created_on) {
		this.created_on = created_on;
	}

	public DateTime getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(DateTime updated_on) {
		this.updated_on = updated_on;
	}
}
