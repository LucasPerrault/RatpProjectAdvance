package GraphAlgorithms.WeightedAlgorithmGraph;

import GraphAlgorithms.AlgorithmGraph;
import DistanceHeuristic.DistanceHeuristic;
import DistanceHeuristic.ManhattanDistance;
import DistanceHeuristic.StopComparator;
import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.util.*;

public class AStar extends AbstractAlgorithmWeightedGraph
{
    DistanceHeuristic<Stop> _heuristicDistance = new ManhattanDistance();

    public AStar(Stop src, Stop dest, NetworkTransport networkTransport)
    {
        super(src, dest, networkTransport);
    }

    public void init() {
        // Initialization of priority queue.
        // With comparator which contains the distance heuristic
        PriorityQueue<Stop> priorityQueue = new PriorityQueue<Stop>(
                1000,
                new StopComparator(_heuristicDistance, _dest)
        );

        // Create optional priority queueu in order to use Dijkstra
        Optional<PriorityQueue<Stop>> priorityQueueOptional = Optional.of(priorityQueue);
        new Dijkstra(_src, _dest, _networkTransport, priorityQueueOptional);
    }
}
