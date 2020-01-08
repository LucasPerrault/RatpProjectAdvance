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

    public RatpDataService(String GET_URL, String USER_AGENT) {
        _httpClient = new HttpUrlConnection(GET_URL, USER_AGENT);
    }
   
    /* model => ["lines","stations" ]*/
    public  ArrayList getRatpData(String model) throws  IOException,ParseException {
        // Init result
        ArrayList data = new ArrayList<Line>();

        // Init all data of ratp
        JSONObject ratpData = _httpClient.getJsonObjectOfAllRatpData();

        // Get Lines in JsonObject
        JSONObject ratpObject = (JSONObject)ratpData.get(model);
        // Read all keys of lines

        for (String key : (Iterable<String>) ratpObject.keySet()) {
            switch (model) {
                case "lignes":
                    data.add(ApiDataHelpers.castObjectToRatpLine((JSONObject) ratpObject.get(key)));
                    break;
                case "stations":
                    data.add(ApiDataHelpers.castObjectToRatpStop((JSONObject) ratpObject.get(key)));
                    break;


            }

        }

        return data;
    }
}
