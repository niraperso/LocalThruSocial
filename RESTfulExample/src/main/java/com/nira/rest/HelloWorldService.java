package com.nira.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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

	@Path("/post")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postMsg(String itemData){

		Gson gson = new GsonBuilder().create();

		System.out.println(gson.toJson(itemData));

		ResponseElements resElem = gson.fromJson(itemData, ResponseElements.class);

		return Response.status(200).entity(resElem.toString()).build();
	}
//	@Path("/post")
//	@PUT
//	public Response postMsg1(String msg){
//		System.out.println("hello");
//		return Response.status(200).entity("Post message successful").build();
//	}
 
}
