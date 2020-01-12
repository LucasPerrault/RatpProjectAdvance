package StructuralProperties;

import DistanceHeuristic.ManhattanDistance;
import DistanceHeuristic.StopComparatorForLongestDistance;
import GraphAlgorithms.WeightedAlgorithmGraph.Dijkstra;
import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.util.*;

public class Eccentricity
{
    private NetworkTransport _networkTransport;
    private Stop _src;
    private List<Stop> _longestStopListPath;
    private double _longestLengthPath = 0;

    public Eccentricity(NetworkTransport networkTransport, Stop src)
    {
        _networkTransport = networkTransport;
        _src = src;
        init();
    }

    private void init()
    {
        for (Stop dest: _networkTransport.getStops())
        {
            PriorityQueue<Stop> priorityQueue = new PriorityQueue<Stop>(
                    1000,
                    new StopComparatorForLongestDistance(new ManhattanDistance(), dest)
            );

            // Create optional priority queueu in order to use Dijkstra
            Optional<PriorityQueue<Stop>> priorityQueueOptional = Optional.of(priorityQueue);
            Dijkstra dijkstra = new Dijkstra(_src, dest, _networkTransport, priorityQueueOptional);

            List<Stop> newStopListPath = dijkstra.getShortestPath();
            double newLengthPath = dijkstra.getShortestPathLength(newStopListPath);

            if (_longestLengthPath < newLengthPath)
            {
                _longestLengthPath = newLengthPath;
                _longestStopListPath = newStopListPath;
            }
        }
    }

    public double getLength()
    {
        return _longestLengthPath;
    }

    public List<Stop> getPath()
    {
        return _longestStopListPath;
    }
}
