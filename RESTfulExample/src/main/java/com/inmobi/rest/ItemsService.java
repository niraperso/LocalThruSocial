package com.inmobi.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.inmobi.ReqResParams;
import com.inmobi.repository.RepoLoader;
import com.inmobi.utils.GetData;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/item")
public class ItemsService {

	boolean isInit=false;
	RepoLoader repoLoader;
	Configuration c;

	@Path("/query")
	@GET
	public Response getMsg(@QueryParam("item") String item,
						   @QueryParam("lat") double lat, @QueryParam("lon") double lon) {

		if(!isInit){
			initialize();
		}
		GetData data = new GetData();
		String feeds;
		ArrayList<ReqResParams> info = data.getData(repoLoader,item,lat,lon);
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

		if(!isInit){
			initialize();
		}
		Gson gson = new Gson();
		JsonObject jsonObject =  gson.fromJson(itemData, JsonObject.class);
		if(jsonObject!=null){
			ReqResParams reqParams = new ReqResParams(jsonObject.get("item").getAsString(),jsonObject.get("price").getAsDouble()
			,jsonObject.get("rating").getAsDouble(),jsonObject.get("lat").getAsDouble(),jsonObject.get("lon").getAsDouble(),
					jsonObject.get("address").getAsString());
		}
		return null;
	}

	void initialize(){
		try {
			c = new PropertiesConfiguration("conf.properties");
			repoLoader = new RepoLoader(c);
			repoLoader.init();
			isInit=true;
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

}
