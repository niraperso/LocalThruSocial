package com.inmobi.repository;

import com.inmobi.ReqResParams;
import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by niranjan.agrawal on 11/08/15.
 */

@Data
class ItemLatLonPair{
    final String item;
    final double lat,lon;
}

public class ItemRepository {

    Map<ItemLatLonPair,ReqResParams> itemRepo = Collections.emptyMap();
    Date lastModified = new Date(0);
    volatile boolean isInitialized = false;

    public ReqResParams query(String item,double lat,double lon){
        return itemRepo.get(new ItemLatLonPair(item,lat,lon));
    }

    public void update(HashMap<ItemLatLonPair,ReqResParams> map,Date lastModified){
        this.itemRepo = map;
        this.lastModified = lastModified;
        this.isInitialized = true;
    }
}
