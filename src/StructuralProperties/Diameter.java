package StructuralProperties;

import Factory.RatpFactory;
import GraphAlgorithms.AlgorithmGraph;
import GraphAlgorithms.BasicAlgorithmGraph.BreadthFirstSearch;
import GraphAlgorithms.WeightedAlgorithmGraph.Dijkstra;
import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.util.Optional;

public class Diameter extends AbstractStructuralPropertie
{
    private RatpFactory _ratpFactory = new RatpFactory();

    public Diameter(NetworkTransport networkTransport)
    {
        _ratpFactory.inputGraphTypeForEccentricity();
        int index = _ratpFactory._indexOfGraphType;
        switch (index) {
            case(1):
                unweightedGraphDiameter(networkTransport);
                break;
            case(2):
                weightedGraphDiameter(networkTransport);
                break;
        }
    }

    private void weightedGraphDiameter(NetworkTransport networkTransport)
    {

        for (Stop stop: networkTransport.getStops())
        {
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
        }
    }

    private void unweightedGraphDiameter(NetworkTransport networkTransport)
    {
        for (Stop stop: networkTransport.getStops())
        {
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
        }
    }
}
