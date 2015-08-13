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

	public ResultSet initialize(String query) throws SQLException {

		preparedStmt = connection.prepareStatement(query);
		ResultSet rs = preparedStmt.executeQuery();
		return rs;
	}

	public ItemInfo selectInItemLocation(String query) throws SQLException {

		DBHelper dbhelp = new DBHelper();
		ItemInfo itemInfo = new ItemInfo();

		ResultSet rs = dbhelp.initialize(query);

		while(rs.next()){

			itemInfo.setLocationID(rs.getInt("id"));
			itemInfo.setLat(rs.getDouble("lat"));
			itemInfo.setLon(rs.getDouble("lon"));
			itemInfo.setAddress(rs.getString("address"));
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

	public ItemInfo selectInItemStore(String query) throws SQLException {

		DBHelper dbhelp = new DBHelper();
		ItemInfo itemInfo = new ItemInfo();

		ResultSet rs = dbhelp.initialize(query);

		while(rs.next()){

			itemInfo.setLocationID(rs.getInt("id"));
			itemInfo.setItem(rs.getString("item_name"));
			itemInfo.setLocationID(rs.getInt("lat_lon_key"));
			itemInfo.setPrice(rs.getDouble("price"));
			itemInfo.setRating(rs.getInt("rating"));
			itemInfo.setDescription(rs.getString("item_desc"));
		}
		return itemInfo;
	}
}
