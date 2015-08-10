package com.nira.utils;

import com.nira.ItemInfo;

import java.sql.*;
import java.util.ArrayList;


/**
 * Created by niranjan.agrawal on 07/08/15.
 */
public class GetData {

    public ArrayList<ItemInfo> getData(String key,double lat,double lon){
        Connection c = DBConnector.getConnection();
        ArrayList<ItemInfo> feedData = new ArrayList<ItemInfo>();
        try {
            PreparedStatement ps = c.prepareStatement("select ss.address,ss.distance,item.item_name,item.price,item.rating,ss.lat,ss.lon " +
                    "from (SELECT id, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lon ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance" +
                    ",address,lat,lon FROM item_location) ss,item_store item where ss.distance < 45 AND item.lat_lon_key=ss.id AND item.item_name=?;");
            ps.setDouble(1, lat);
            ps.setDouble(2, lon);
            ps.setDouble(3, lat);
            ps.setString(4,key);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ItemInfo responseElement = new ItemInfo();
                responseElement.setItem(rs.getString("item_name"));
                responseElement.setLat(rs.getDouble("lat"));
                responseElement.setLon(rs.getDouble("lon"));
                responseElement.setPrice(rs.getDouble("price"));
                responseElement.setRating(rs.getDouble("rating"));
                responseElement.setAddress(rs.getString("address"));
                feedData.add(responseElement);
            }
            return feedData;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
