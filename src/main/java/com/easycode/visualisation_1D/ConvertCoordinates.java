/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easycode.visualisation_1D;

import com.easycode.visualisation_1D.CoordinateTypes.*;

/**
 *
 * @author Luuk
 */
public class ConvertCoordinates {
    public static CoordinateGeneric<Rijksdriehoek> RDHtoRDH(CoordinateGeneric<Rijksdriehoek> coordinates) {
        return coordinates;
    }
    
    public static CoordinateGeneric<WGS> RDHtoWGS(CoordinateGeneric<Rijksdriehoek> coordinates) {
        return DECtoWGS(RDHtoDEC(coordinates));
    }
    
    public static CoordinateGeneric<Decimal> RDHtoDEC(CoordinateGeneric<Rijksdriehoek> coordinates) {
        // The city "Amsterfoort" is used as reference "Rijksdriehoek" coordinate.
        int referenceRdX = 155000;
        int referenceRdY = 463000;

        double dX = (double)(coordinates.getxCoordinate().getCoordinate() - referenceRdX) * (double)(Math.pow(10,-5));
        double dY = (double)(coordinates.getyCoordinate().getCoordinate() - referenceRdY) * (double)(Math.pow(10,-5));

        double sumN = 
            (3235.65389 * dY) + 
            (-32.58297 * Math.pow(dX, 2)) + 
            (-0.2475 * Math.pow(dY, 2)) + 
            (-0.84978 * Math.pow(dX, 2) * dY) + 
            (-0.0655 * Math.pow(dY, 3)) + 
            (-0.01709 * Math.pow(dX, 2) * Math.pow(dY, 2)) + 
            (-0.00738 * dX) + 
            (0.0053 * Math.pow(dX, 4)) + 
            (-0.00039 * Math.pow(dX, 2) * Math.pow(dY, 3)) + 
            (0.00033 * Math.pow(dX, 4) * dY) + 
            (-0.00012 * dX * dY);
        double sumE = 
            (5260.52916 * dX) + 
            (105.94684 * dX * dY) + 
            (2.45656 * dX * Math.pow(dY, 2)) + 
            (-0.81885 * Math.pow(dX, 3)) + 
            (0.05594 * dX * Math.pow(dY, 3)) + 
            (-0.05607 * Math.pow(dX, 3) * dY) + 
            (0.01199 * dY) + 
            (-0.00256 * Math.pow(dX, 3) * Math.pow(dY, 2)) + 
            (0.00128 * dX * Math.pow(dY, 4)) + 
            (0.00022 * Math.pow(dY, 2)) + 
            (-0.00022 * Math.pow(dX, 2)) + 
            (0.00026 * Math.pow(dX, 5));


        // The city "Amsterfoort" is used as reference "WGS84" coordinate.
        double referenceWgs84X = 52.15517;
        double referenceWgs84Y = 5.387206;

        double latitude = referenceWgs84X + (sumN / 3600);
        double longitude = referenceWgs84Y + (sumE / 3600);

        return new CoordinateGeneric<>(new Decimal(latitude), new Decimal(longitude));
    }
    
    public static CoordinateGeneric<WGS> WGStoWGS(CoordinateGeneric<WGS> coordinates) {
        return coordinates;
    }
    
    public static CoordinateGeneric<Decimal> WGStoDEC(CoordinateGeneric<WGS> coordinates) {
        CoordinateGeneric<Decimal> convertedCoordinate = new CoordinateGeneric();
        convertedCoordinate.setxCoordinate(new Decimal((coordinates.getxCoordinate().getDegrees()) + (coordinates.getxCoordinate().getMinutes() / 60) + (coordinates.getxCoordinate().getSeconds() / 3600)));
        convertedCoordinate.setyCoordinate(new Decimal((coordinates.getyCoordinate().getDegrees()) + (coordinates.getyCoordinate().getMinutes() / 60) + (coordinates.getyCoordinate().getSeconds() / 3600)));
        return convertedCoordinate;
    }
    
    public static CoordinateGeneric<Rijksdriehoek> WGStoRDH(CoordinateGeneric<WGS> coordinates) {
        return DECtoRDH(WGStoDEC(coordinates));
    }
    
    public static CoordinateGeneric<Decimal> DECtoDEC(CoordinateGeneric<Decimal> coordinates) {
        return coordinates;
    }
        
    public static CoordinateGeneric<WGS> DECtoWGS(CoordinateGeneric<Decimal> coordinates) {
        CoordinateGeneric<WGS> convertedCoordinate = new CoordinateGeneric();
        convertedCoordinate.setxCoordinate(DECtoWGSPoint(coordinates.getxCoordinate()));
        convertedCoordinate.setyCoordinate(DECtoWGSPoint(coordinates.getyCoordinate()));
        return convertedCoordinate;
    }
    
    public static WGS DECtoWGSPoint(Decimal coordinate) {
        double degrees = Math.floor(coordinate.getCoordinate());
        double minutes = Math.floor((coordinate.getCoordinate() * 60) % 60);
        double seconds = Math.floor(Math.abs(coordinate.getCoordinate()) * 3600) % 60;
        return new WGS(degrees, minutes, seconds);
    }
    
    public static CoordinateGeneric<Rijksdriehoek> DECtoRDH(CoordinateGeneric<Decimal> coordinates) {
        // The city “Amsterfoort” is used as reference “Rijksdriehoek” coordinate.
        int referenceRdX = 155000;
        int referenceRdY = 463000;

        // The city “Amsterfoort” is used as reference “WGS84” coordinate.
        double referenceWgs84X = 52.15517;
        double referenceWgs84Y = 5.387206;

        double[][] Rpq = new double[4][5];

         Rpq[0][1] = 190094.945;
        Rpq[1][1] = -11832.228;
        Rpq[2][1] = -114.221;
        Rpq[0][3] = -32.391;
        Rpq[1][0] = -0.705;
        Rpq[3][1] = -2.340;
        Rpq[0][2] = -0.008;
        Rpq[1][3] = -0.608;
        Rpq[2][3] = 0.148;

        double[][] Spq = new double[4][5];
        Spq[0][1] = 0.433;
        Spq[0][2] = 3638.893;
        Spq[0][4] = 0.092;
        Spq[1][0] = 309056.544;
        Spq[2][0] = 73.077;
        Spq[1][2] = -157.984;
        Spq[3][0] = 59.788;
        Spq[2][2] = -6.439;
        Spq[1][1] = -0.032;
        Spq[1][4] = -0.054;

        double d_lattitude = (0.36 * (coordinates.getxCoordinate().getCoordinate() - referenceWgs84X));
        double d_longitude = (0.36 * (coordinates.getyCoordinate().getCoordinate() - referenceWgs84Y));

        double calc_latt = 0;
        double calc_long = 0;

        for (int p = 0; p < 4; p++)
        {
            for (int q = 0; q < 5; q++)
            {
                calc_latt += Rpq[p][q] * Math.pow(d_lattitude, p) * Math.pow(d_longitude, q);
                calc_long += Spq[p][q] * Math.pow(d_lattitude, p) * Math.pow(d_longitude, q);
            }
        }

        double rd_x_coordinate = (referenceRdX + calc_latt);
        double rd_y_coordinate = (referenceRdY + calc_long);

        return new CoordinateGeneric<>(new Rijksdriehoek(rd_x_coordinate), new Rijksdriehoek(rd_y_coordinate));
    }

    static String RDHtoGEO(CoordinateGeneric<Rijksdriehoek> RDHCoordinates) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
