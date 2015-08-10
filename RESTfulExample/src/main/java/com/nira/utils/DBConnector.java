package com.nira.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by niranjan.agrawal on 06/08/15.
 */
public class DBConnector {

    private static Connection c;
    static Connection getConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://127.0.0.1:5499/item_prod",
                            "dhirendra", "Genesys@virus10");
        } catch (Exception e) {
            System.out.println("Excetion caught..");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return c;
    }
}
