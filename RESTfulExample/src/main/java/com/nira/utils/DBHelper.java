package com.nira.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nira.ItemInfo;

public class DBHelper {
	
	Connection con;
	public DBHelper(){
		
		con = DBConnector.getConnection();
	}
	
	public ItemInfo selectQuery(String query) {
		// TODO Auto-generated method stub
		
		ItemInfo itemInfo = new ItemInfo();
		try {
			
			PreparedStatement pr = con.prepareStatement(query);
			ResultSet rs = pr.executeQuery();
			
			while(rs.next()){
				
				itemInfo.setLat(rs.getDouble("lat"));
				itemInfo.setLon(rs.getDouble("lon"));
				itemInfo.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return itemInfo;
	}

}
