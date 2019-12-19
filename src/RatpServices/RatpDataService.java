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
    // Get list of all ratp's lines
    public ArrayList<Line> getRatpLines() throws IOException, ParseException
    {
        // Init result
        ArrayList<Line> lines = new ArrayList<Line>();

        // Init all data of ratp
        JSONObject ratpData = _httpClient.getJsonObjectOfAllRatpData();

        // Get Lines in JsonObject
        JSONObject linesObject = (JSONObject)ratpData.get("lignes");
        // Read all keys of lines
        Iterator<String> keys = linesObject.keySet().iterator();

        while(keys.hasNext()) {
            String key = keys.next();
            lines.add(ApiDataHelpers.castObjectToRatpLine((JSONObject)linesObject.get(key)));
        }

        return lines;
    }
}
