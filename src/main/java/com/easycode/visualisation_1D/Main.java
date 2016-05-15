/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easycode.visualisation_1D;

import com.easycode.visualisation_1D.CoordinateTypes.CoordinateGeneric;
import com.easycode.visualisation_1D.CoordinateTypes.Decimal;
import com.easycode.visualisation_1D.CoordinateTypes.Rijksdriehoek;
import com.easycode.visualisation_1D.CoordinateTypes.WGS;
import java.util.Scanner;

/**
 *
 * @author Luuk
 */
public class Main {
    static Scanner reader = new Scanner(System.in);
    static String inputConvertFrom, inputConvertTo;
    
    public static void main(String[] args) {
        System.out.println("Available coordinate types: (RDH), (GEO), (DEC)");
        System.out.println("RDH Example: 199735, 307365");
        System.out.println("GEO Example: 50 45 16, 6 1 16 or 38 53 23, -77 00 32");
        System.out.println("DEC Example: 50.75450, 6.02110");

        CoordinateGeneric<WGS> WGSCoordinates = new CoordinateGeneric<>(new WGS(38,53,24), new WGS(77, 00, 32));
        CoordinateGeneric<Decimal> DECCoordinates = new CoordinateGeneric<>(new Decimal(50.75450), new Decimal(6.02110));
        CoordinateGeneric<Rijksdriehoek> RDHCoordinates = new CoordinateGeneric<>(new Rijksdriehoek(199735), new Rijksdriehoek(307365));
    }
    
    public static void askUserInput(String type) {
        if (type.equals("from")) {
            System.out.println("Please type in the coordinate you want to convert from:");
            inputConvertFrom = reader.next();
            if (checkInputFromUser(inputConvertFrom) != true) {
                System.out.println("Sorry that input was not a valid coordinate type, please try again");
                askUserInput("from");
            }
        }
        else {
            System.out.println("Please type in the coordinate you want to convert to:");
            inputConvertTo = reader.next();
            if (checkInputFromUser(inputConvertFrom) != true) {
                System.out.println("Sorry that input was not a valid coordinate type, please try again");
                askUserInput("to");
            }
        }
    }
    
    public static boolean checkInputFromUser(String input) {
        return ((input.equalsIgnoreCase("RDH"))
               || (input.equalsIgnoreCase("GEO"))
               || (input.equalsIgnoreCase("DEC")));
    }
}
