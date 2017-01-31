package com.build.rest.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.build.rest.entity.Customer;

@Path("/xml")
public class XMLService {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_XML)
	public Customer getCustomerInXML() {

		Customer customer = new Customer();
		customer.setName("Abhijit");
		customer.setPin(1);

		return customer;

	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_XML)
	public Response createTrackInJSON(Customer customer) {

		String result = "Customer saved : " + customer;
		return Response.status(201).entity(result).build();

	}
}