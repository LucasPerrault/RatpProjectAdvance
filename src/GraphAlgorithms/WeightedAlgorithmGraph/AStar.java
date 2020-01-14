package GraphAlgorithms.WeightedAlgorithmGraph;

import DistanceHeuristic.DistanceHeuristic;
import DistanceHeuristic.ManhattanDistance;
import DistanceHeuristic.StopComparatorForShortestDistance;
import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.util.*;

public class AStar extends AbstractAlgorithmWeightedGraph
{
    Stop _dest;
    DistanceHeuristic<Stop> _heuristicDistance = new ManhattanDistance();

    public AStar(Stop src, Stop dest, NetworkTransport networkTransport)
    {
        super(src, networkTransport);
        _dest = dest;
    }

    public void init(Boolean withoutRecursivity) {
        // Initialization of priority queue.
        // With comparator which contains the distance heuristic
        PriorityQueue<Stop> priorityQueue = new PriorityQueue<Stop>(
                1000,
                new StopComparatorForShortestDistance(_heuristicDistance, _dest)
        );

        // Create optional priority queueu in order to use Dijkstra
        Optional<PriorityQueue<Stop>> priorityQueueOptional = Optional.of(priorityQueue);
        new Dijkstra(_src, _networkTransport, priorityQueueOptional);
    }
}
