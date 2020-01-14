package GraphAlgorithms.WeightedAlgorithmGraph;

import GraphAlgorithms.AlgorithmGraph;
import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractAlgorithmWeightedGraph implements AlgorithmGraph
{
    protected Stop _src;
    protected NetworkTransport _networkTransport;

    public AbstractAlgorithmWeightedGraph(Stop src, NetworkTransport networkTransport)
    {
        _src = src;
        _networkTransport = networkTransport;
    }

    public double getShortestPathLength(List<Stop> stops)
    {
        double shortestPathLength = 0;
        for (Stop stop: stops)
        {
            if (!(_networkTransport.getTravelledDistanceByStop(stop) == 0))
            {
                shortestPathLength = shortestPathLength + stop.getDistanceBetween(_networkTransport.getPredecessorByStop(stop));
            }

        }
        return shortestPathLength;
    }

    public void printShortestPath(List<Stop> stops, Stop dest)
    {
        Collections.reverse(stops);
        System.out.println("\n The shortest path of " + _src.getName() + " to " + dest.getName() + " is : \n");
        for (Stop stop: stops)
        {
            System.out.println("- " + stop.getName() + " with distance of " + _networkTransport.getTravelledDistanceByStop(stop) + "km.");
        }
        System.out.println("The total length of shortest path is " + getShortestPathLength(stops) + "km.");
    }

    public List<Stop> getShortestPath(Stop dest) {

        // Initialization
        List<Stop> shortestPath = new LinkedList<Stop>();
        double shortestPathLength = 0;
        Stop predecessor = dest;
        shortestPath.add(predecessor);

        // Read all the predecessors fill by the algorithm
        while(!predecessor.equals(_src)) {
            predecessor = _networkTransport.getPredecessorByStop(predecessor);
            shortestPath.add(predecessor);
        }

        // return the path
        return shortestPath;
    }
}
