import HttpUrlConnection.HttpUrlConnection;
import RatpServices.RatpDataService;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class main {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "http://vasyenmetro.com/data/reseau.json";

    public static void main(String[] args) throws IOException, ParseException {
        RatpDataService ratpDataService = new RatpDataService(GET_URL, USER_AGENT);
        System.out.println(ratpDataService.getRatpLines());
    }
}
