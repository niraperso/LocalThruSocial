package com.inmobi.repository;

import com.inmobi.ReqResParams;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by niranjan.agrawal on 12/08/15.
 */

public class PopulateItemStoreRepo {

    HashMap<ItemLatLonPair,ReqResParams> populateItemStore(Connection c,String sql) throws SQLException {
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            HashMap<ItemLatLonPair,ReqResParams> map = new HashMap<ItemLatLonPair, ReqResParams>();
            while(resultSet.next()){
                Map.Entry<ItemLatLonPair,ReqResParams> entry = apply(resultSet);
                map.put(entry.getKey(),entry.getValue());
            }
        return map;
    }

    public Map.Entry<ItemLatLonPair, ReqResParams> apply(ResultSet r) throws SQLException {
        return makeEntry(r.getString("item_name"),
                r.getDouble("price"),
                r.getDouble("lat"),
                r.getDouble("lon"),
                r.getDouble("rating"),
                r.getString("address"));
    }

    private Map.Entry<ItemLatLonPair,ReqResParams> makeEntry(String item,double price,double lat,double lon,
                                                             double rating,String address) {
        ItemLatLonPair a = new ItemLatLonPair(item,lat,lon);
        ReqResParams b = new ReqResParams(item,price,rating,lat,lon,address);
        return new ImmutablePair(a,b);
    }

}
