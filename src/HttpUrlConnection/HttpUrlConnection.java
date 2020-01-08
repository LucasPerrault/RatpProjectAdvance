package HttpUrlConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnection {

    private static String _userAgent = "Mozilla/5.0";
    private static String _urlGet;

    public HttpUrlConnection(String urlGet, String userAgent)
    {
        _urlGet = urlGet;
        _userAgent = userAgent;
    }

    public JSONObject getJsonObjectOfAllRatpData() throws IOException, ParseException {
        /* Declare the url and HttpClient object */
        URL url = new URL(_urlGet);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        /* Configuration of HttpClient */
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", _userAgent);

        /* Test the exception of response */
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code : " + responseCode);

        // Success !
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Throw json data treatment
            InputStreamReader input = new InputStreamReader(con.getInputStream());

            // Declare JsonParser
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(input);

            // Get Json result
            return (JSONObject) obj;

        } else {
            System.out.println("GET request not worked" + responseCode);
            return new JSONObject();
        }
    }
}
