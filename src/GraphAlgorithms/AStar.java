package GraphAlgorithms;

import GraphAlgorithms.DistanceHeuristic.DistanceHeuristic;
import GraphAlgorithms.DistanceHeuristic.StopComparator;
import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.util.*;

public class AStar
{
    Stop _src;
    Stop _dest;
    NetworkTransport _networkTransport;
    DistanceHeuristic<Stop> _heuristicDistance;
    Dijkstra _dijkstraClass;


    public AStar(Stop src, Stop dest, NetworkTransport networkTransport)
    {
        _src = src;
        _dest = dest;
        _networkTransport = networkTransport;
        init();
    }

    private void init() {
        // Initialization of priority queue.
        // With comparator which contains the distance heuristic
        PriorityQueue<Stop> priorityQueue = new PriorityQueue<Stop>(
                1000,
                new StopComparator(_heuristicDistance, _dest)
        );

        // Create optional priority queueu in order to use Dijkstra
        Optional<PriorityQueue<Stop>> priorityQueueOptional = new Optional<>(priorityQueue);
        _dijkstraClass = new Dijkstra(_src, _dest, _networkTransport, priorityQueueOptional);
    }

    public List<Stop> getShortestPath() {
        return _dijkstraClass.getShortestPath();
    }

}
