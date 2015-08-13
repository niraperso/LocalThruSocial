package com.inmobi.location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dhirendra.thakur on 8/13/15.
 */
public class GeoLocationDemo {

    /**
     * @param radius radius of the sphere.
     * @param location center of the query circle.
     * @param distance radius of the query circle.
     * @param connection an SQL connection.
     * @return places within the specified distance from location.
     */
    public static ResultSet findPlacesWithinDistance(double radius, GeoLocation location, double distance, Connection connection)
            throws SQLException {

        GeoLocation[] boundingCoordinates = location.boundingCoordinates(distance, radius);
        boolean meridian180WithinDistance = boundingCoordinates[0].getLongitudeInRadians() >
                        boundingCoordinates[1].getLongitudeInRadians();

        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM item_location WHERE (lat >= ? AND lat <= ?) AND (lon >= ? " +
                        (meridian180WithinDistance ? "OR" : "AND") + " lon <= ?) AND " +
                        "acos(sin(?) * sin(lat) + cos(?) * cos(lat) * cos(lon - ?)) <= ?");

        statement.setDouble(1, boundingCoordinates[0].getLatitudeInRadians());
        statement.setDouble(2, boundingCoordinates[1].getLatitudeInRadians());
        statement.setDouble(3, boundingCoordinates[0].getLongitudeInRadians());
        statement.setDouble(4, boundingCoordinates[1].getLongitudeInRadians());
        statement.setDouble(5, location.getLatitudeInRadians());
        statement.setDouble(6, location.getLatitudeInRadians());
        statement.setDouble(7, location.getLongitudeInRadians());
        statement.setDouble(8, distance / radius);
        return statement.executeQuery();
    }

    public static void main(String[] args) throws SQLException {

        double earthRadius = 6371.01;
        GeoLocation myLocation = GeoLocation.fromRadians(12.8988180, 77.6534780);
        double distance = 5;
        Connection connection = null;

        ResultSet resultSet = findPlacesWithinDistance(earthRadius, myLocation, distance, connection);
    }

}
