package com.inmobi.repository;

import com.inmobi.ReqResParams;
import lombok.Data;

import java.util.*;

/**
 * Created by niranjan.agrawal on 12/08/15.
 */

@Data
class LatLonPair{
    final double lat,lon;
}

public class LatLonRepository {

    Map<LatLonPair,ArrayList<LatLonPair>> latlonRepo = Collections.emptyMap();

    Date lastModified = new Date(0);
    volatile boolean isInitialized = false;

    public ArrayList<LatLonPair> query(double lat,double lon){
        return latlonRepo.get(new LatLonPair(lat,lon));
    }

    public void update(HashMap<LatLonPair,ArrayList<LatLonPair>> map,Date lastModified){
        this.latlonRepo = map;
        this.lastModified = lastModified;
        this.isInitialized = true;
    }
}
