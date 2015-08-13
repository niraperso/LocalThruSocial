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

import java.sql.SQLException;
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
	public Response postMsg(String itemData) throws SQLException {
		
		ItemJsonParser itemJsonParser = new ItemJsonParser();
		UpdateItem upItem = new UpdateItem();
		
		ItemInfo itemInfo = itemJsonParser.getItemData(itemData);

		int res = upItem.isLatLonExist(itemInfo);
		
		if( res == 0){

			// insert into item_location and item_store
			upItem.updateLocation(itemInfo);
			//select query with where lat lon
			int lat_lon_id = upItem.getLatLonId(itemInfo);
			//insert into item_store with lat_lon_id
			upItem.insertIntoItemStore(lat_lon_id, itemInfo);

			return Response.status(201).entity("Updated item!!").build();

		}else{

			//if lat-lon exist,
			//check for item present in item_store
			//
			double price=0;
			String itemName  = upItem.checkItemStore(res);

			System.out.println("Item name-"+itemName);
			if(!itemName.equalsIgnoreCase(itemInfo.getItem())){

				//insert into item_store and item_location table
				upItem.updateItem(itemInfo);
			}else{

				//double itemPrice = upItem.getItemPrice(itemInfo);

				price = itemInfo.getPrice();
				System.out.print("The price of item -" + price);
			}
			return Response.status(201).entity("Lowest price of the item ..").build();
		}
	}
}
