package StructuralProperties;

import DistanceHeuristic.DistanceHeuristic;
import DistanceHeuristic.ManhattanDistance;
import DistanceHeuristic.StopComparatorForLongestDistance;
import DistanceHeuristic.StopComparatorForShortestDistance;
import GraphAlgorithms.WeightedAlgorithmGraph.Dijkstra;
import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.util.*;

public class Eccentricity implements StructualPropertie
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
        Dijkstra dijkstra = new Dijkstra(_src, _networkTransport, Optional.empty());
        dijkstra.init(true);
        for (Stop dest: _networkTransport.getStops())
        {
            List<Stop> newStopListPath = dijkstra.getShortestPath(dest);
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
