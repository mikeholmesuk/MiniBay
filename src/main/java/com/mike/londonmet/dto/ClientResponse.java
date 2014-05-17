package com.mike.londonmet.dto;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
public class ClientResponse {
	private Status status;
	private String message;
	private Object body;
	private DateTime responseTime;

	public ClientResponse() {
		this.responseTime = new DateTime();
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public DateTime getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(DateTime responseTime) {
		this.responseTime = responseTime;
	}
}
