/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easycode.visualisation_1D.CoordinateTypes;

/**
 *
 * @author Luuk
 */
public class Decimal {
    private double coordinate;

    public Decimal(double coordinate) {
        this.coordinate = coordinate;
    }

    public double getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(double coordinate) {
        this.coordinate = coordinate;
    }
}
