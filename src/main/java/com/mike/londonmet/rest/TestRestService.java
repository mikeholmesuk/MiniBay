package com.mike.londonmet.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
@Path("/test")
public class TestRestService {

	@GET
	public String getTest() {
		return "hello world";
	}
}
