package com.inmobi;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by niranjan.agrawal on 07/08/15.
 */

@Data
@AllArgsConstructor
public class ReqResParams {

    private String item;
    private double price;
    private double rating;
    private double lat;
    private double lon;
    private String address;
}
