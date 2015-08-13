package com.inmobi.repository;


import com.inmobi.ReqResParams;
import lombok.Getter;
import org.apache.commons.configuration.Configuration;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by niranjan.agrawal on 11/08/15.
 */
public class RepoLoader {

    final DataSource ds;
    final int itemrepoRefreshTime, latlonrepoRefreshTime;

    @Getter
    final ItemRepository itemRepository;
    final PopulateItemStoreRepo populateItemStore;
    static final String itemQuery = "select a.item_name,a.price,a.rating,a.item_desc,b.address,b.lat,b.lon from " +
            "item_store a,item_location b where a.lat_lon_key=b.id;";

    public RepoLoader(Configuration c) {
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setServerName(c.getString(""));
        pgSimpleDataSource.setUser(c.getString(""));
        pgSimpleDataSource.setPassword(c.getString(""));
        pgSimpleDataSource.setPortNumber(c.getInt(""));
        pgSimpleDataSource.setDatabaseName(c.getString(""));
        ds = pgSimpleDataSource;
        itemrepoRefreshTime = c.getInt("") * 1000;
        latlonrepoRefreshTime = c.getInt("") * 1000;
        itemRepository = new ItemRepository();
        populateItemStore = new PopulateItemStoreRepo();
    }

    public void init() {
        refreshItemStore();
        refreshLatLonRepo();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                refreshItemStore();
            }
        }, itemrepoRefreshTime, itemrepoRefreshTime);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                refreshLatLonRepo();
            }
        }, latlonrepoRefreshTime, latlonrepoRefreshTime);
    }

    private void refreshLatLonRepo() {
        try {
            Connection c = ds.getConnection();
            HashMap<ItemLatLonPair, ReqResParams> map = populateItemStore.populateItemStore(c, itemQuery);
            itemRepository.update(map, new Date());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void refreshItemStore() {
        try {
            Connection c = ds.getConnection();
            HashMap<ItemLatLonPair, ReqResParams> map = populateItemStore.populateItemStore(c, itemQuery);
            itemRepository.update(map, new Date());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}