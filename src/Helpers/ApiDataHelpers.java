package Helpers;

import HttpUrlConnection.model.Line;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class ApiDataHelpers {

    // It used to cast an object in RatpLine type
    public static Line castObjectToRatpLine(JSONObject lineJsonObject)
    {
        return new Line(
                ((ArrayList<ArrayList<String>>)lineJsonObject.get("arrets")),
                (String)lineJsonObject.get("name"),
                (String)lineJsonObject.get("num"),
                (String)lineJsonObject.get("color"),
                (String)lineJsonObject.get("type"),
                ((ArrayList<ArrayList<String>>)lineJsonObject.get("arrets"))
        );
    }
}
