package com.nira.utils;

import com.nira.ResponseElements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by niranjan.agrawal on 07/08/15.
 */
public class GetData {

    public ArrayList<ResponseElements> getData(String key){
        Connection c = DBConnector.getConnection();
        ArrayList<ResponseElements> feedData = new ArrayList<ResponseElements>();
        try {
            PreparedStatement ps = c.prepareStatement("select * FROM ItemStore where item_key="+key+";");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ResponseElements responseElement = new ResponseElements();
                responseElement.setItem(rs.getString("item_key"));
                responseElement.setLocation(rs.getString("location"));
                responseElement.setPrice(rs.getDouble("price"));
                responseElement.setRating(rs.getDouble("rating"));
                feedData.add(responseElement);
            }
            return feedData;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
