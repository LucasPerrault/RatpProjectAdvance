package StructuralProperties;

import Factory.RatpFactory;
import GraphAlgorithms.AlgorithmGraph;
import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.util.*;

public class Eccentricity extends AbstractStructuralPropertie
{
    private RatpFactory _ratpFactory = new RatpFactory();
    private NetworkTransport _networkTransport;
    private Stop _src;
    private Optional<AlgorithmGraph> _algorithmGraphOptional;

    public Eccentricity(NetworkTransport networkTransport, Stop src, Optional<AlgorithmGraph> algorithmGraphOptional)
    {
        _networkTransport = networkTransport;
        _src = src;
        _algorithmGraphOptional = algorithmGraphOptional;

        AlgorithmGraph algorithmGraph;
        if (_algorithmGraphOptional.isEmpty())
        {
            algorithmGraph = _ratpFactory.getAlgorithmForEccentricityFactory(_networkTransport, _src);
        } else {
            algorithmGraph = _algorithmGraphOptional.get();
        }
        algorithmGraph.init(false);

        Queue<Stop> stopsQueue = new LinkedList<Stop>(networkTransport.getStops());
        init(stopsQueue, algorithmGraph);

    }

    private void init(Queue<Stop> stopQueue, AlgorithmGraph algorithmGraph)
    {

        if (stopQueue.isEmpty()) {
            return;
        }

        Stop dest = stopQueue.poll();

        List<Stop> newStopListPath = algorithmGraph.getShortestPath(dest);
        double newLengthPath = algorithmGraph.getShortestPathLength(newStopListPath);

        if (_lengthPath < newLengthPath) {
            _lengthPath = newLengthPath;
            _path = newStopListPath;
        }

        init(stopQueue, algorithmGraph);
    }

}
