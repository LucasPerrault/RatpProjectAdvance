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
import Ui.SimpleMap;
import javafx.application.Application;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

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



        // B. Algorithms and Shortest Path
            // B.0 Stop Factory :
        RatpFactory ratpFactory = new RatpFactory();
        Stop src = ratpFactory.getStopFactory(stops);
        Stop dest = ratpFactory.getStopFactory(stops);

            // B.1 Graph Factory :
        AlgorithmGraph algorithmGraph = ratpFactory.getAlgorithmFactory(networkTransport, src, dest);


        // B.2 Initialization of algorithm with

                // B.2.1 Recursivity. (check boolean value)
        long startTime = System.currentTimeMillis();
        algorithmGraph.init(false); // Recursif Algorithm
        long stopTime = System.currentTimeMillis();
        System.out.println("\nPermorfance algorithm recursif : " + (stopTime - startTime) + "ms\n");

                // B.2.2 Non-Recursivity. (check boolean value)
        long startTimeIt = System.currentTimeMillis();
        algorithmGraph.init(true); // Non-recursif algorithm
        long stopTimeIt = System.currentTimeMillis();
        System.out.println("\nPermorfance algorithm non-recursif : " + (stopTimeIt - startTimeIt) + "ms\n");

        // B.3 Shortest path and length.
        List<Stop> shortestPath = algorithmGraph.getShortestPath(dest);
        double length = algorithmGraph.getShortestPathLength(shortestPath);

        // B.4 Print them
        algorithmGraph.printShortestPathAndLength(shortestPath, length, dest);

        //Ui interaction
        SimpleMap map = new SimpleMap(shortestPath, stops, lines, networkTransport);
        map.main();

        // C. Structural properties
        // C.1 Eccentricity
        System.out.println("\n\nEccentricity part : ----------------------------------------------\n");
        Eccentricity eccentricity = new Eccentricity(networkTransport, src, Optional.empty());
        List<Stop> eccentricityPath = eccentricity.getPath();
        double eccentricityLength = eccentricity.getLength();

        eccentricity.printPathAndLength(eccentricityPath, eccentricityLength);

            // C.2 Diameter
        System.out.println("\n\nDiameter part : ------------------------------------------------- \n");
        long startTimeDiameter = System.currentTimeMillis();
        Diameter diameter = new Diameter(networkTransport);
        List<Stop> diameterPath = diameter.getPath();
        double diameterLength = diameter.getLength();
        long stopTimeDiameter = System.currentTimeMillis();
        System.out.println("\nPermorfance algorithm : " + (stopTimeDiameter - startTimeDiameter ) + "ms\n");

        diameter.printPathAndLength(diameterPath, diameterLength);

            // C.3 Radius
        System.out.println("\n\nRadius part : ----------------------------------------------------\n");
        long startTimeRadius = System.currentTimeMillis();
        Radius radius = new Radius(networkTransport);
        List<Stop> radiusPath = radius.getPath();
        double radiusLength = radius.getLength();

        long stopTimeRadius = System.currentTimeMillis();
        System.out.println("\nPermorfance algorithm : " + (stopTimeRadius - startTimeRadius) + "ms\n");

        radius.printPathAndLength(radiusPath, radiusLength);
    }
}
