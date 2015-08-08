package com.nira.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by niranjan.agrawal on 07/08/15.
 */
public class DBTest {

    static Connection c;
    public static void main(String args[]){
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://127.0.0.1:5432/ItemDb",
                            "inmobi", "inmobi");
            System.out.println("Connection successful");
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery( "SELECT * from itemstore;" );
            while ( rs.next() ) {
                System.out.println(rs.getString("item_key"));
            }
            rs.close();
            st.close();
            c.close();

        } catch (Exception e) {
            System.out.println("Excetion caught..");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

    }
}
