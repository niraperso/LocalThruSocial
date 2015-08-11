package com.inmobi.utils;

import com.inmobi.ReqResParams;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by niranjan.agrawal on 09/08/15.
 */
public class PostData {

    public void postData(ReqResParams params){

        int id=-1;
        Connection c = DBConnector.getConnection();
        try {
            PreparedStatement ps = c.prepareStatement("select id from item_location where lat=? and lon=?;");
            ps.setDouble(1,params.getLat());
            ps.setDouble(2,params.getLon());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
            if(id==-1){
                ps.executeQuery("insert into item_location");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
