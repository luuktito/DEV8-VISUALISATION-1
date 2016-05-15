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
public class WGS {
    private double degrees;
    private double minutes;
    private double seconds;

    public WGS(double degrees, double minutes, double seconds) {
        this.degrees = degrees;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public WGS() {
    }

    @Override
    public String toString() {
        return "WGS{" + "degrees=" + degrees + ", minutes=" + minutes + ", seconds=" + seconds + '}';
    }

    public double getDegrees() {
        return degrees;
    }

    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    public double getMinutes() {
        return minutes;
    }

    public void setMinutes(double minutes) {
        this.minutes = minutes;
    }

    public double getSeconds() {
        return seconds;
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }
}
