package com.nira.utils;

import com.nira.ItemInfo;

import java.sql.SQLException;

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

		//int out = uItem.isLatLonExist(iInfo);

		//System.out.println(out);
	}

	public int isLatLonExist(ItemInfo itemData) throws SQLException {

    	// run select query and check lat-lon exist or not
		String latLonIdQuery = "select * from item_location where lat="+itemData.getLat()+" and lon="+itemData.getLon();

		//System.out.println(latLonIdQuery);

		ItemInfo itemLocDetail =  dbhelper.selectInItemLocation(latLonIdQuery);
    	
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

	public String checkItemStore(int latLonId) throws SQLException {

		String itemQuery = "select * from item_store where lat_lon_key="+latLonId;

		ItemInfo itemInfo = dbhelper.selectInItemStore(itemQuery);

		return itemInfo.getItem();
	}

	public void updateItem(ItemInfo itemInfo) {

		String updateItem = "";
	}

	public int getLatLonId(ItemInfo itemData) throws SQLException {

		String latQuery = "select * from item_location where lat="+itemData.getLat();

		ItemInfo itemId = dbhelper.selectInItemLocation(latQuery);

		return itemId.getLocationID();
	}

	public void insertIntoItemStore(int lat_lon_id, ItemInfo itemInfo) {

		String insertItem = "insert into item_store (item_name,lat_lon_key,rating,item_desc,price)"
				+" values ("+"'"+itemInfo.getItem()+"'"+","+lat_lon_id+","+itemInfo.getRating()+","
				+itemInfo.getDescription()+","+itemInfo.getPrice()+")";

		dbhelper.insertQuery(insertItem);
	}
}
