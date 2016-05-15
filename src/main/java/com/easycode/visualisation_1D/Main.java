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
     
    public static void main(String[] args) {
        System.out.println("Available coordinate types: (RDH), (GEO), (DEC)");
        System.out.println("RDH Example: 199735, 307365");
        System.out.println("GEO Example: 50 45 16, 6 1 16 or 38 53 23, -77 00 32");
        System.out.println("DEC Example: 50.75450, 6.02110");

        int inputUserChoiceFrom = askUserInput("from");
        int inputUserChoiceTo = askUserInput("to");
        convertUserInput(inputUserChoiceFrom, inputUserChoiceTo);
    }
    
    public static int askUserInput(String type) {
        if (type.equals("from")) {
            System.out.println("Please type in the coordinate type you want to convert from:");
            int inputConvertFrom = checkInputFromUser(reader.nextLine());
            if (inputConvertFrom == 0) {
                System.out.println("Sorry that input was not a valid coordinate type, please try again");
                askUserInput("from");
            }
            return inputConvertFrom;
        }
        else {
            System.out.println("Please type in the coordinate type you want to convert to:");
            int inputConvertTo = checkInputFromUser(reader.nextLine());
            if (inputConvertTo == 0) {
                System.out.println("Sorry that input was not a valid coordinate type, please try again");
                askUserInput("to");
            }
            return inputConvertTo;
        }
    }
    
    public static int checkInputFromUser(String input) {
        if (input.equalsIgnoreCase("RDH")) 
            return 1;
        else if (input.equalsIgnoreCase("GEO"))
            return 2;
        else if (input.equalsIgnoreCase("DEC"))
            return 3;
        else
            return 0;
    }
    
    public static void convertUserInput(int inputConvertFrom, int inputConvertTo) {
            CoordinateGeneric<Rijksdriehoek> RDHCoordinates = new CoordinateGeneric<>();
            CoordinateGeneric<WGS> WGSCoordinates = new CoordinateGeneric<>();
            CoordinateGeneric<Decimal> DECCoordinates = new CoordinateGeneric<>();
            CoordinateGeneric<WGS> WGSResults = new CoordinateGeneric<>();
            CoordinateGeneric<Decimal> DECResults = new CoordinateGeneric<>();
            CoordinateGeneric<Rijksdriehoek> RDHResults = new CoordinateGeneric<>();
            double inputConvertFromDouble[] = new double[3];
            double inputConvertToDouble[] = new double[3];
            String inputConvertFromValue;
        
            System.out.println("Please type in the Coordinates you want to convert:");
            inputConvertFromValue = reader.nextLine();
            String[] newXString = inputConvertFromValue.substring(0, inputConvertFromValue.indexOf(',')).split(" ");
            String[] newYString = inputConvertFromValue.substring(inputConvertFromValue.indexOf(',') + 1).split(" ");
            
            for (int i = 0; i < newXString.length; i++) {
                inputConvertFromDouble[i] = Double.parseDouble(newXString[i]);
                inputConvertToDouble[i] = Double.parseDouble(newYString[i+1]);
            }
                        
            switch((inputConvertFrom*10)+inputConvertTo) {
                case 11 :
                    RDHCoordinates = new CoordinateGeneric<>(new Rijksdriehoek(inputConvertFromDouble[0]), new Rijksdriehoek(inputConvertToDouble[0]));
                    RDHResults = ConvertCoordinates.RDHtoRDH(RDHCoordinates);
                    break;
                case 12 :
                    RDHCoordinates = new CoordinateGeneric<>(new Rijksdriehoek(inputConvertFromDouble[0]), new Rijksdriehoek(inputConvertToDouble[0]));
                    WGSResults = ConvertCoordinates.RDHtoWGS(RDHCoordinates);
                    break;
                case 13 :
                    RDHCoordinates = new CoordinateGeneric<>(new Rijksdriehoek(inputConvertFromDouble[0]), new Rijksdriehoek(inputConvertToDouble[0]));
                    DECResults = ConvertCoordinates.RDHtoDEC(RDHCoordinates);
                    break;
                case 21 :
                    WGSCoordinates = new CoordinateGeneric<>(new WGS(inputConvertFromDouble[0],inputConvertFromDouble[1],inputConvertFromDouble[2]), new WGS(inputConvertToDouble[0],inputConvertToDouble[1],inputConvertToDouble[2]));
                    RDHResults = ConvertCoordinates.WGStoRDH(WGSCoordinates);
                    break;
                case 22 :
                    WGSCoordinates = new CoordinateGeneric<>(new WGS(inputConvertFromDouble[0],inputConvertFromDouble[1],inputConvertFromDouble[2]), new WGS(inputConvertToDouble[0],inputConvertToDouble[1],inputConvertToDouble[2]));
                    WGSResults = ConvertCoordinates.WGStoWGS(WGSCoordinates);
                    break;
                case 23 :
                    WGSCoordinates = new CoordinateGeneric<>(new WGS(inputConvertFromDouble[0],inputConvertFromDouble[1],inputConvertFromDouble[2]), new WGS(inputConvertToDouble[0],inputConvertToDouble[1],inputConvertToDouble[2]));
                    DECResults = ConvertCoordinates.WGStoDEC(WGSCoordinates);
                    break;
                case 31 :
                    DECCoordinates = new CoordinateGeneric<>(new Decimal(inputConvertFromDouble[0]), new Decimal(inputConvertToDouble[0]));
                    RDHResults = ConvertCoordinates.DECtoRDH(DECCoordinates);
                    break;
                case 32 :
                    DECCoordinates = new CoordinateGeneric<>(new Decimal(inputConvertFromDouble[0]), new Decimal(inputConvertToDouble[0]));
                    WGSResults = ConvertCoordinates.DECtoWGS(DECCoordinates);
                    break;
                case 33 :
                    DECCoordinates = new CoordinateGeneric<>(new Decimal(inputConvertFromDouble[0]), new Decimal(inputConvertToDouble[0]));
                    DECResults = ConvertCoordinates.DECtoDEC(DECCoordinates);
                    break;
                default:
            }
            
            if (inputConvertTo == 1) {
                System.out.println("new X: " + RDHResults.getxCoordinate().getCoordinate());
                System.out.println("new Y: " + RDHResults.getyCoordinate().getCoordinate());
            }
            else if (inputConvertTo == 2) {
                System.out.println("new X: " + WGSResults.getxCoordinate().toString());
                System.out.println("new Y: " + WGSResults.getyCoordinate().toString());
            }
            else { 
                System.out.println("new X: " + DECResults.getxCoordinate().getCoordinate());
                System.out.println("new Y: " + DECResults.getyCoordinate().getCoordinate());
            }
    }
}
