package Helpers;

import HttpUrlConnection.model.Line;
import HttpUrlConnection.model.Stop;
import org.json.simple.JSONObject;

import java.lang.reflect.Array;
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
        // Create a dictionnary of the lines for the station
        // /!\ Here, the object lines on the api of vasyenmetro.com is : /!\
        // line : { "metro": ["13, 12"] } OR line : { "RER": ["B"] } etc..
        HashMap<String, ArrayList<String>> apiRatpLines = (HashMap<String, ArrayList<String>>) stationJsonObject.get("lignes");

        // We choosed the type of line.
        String type = "metro";

        ArrayList<String> lineIdsContainedByEachStop = apiRatpLines.get(type);


        return new Stop(
                (String) stationJsonObject.get("commune"),
                (String) stationJsonObject.get("lat"),
                (String) stationJsonObject.get("lng"),
                (ArrayList<String>) lineIdsContainedByEachStop,
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

    /*
    /!\ Some station's type are wrong. Puteaux's station contains a subway and rer but it's type is RER... /!\
    Well, we need to check the line key of station
     */
    public static boolean isTypeOfLineForStation(String type, JSONObject ratpDataObject)
    {
        HashMap<String, ArrayList<String>> lines = (HashMap<String, ArrayList<String>>)ratpDataObject.get("lignes");
        return lines.containsKey(type);
    }
}
