package com.nira.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nira.ItemInfo;
import com.nira.ItemJsonParser;
import com.nira.utils.GetData;
import com.nira.utils.UpdateItem;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
		ArrayList<ItemInfo> info = data.getData(item,lat,lon);
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

	@Path("/post")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postMsg(String itemData){
		
		ItemJsonParser itemJsonParser = new ItemJsonParser();
		UpdateItem upItem = new UpdateItem();
		
		ItemInfo itemInfo = itemJsonParser.getItemData(itemData);
			
		double latVal = itemInfo.getLat();
		double lonVal = itemInfo.getLon();
		
		boolean res = upItem.isLatLonExist(latVal, lonVal);	
		
		if(!res){
			
			upItem.updateLocation(latVal, lonVal);
		}
		
		
		return Response.status(201).entity().build();
	}
}
