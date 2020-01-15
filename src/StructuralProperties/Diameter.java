package StructuralProperties;

import Factory.RatpFactory;
import GraphAlgorithms.AlgorithmGraph;
import GraphAlgorithms.BasicAlgorithmGraph.BreadthFirstSearch;
import GraphAlgorithms.WeightedAlgorithmGraph.Dijkstra;
import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.util.*;

public class Diameter extends AbstractStructuralPropertie
{
    private RatpFactory _ratpFactory = new RatpFactory();

    public Diameter(NetworkTransport networkTransport)
    {
        _ratpFactory.inputGraphTypeForEccentricity();
        Queue<Stop> stopsQueue = new LinkedList<>(networkTransport.getStops());

        int index = _ratpFactory._indexOfGraphType;
        switch (index) {
            case(1):
                unweightedGraphDiameter(networkTransport, stopsQueue);
                break;
            case(2):
                weightedGraphDiameter(networkTransport, stopsQueue);
                break;
        }
    }

    private void weightedGraphDiameter(NetworkTransport networkTransport, Queue<Stop> stopQueue)
    {

        if (stopQueue.isEmpty()) {
            return;
        }

        Stop stop = stopQueue.poll();

        Eccentricity eccentricity = new Eccentricity(
                networkTransport,
                stop,
                Optional.of(new Dijkstra(stop, networkTransport, Optional.empty()))
        );

        if (_lengthPath < eccentricity.getLength())
        {
            _lengthPath = eccentricity.getLength();
            _path = eccentricity.getPath();
        }
        weightedGraphDiameter(networkTransport, stopQueue);
    }

    private void unweightedGraphDiameter(NetworkTransport networkTransport, Queue<Stop> stopQueue)
    {
        if (stopQueue.isEmpty()) {
            return;
        }

        Stop stop = stopQueue.poll();

        Eccentricity eccentricity = new Eccentricity(
                networkTransport,
                stop,
                Optional.of(new BreadthFirstSearch(stop, networkTransport))
        );

        if (_lengthPath < eccentricity.getLength())
        {
            _lengthPath = eccentricity.getLength();
            _path = eccentricity.getPath();
        }
        unweightedGraphDiameter(networkTransport, stopQueue);
    }
}
