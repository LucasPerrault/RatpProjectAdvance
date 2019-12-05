package HttpUrlConnection;

import java.io.BufferedReader;
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

    public static String sendGET() throws IOException {
        /* Declare the url and HttpClient object */
        URL obj = new URL(_urlGet);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

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
            BufferedReader in = new BufferedReader(input);
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Read result
           return response.toString();
        } else {
            System.out.println("GET request not worked");
            return "";

        }
    }
}
