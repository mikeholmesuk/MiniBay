package com.mike.londonmet.message;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
public class ClientJsonResponse {
	private Status status;
	private String body;
	private Date responseTime;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
}
