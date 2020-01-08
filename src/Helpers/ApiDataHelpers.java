package Helpers;

import HttpUrlConnection.model.Line;
import HttpUrlConnection.model.Stop;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class ApiDataHelpers {

    // It used to cast an object in RatpLine type
    public static Line castObjectToRatpLine(JSONObject lineJsonObject) {
        return new Line(
                ((ArrayList<ArrayList<String>>) lineJsonObject.get("arrets")),
                (String) lineJsonObject.get("name"),
                (String) lineJsonObject.get("num"),
                (String) lineJsonObject.get("color"),
                (String) lineJsonObject.get("type"),
                ((ArrayList<ArrayList<String>>) lineJsonObject.get("arrets"))
        );
    }

    public static Stop castObjectToRatpStop(JSONObject stationJsonObject) {
        // Get the type of the current station
        String type = (String) stationJsonObject.get("type");
        // Create a dictionnary of the lines for the station
        HashMap<String, ArrayList<String>> lines = (HashMap<String, ArrayList<String>>) stationJsonObject.get("lignes");


        return new Stop(
                (String) stationJsonObject.get("commune"),
                (String) stationJsonObject.get("lat"),
                (String) stationJsonObject.get("lng"),
                lines.get(type),
                (String) stationJsonObject.get("nom"),
                (String) stationJsonObject.get("num"),
                (String) stationJsonObject.get("type"),
                (Boolean) stationJsonObject.get("isHub")

        );
    }

    public static boolean isTypeOf(String type, JSONObject ratpDataObject)
    {
        return ratpDataObject.get("type").equals(type);
    }
}
