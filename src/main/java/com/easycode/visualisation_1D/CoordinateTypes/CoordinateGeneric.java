/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easycode.visualisation_1D.CoordinateTypes;

/**
 *
 * @author Luuk
 * @param <T>
 */
public class CoordinateGeneric<T> {
    private T xCoordinate;
    private T yCoordinate;

    public CoordinateGeneric(T xCoordinate, T yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public CoordinateGeneric() {
    }
    
    public T getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(T xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public T getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(T yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    @Override
    public String toString() {
        return "CoordinateGeneric{" + "xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + '}';
    }

}
