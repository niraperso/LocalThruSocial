package com.nira.utils;

import com.nira.ItemInfo;

/**
 * Created by dhirendra.thakur on 8/9/15.
 */
public class UpdateItem {

	DBHelper dbhelper = new DBHelper();

	public static void main(String[] args) {

		UpdateItem uItem = new UpdateItem();

		ItemInfo iInfo = new ItemInfo();

		iInfo.setLat(1);
		iInfo.setLon(1);

		int out = uItem.isLatLonExist(iInfo);

		System.out.println(out);
	}

	public int isLatLonExist(ItemInfo itemData){

    	// run select query and check lat-lon exist or not
    	//"select * from item_location where lat="+itemData.getLat()+" and lon="+itemData.getLon();
		String latLonIdQuery = "select ss.address,ss.distance,item.item_name,item.price,item.rating,ss.lat,ss.lon " +
				"from (SELECT id, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lon ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance" +
				",address,lat,lon FROM item_location) ss,item_store item where ss.distance < 45 AND item.lat_lon_key=ss.id AND item.item_name=?;";

		System.out.println(latLonIdQuery);

		ItemInfo itemLocDetail =  dbhelper.selectQuery(latLonIdQuery);
    	
    	if(itemLocDetail.getLocationID()!=0)
    	{
    		return itemLocDetail.getLocationID();
    	}

		return 0;
    }

	public void updateLocation(ItemInfo itemData) {
		// TODO Auto-generated method stub
				
		String updateLocation = "INSERT INTO item_location (address,lat,lon)"
				+" values ("+"'"+itemData.getAddress()+"'"+","+itemData.getLat()+","+itemData.getLon()+")";
		//System.out.println(updateLocation);
		dbhelper.insertQuery(updateLocation);
	}

	public String checkItemStore(int latLonId) {

		String itemQuery = "select item_name from item_store where id ="+latLonId;

		ItemInfo itemInfo = dbhelper.selectQuery(itemQuery);

		return itemInfo.getItem();
	}

	public void updateItem(ItemInfo itemInfo) {

		String updateItem = "";
	}

	public int getLatLonId(ItemInfo itemData) {

		String latQuery = "select id from item_location where lat="+itemData.getLat();

		ItemInfo itemId = dbhelper.selectQuery(latQuery);

		return itemId.getLocationID();
	}

	public void insertIntoItemStore(int lat_lon_id, ItemInfo itemInfo) {

		String insertItem = "insert into item_store (item_name,lat_lon_key,rating,item_desc)"
				+" values ("+"'"+itemInfo.getItem()+"'"+lat_lon_id+itemInfo.getRating()+")";

		dbhelper.insertQuery(insertItem);
	}
}
