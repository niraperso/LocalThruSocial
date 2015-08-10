package com.nira.utils;

import com.nira.ItemInfo;

/**
 * Created by dhirendra.thakur on 8/9/15.
 */
public class UpdateItem {
	
	DBHelper dbhelper = new DBHelper();
    public boolean isLatLonExist(double lat, double lon){

    	// run select query and check lat-lon exist or not
    	String getLatLon = "select lat,lon from item_location";
    	
    	ItemInfo itemLocDetail =  dbhelper.selectQuery(getLatLon);
    	
    	if((itemLocDetail.getLat() == lat) && (itemLocDetail.getLon() == lon))
    	{
    		return true;
    	}
    		
    	return false;
    }

	public void updateLocation(double latVal, double lonVal) {
		// TODO Auto-generated method stub
				
		String updateLocation = "";
	}
}
