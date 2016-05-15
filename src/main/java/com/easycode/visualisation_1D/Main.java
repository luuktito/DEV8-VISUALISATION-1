/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easycode.visualisation_1D;

import java.util.Scanner;

/**
 *
 * @author Luuk
 */
public class Main {
    static Scanner reader = new Scanner(System.in);
    static String inputConvertFrom, inputConvertTo;
    
    public static void main(String[] args) {
        System.out.println("Available coordinate types: Rijksdriehoek (RDH), Geographical (GEO), Decimal (DEC)");
        askUserInput("from");
        askUserInput("to");
        System.out.println("From: " + inputConvertFrom + ", To: " + inputConvertTo);
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
        return (input.equalsIgnoreCase("Rijksdriehoek"))
                || (input.equalsIgnoreCase("RDH"))
                || (input.equalsIgnoreCase("Geographical"))
                || (input.equalsIgnoreCase("GEO"))
                || (input.equalsIgnoreCase("Decimal"))
                || (input.equalsIgnoreCase("DEC"));
    }
}
