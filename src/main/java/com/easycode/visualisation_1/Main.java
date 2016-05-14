/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easycode.visualisation_1;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;
import static processing.core.PApplet.map;
import static processing.core.PConstants.CENTER;
import processing.core.PImage;

/**
 *
 * @author Luuk
 */
public class Main extends PApplet{

    PImage photo;
    private static ArrayList<Earthquake> earthquakeList = new ArrayList<>();
    private static final JSONtoEarthquake newJsontoEarthquake = new JSONtoEarthquake();
    JsonObject earthquakes;
    JsonArray values;
    int c1, c2;
    int Y_AXIS = 1;
    int X_AXIS = 2;
    int from = color (232, 255, 62);
    int to = color (255, 62, 143);
    int j=0;

    public static void main(String[] args) {
        try {
            earthquakeList = newJsontoEarthquake.JSONtoEarthquake();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        PApplet.main( new String[]{"com.easycode.visualisation_1.Main"} ); 
    }
    
    @Override
    public void settings() {
        size(1200, 1012);
    }
    
    @Override
    public void setup(){
        background(255,255,255);
        photo = loadImage("IcelandMap.png", "png");
        image(photo, 100,100,1000,812);
        
        drawLegend();
        drawEarthquakes();
    }

    @Override
    public void draw() {
        setGradient(900, 950, 200, 10, c1, c2, Y_AXIS);
    }

//    public void createEarthquakeList() {
//        try {
//            JSONParser newJson = new JSONParser();
//            earthquakes = newJson.getJsonFromUrl("http://apis.is/earthquake/is");
//            drawEarthquakes2(earthquakes);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        
//    }
    
    public void drawEarthquakes() {
        strokeWeight((float) 1.5);
        for (Earthquake EQ : earthquakeList) {       
            int c = color(255, (255 - ((EQ.getSize()+1)*85)), 0);
            fill(c);
            ellipse(((27+EQ.getLongitude())*74),(((68-EQ.getLatitude())*180)), (EQ.getDepth() * 2), (EQ.getDepth() * 2));
            
            println("Latitude: " + EQ.getLatitude() + ", Longitude: " + EQ.getLongitude() + ", Depth: " + EQ.getDepth() + ", Size: " + EQ.getSize());
        }
    }
    
    public void drawEarthquakes2(JsonObject earthquakes) {
        values = earthquakes.getAsJsonArray("results");
        for (int i = 0; i < values.size(); i++) {
            JsonObject earthquake = values.get(i).getAsJsonObject(); 

            Float latitude = earthquake.get("latitude").getAsFloat();
            Float longitude = earthquake.get("longitude").getAsFloat();
            Float depth = earthquake.get("depth").getAsFloat();
            Float size = earthquake.get("size").getAsFloat();

            int c = color(255, (255 - ((size+1)*85)), 0);
            strokeWeight((float) 1.5);
            fill(c);
            ellipse(((27+longitude)*74),(((68-latitude)*180)), (depth * 2), (depth * 2));
            
            println("Latitude: " + latitude + ", Longitude: " + longitude + ", Depth: " + depth + ", Size: " + size);
        }
    }
    
    public void drawLegend() {
        stroke(4);
        c1 = color(255, 255, 0);
        c2 = color(255, 0, 0);
        textAlign(CENTER);
        textSize(14);
        fill(1);
        text("Scale of Richter", 955, 940);
        textSize(12);
        text("-1              0               1               2", 1000, 975);
        textSize(20);

        text("Earthquakes Iceland from May 9th 2015 to May 11th 2015", 600, 50);
        textSize(14);
        text("From vedur.is", 600, 80);

        strokeWeight((float) 1.0);
        fill(255,255,255);
        rect(750, 950, 50, 10);
        fill(0,0,0);
        rect(800, 950, 50, 10);
        line(750, 950, 750, 965);
        line(850, 950, 850, 965);

        textSize(12);
        text("0                       25 km", 815, 975);
        textSize(14);
        text("Depth in KM", 791, 940);
    }
    
    public void setGradient(int x, int y, float w, float h, int c1, int c2, int axis ) {
        for (int i = x; i <= x+w; i+=1) {
          float inter = map(i, x, x+w, 0, 1);
          int c = lerpColor(c1, c2, inter);
          stroke(c);
          line(i, y, i, y+h);
        }
    }
}