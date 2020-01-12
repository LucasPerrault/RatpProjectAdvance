package GraphAlgorithms.WeightedAlgorithmGraph;

import GraphAlgorithms.AlgorithmGraph;
import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractAlgorithmWeightedGraph implements AlgorithmGraph
{
    protected Stop _src;
    protected Stop _dest;
    protected NetworkTransport _networkTransport;

    public AbstractAlgorithmWeightedGraph(Stop src, Stop dest, NetworkTransport networkTransport)
    {
        _src = src;
        _dest = dest;
        _networkTransport = networkTransport;
    }

    public double getShortestPathLength(List<Stop> stops)
    {
        double shortestPathLength = 0;
        for (Stop stop: stops)
        {
            shortestPathLength = shortestPathLength + stop.getDistanceBetween(_networkTransport.getPredecessorByStop(stop));

        }
        return shortestPathLength;
    }

    public void printShortestPath(List<Stop> stops, NetworkTransport networkTransport)
    {
        System.out.println("\n The shortest path of " + _src.getName() + " to " + _dest.getName() + " is : \n");
        for (Stop stop: stops)
        {
            System.out.println("- " + stop.getName() + " with distance of " + networkTransport.getTravelledDistanceByStop(stop) + "km.");
        }
        System.out.println("The total length of shortest path is " + getShortestPathLength(stops) + "km.");
    }

    public List<Stop> getShortestPath() {

        // Initialization
        List<Stop> shortestPath = new LinkedList<Stop>();
        double shortestPathLength = 0;
        Stop predecessor = _dest;
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
