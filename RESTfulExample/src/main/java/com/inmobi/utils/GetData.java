package com.inmobi.utils;

import com.inmobi.ReqResParams;
import com.inmobi.repository.RepoLoader;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by niranjan.agrawal on 07/08/15.
 */
@Slf4j
public class GetData {

    public ArrayList<ReqResParams> getData(RepoLoader repoLoader,String key,double lat,double lon){
        ReqResParams reqResParams = repoLoader.getItemRepository().query(key, lat, lon);
        if(reqResParams!=null) {
            Connection c = DBConnector.getConnection();
            ArrayList<ReqResParams> feedData = new ArrayList<ReqResParams>();
            try {
                PreparedStatement ps = c.prepareStatement("select ss.address,ss.distance,item.item_name,item.price,item.rating,ss.lat,ss.lon " +
                        "from (SELECT id, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lon ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance" +
                        ",address,lat,lon FROM item_location) ss,item_store item where ss.distance < 45 AND item.lat_lon_key=ss.id AND item.item_name=?;");
                ps.setDouble(1, lat);
                ps.setDouble(2, lon);
                ps.setDouble(3, lat);
                ps.setString(4, key);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ReqResParams responseElement = new ReqResParams(rs.getString("item_name"), rs.getDouble("price")
                            , rs.getDouble("rating"), rs.getDouble("lat"), rs.getDouble("lon"), rs.getString("address"));
                    feedData.add(responseElement);
                }
                return feedData;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
