package com.nira;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ItemJsonParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String itemJson = "{\"item\":\"onion\",\"lat\":\"37\",\"lon\":\"122\",\"price\":\"25\",\"address\":\"blr\"}";
	}
	
	public ItemInfo getItemData(String jsonStr){
		
		Gson gson = new GsonBuilder().create();
		
		ItemInfo itemInfo = gson.fromJson(jsonStr, ItemInfo.class);
		
		return itemInfo;
	}
}
