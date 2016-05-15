/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easycode.visualisation_1;

import java.sql.Timestamp;

/**
 *
 * @author Luuk
 */
public class Earthquake {

    float latitude;
    float longitude;
    float depth;
    float size;
    Timestamp timestamp;

    public Earthquake(float latitude, float longitude, float depth, float size, Timestamp timestamp) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.depth = depth;
        this.size = size;
        this.timestamp = timestamp;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }
    
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

}
