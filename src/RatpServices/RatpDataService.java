package RatpServices;

import Helpers.ApiDataHelpers;
import HttpUrlConnection.HttpUrlConnection;
import HttpUrlConnection.model.Line;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class RatpDataService
{
    HttpUrlConnection _httpClient;
    JSONObject _ratpData;

    public RatpDataService(String GET_URL, String USER_AGENT) throws  IOException,ParseException  {
        _httpClient = new HttpUrlConnection(GET_URL, USER_AGENT);
        _ratpData = _httpClient.getJsonObjectOfAllRatpData();

    }
   
    /* model => ["lines","stations" ]*/
    public  ArrayList getRatpData(String model) {
        // Init result
        ArrayList data = new ArrayList<Line>();

        // Get Lines in JsonObject
        JSONObject ratpObject = (JSONObject)_ratpData.get(model);
        // Read all keys of lines

        for (String key : (Iterable<String>) ratpObject.keySet()) {
            switch (model) {
                case "lignes":
                    if (ApiDataHelpers.isTypeOf("metro", (JSONObject) ratpObject.get(key)))
                    {
                        data.add(ApiDataHelpers.castObjectToRatpLine((JSONObject) ratpObject.get(key)));
                    }
                    break;
                case "stations":
                    if (ApiDataHelpers.isTypeOf("metro", (JSONObject) ratpObject.get(key)))
                    {
                        data.add(ApiDataHelpers.castObjectToRatpStop((JSONObject) ratpObject.get(key)));
                    }
                    break;


            }

        }

        return data;
    }
}
