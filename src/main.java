import Factory.RatpFactory;
import GraphAlgorithms.WeightedAlgorithmGraph.AStar;
import HttpUrlConnection.model.Line;
import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;
import RatpServices.RatpDataService;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

public class main {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "http://vasyenmetro.com/data/reseau.json";

    public static void main(String[] args) throws IOException, ParseException {
        RatpDataService ratpDataService = new RatpDataService(GET_URL, USER_AGENT);
        ArrayList<Stop> stops = ratpDataService.getRatpData("stations" );
        ArrayList<Line> lines = ratpDataService.getRatpData("lignes" );
        NetworkTransport networkTransport = new NetworkTransport(lines, stops);

        RatpFactory ratpFactory = new RatpFactory(lines, stops);
        Stop srcStop = ratpFactory.getStopFactory();
        Stop destStop = ratpFactory.getStopFactory();

        AStar AStar = new AStar(srcStop, destStop, networkTransport);
        AStar.init();
        AStar.getShortestPath();

        //System.out.println("\n\n");

        /*Dijkstra dijkstra = new Dijkstra(srcStop, destStop, networkTransport, Optional.empty());
        dijkstra.init();
        dijkstra.getShortestPath();*/

        //System.out.println("\n\n");

        //BreadthFirstSearch BFS = new BreadthFirstSearch(srcStop, destStop, networkTransport);
        //BFS.init()
        //System.out.println(BFS.getPredecessorStopsPathWithBFS(srcStop));
    }
}
