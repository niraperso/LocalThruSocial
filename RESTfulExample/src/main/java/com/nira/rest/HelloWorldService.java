package com.nira.rest;

import com.google.gson.Gson;
import com.nira.ResponseElements;
import com.nira.utils.GetData;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/item")
public class HelloWorldService {

	@Path("/query")
	@GET
	public Response getMsg(@QueryParam("item") String item,
						   @QueryParam("lat") double lat, @QueryParam("lon") double lon) {

		GetData data = new GetData();
		String feeds;
		ArrayList<ResponseElements> info = data.getData(item,lat,lon);
		if(info!=null) {
			Gson gson = new Gson();
			System.out.println(gson.toJson(info));
			feeds = gson.toJson(info);
		}
		else{
			feeds="{}";
		}
		return Response.status(200).entity(feeds).build();
 
	}

	@Path("/{param}")
	@POST
	public Response postMsg(@PathParam("param") String msg){
		System.out.println("hello");
		return Response.status(201).entity("Post message successful").build();
	}
}
