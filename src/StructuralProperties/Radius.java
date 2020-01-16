package StructuralProperties;

import Factory.RatpFactory;
import GraphAlgorithms.BasicAlgorithmGraph.BreadthFirstSearch;
import GraphAlgorithms.WeightedAlgorithmGraph.Dijkstra;
import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Radius extends AbstractStructuralPropertie
{
    private RatpFactory _ratpFactory = new RatpFactory();

    public Radius(NetworkTransport networkTransport)
    {
        _ratpFactory.inputGraphTypeForEccentricity();
        int index = _ratpFactory._indexOfGraphType;
        Queue<Stop> stopQueue = new LinkedList<Stop>(networkTransport.getStops());
        switch (index) {
            case(1):
                unweightedGraphRadius(networkTransport, stopQueue);
                break;
            case(2):
                weightedGraphRadius(networkTransport, stopQueue);
                break;
        }
    }

    private void weightedGraphRadius(NetworkTransport networkTransport, Queue<Stop> stopQueue)
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

        if (_lengthPath > eccentricity.getLength())
        {
            _lengthPath = eccentricity.getLength();
            _path = eccentricity.getPath();
        }
    }

    private void unweightedGraphRadius(NetworkTransport networkTransport, Queue<Stop> stopQueue)
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

        if (_lengthPath > eccentricity.getLength())
        {
            _lengthPath = eccentricity.getLength();
            _path = eccentricity.getPath();
        }
    }
}
