package com.nira.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
 
@Path("/hello")
public class HelloWorldService {
 
	@Path("/{param}")
	@GET
	public Response getMsg(@PathParam("param") String msg) {

        System.out.println("hello");
        String output = "Jersey say : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}

	@Path("/{param}")
	@POST
	public Response postMsg(@PathParam("param") String msg){
		System.out.println("hello");
		return Response.status(201).entity("Post message successful").build();
	}

//	@Path("/post")
//	@PUT
//	public Response postMsg1(String msg){
//		System.out.println("hello");
//		return Response.status(200).entity("Post message successful").build();
//	}
 
}
