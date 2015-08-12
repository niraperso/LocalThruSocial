package com.nira.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nira.ItemInfo;

public class DBHelper {
	
	Connection connection;
	PreparedStatement preparedStmt;

	public DBHelper(){

		connection = DBConnector.getConnection();
	}
	
	public ItemInfo selectQuery(String query) {
		// TODO Auto-generated method stub
		
		ItemInfo itemInfo = new ItemInfo();
		try {

			preparedStmt = connection.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();
			
			while(rs.next()){

				itemInfo.setLocationID(rs.getInt("id"));
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
	
	public void insertQuery(String query){

		try {

			preparedStmt = connection.prepareStatement(query);
			preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
