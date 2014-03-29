package com.mike.londonmet.message;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
public enum Status {
	SUCCESS("Success"),
	FAILURE("Failure"),
	UNAUTHORISED("Unauthorised");

	private String value;

	private Status(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
