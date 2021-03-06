import Factory.RatpFactory;
import GraphAlgorithms.AlgorithmGraph;
import GraphAlgorithms.BasicAlgorithmGraph.BreadthFirstSearch;
import GraphAlgorithms.BasicAlgorithmGraph.DepthFirstSearch;
import GraphAlgorithms.WeightedAlgorithmGraph.AStar;
import HttpUrlConnection.model.Line;
import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;
import RatpServices.RatpDataService;
import StructuralProperties.Diameter;
import StructuralProperties.Eccentricity;
import StructuralProperties.Radius;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

public class main {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "http://vasyenmetro.com/data/reseau.json";

    public static void main(String[] args) throws IOException, ParseException {

        // A. Collection of data and graph construction.
            // A.1 Get data of API
        RatpDataService ratpDataService = new RatpDataService(GET_URL, USER_AGENT);
        ArrayList<Stop> stops = ratpDataService.getRatpData("stations" );
        ArrayList<Line> lines = ratpDataService.getRatpData("lignes" );

            // A.2 Graph construction in UI
        NetworkTransport networkTransport = new NetworkTransport(lines, stops);

        /*Remy's party*/


        // B. Algorithms and Shortest Path
            // B.0 Stop Factory :
        RatpFactory ratpFactory = new RatpFactory();
        Stop src = ratpFactory.getStopFactory(stops);
        Stop dest = ratpFactory.getStopFactory(stops);


            // B.1 Graph Factory :
        AlgorithmGraph algorithmGraph = ratpFactory.getAlgorithmFactory(networkTransport, src, dest);

            // B.2 Initialization of algorithm with

                // B.2.1 Recursivity. (check boolean value)
        algorithmGraph.init(false); // Recursif Algorithm

                // B.2.2 Non-Recursivity. (check boolean value)
        algorithmGraph.init(true); // Non-recursif algorithm

            // B.3 Shortest path and length.
        List<Stop> shortestPath = algorithmGraph.getShortestPath(dest);
        double length = algorithmGraph.getShortestPathLength(shortestPath);

            // B.4 Print them
        algorithmGraph.printShortestPathAndLength(shortestPath, length, dest);

        // C. Structural properties
            // C.1 Eccentricity
        System.out.println("\n\nEccentricity part : \n");
        Eccentricity eccentricity = new Eccentricity(networkTransport, src, Optional.empty());
        List<Stop> eccentricityPath = eccentricity.getPath();
        double eccentricityLength = eccentricity.getLength();

        eccentricity.printPathAndLength(eccentricityPath, eccentricityLength);

            // C.2 Diameter
        System.out.println("\n\nDiameter part : \n");
        Diameter diameter = new Diameter(networkTransport);
        List<Stop> diameterPath = diameter.getPath();
        double diameterLength = diameter.getLength();

        diameter.printPathAndLength(diameterPath, diameterLength);

            // C.3 Radius
        System.out.println("\n\nRadius part : \n");
        Radius radius = new Radius(networkTransport);
        List<Stop> radiusPath = radius.getPath();
        double radiusLength = radius.getLength();

        radius.printPathAndLength(radiusPath, radiusLength);
    }
}
