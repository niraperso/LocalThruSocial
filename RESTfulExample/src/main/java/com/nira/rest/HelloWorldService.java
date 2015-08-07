package com.nira.rest;

import com.google.gson.Gson;
import com.nira.ResponseElements;
import com.nira.utils.GetData;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/hello")
public class HelloWorldService {
 
	@Path("/{param}")
	@GET
	public Response getMsg(@PathParam("param") String msg) {

        System.out.println("hello");
//        String output = "Jersey say : " + msg;
		GetData data = new GetData();
		ArrayList<ResponseElements> info = data.getData(msg);
		Gson gson = new Gson();
		System.out.println(gson.toJson(info));
		String feeds = gson.toJson(info);
		return Response.status(200).entity(feeds).build();
 
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
