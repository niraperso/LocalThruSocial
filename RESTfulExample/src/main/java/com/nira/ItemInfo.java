package com.nira;

/**
 * Created by niranjan.agrawal on 07/08/15.
 */
public class ItemInfo {

    private String item;
    private double price;
    private double rating;
    private double lat;
    private double lon; 
    private String address;
    		 
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    
    @Override
    public String toString(){
    	
    	return lat+","+lon;
    }
}
