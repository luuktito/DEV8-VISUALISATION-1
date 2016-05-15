/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easycode.visualisation_1;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luuk
 */
public class JSONtoEarthquake {
    private final JSONParser newJson = new JSONParser();
    private final JsonObject earthquakes = getEarthquakeJson();

    public ArrayList<Earthquake> JSONtoEarthquake() throws IOException {
        return createEarthquakeArrayList(earthquakes);
        //this.earthquakeList = createEarthquakeArrayList(earthquakes);
    }

    public JsonObject getEarthquakeJson() {
        JsonObject newEarthquakeJson = null;
        try {
            newEarthquakeJson = newJson.getJsonFromUrl("http://apis.is/earthquake/is");
            
        } catch (IOException ex) {
            Logger.getLogger(JSONtoEarthquake.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newEarthquakeJson;
    }
    
    public ArrayList<Earthquake> createEarthquakeArrayList(JsonObject earthquakes) throws IOException {

        JsonArray values = earthquakes.getAsJsonArray("results");
        ArrayList<Earthquake> returnList = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            JsonObject earthquake = values.get(i).getAsJsonObject(); 

            Float latitude = earthquake.get("latitude").getAsFloat();
            Float longitude = earthquake.get("longitude").getAsFloat();
            Float depth = earthquake.get("depth").getAsFloat();
            Float size = earthquake.get("size").getAsFloat();
            String str_timestamp = earthquake.get("timestamp").getAsString();

            Timestamp timeStampDate = Utilities.convertStringToTimestamp(str_timestamp);

            Earthquake EQ = new Earthquake(latitude, longitude, depth, size, timeStampDate);
            returnList.add(EQ);
        }
        return returnList;
    }
}
