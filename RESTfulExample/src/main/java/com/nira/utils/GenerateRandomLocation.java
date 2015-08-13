package com.nira.utils;

import java.util.Random;

/**
 * Created by dhirendra.thakur on 8/13/15.
 */
public class GenerateRandomLocation {

    public static void main(String[] args) {

        getLocation(12.8988180,77.6534780,50);
    }

    public static void getLocation(double x0, double y0, int radius) {

        Random random = new Random();

        // Convert radius from meters to degrees
        double radiusInDegrees = radius / 111000f;

        double u = random.nextDouble();
        double v = random.nextDouble();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        double x = w * Math.cos(t);
        double y = w * Math.sin(t);

        // Adjust the x-coordinate for the shrinking of the east-west distances
        double new_x = x / Math.cos(y0);

        double foundLongitude = new_x + x0;
        double foundLatitude = y + y0;
        System.out.println("Longitude: " + foundLongitude + "  Latitude: " + foundLatitude );
    }
}
