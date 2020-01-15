package GraphAlgorithms.BasicAlgorithmGraph;

import GraphAlgorithms.AlgorithmGraph;
import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.util.*;

public class BreadthFirstSearch implements AlgorithmGraph
{
    private Stop _src;
    private NetworkTransport _networkTransport;
    private Stack<Stop> _predessorStopsPath = new Stack<Stop>();

    public BreadthFirstSearch(Stop src, NetworkTransport networkTransport)
    {
        _src = src;
        _networkTransport = networkTransport;
    }

    public void init(Boolean withoutRecursivity) {
        Queue<Stop> evaluatedQueue = new LinkedList<Stop>();
        HashMap<Stop, Boolean> cellAlreadyVisited = new HashMap<Stop, Boolean>();

        /* Initialization of differents queues */
        evaluatedQueue.add(_src);
        _predessorStopsPath.add(_src);
        cellAlreadyVisited.put(_src, true);

        long startTime = System.currentTimeMillis();
        if (withoutRecursivity) {
            BFSWithoutRecursivity(evaluatedQueue, cellAlreadyVisited);
        } else {
            BFSRecursive(evaluatedQueue, cellAlreadyVisited);
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("\nPermorfance of BFS algorithm : " + (stopTime - startTime) + "ms\n");
    }

    private void BFSRecursive(Queue<Stop> evaluatedQueue,  HashMap<Stop, Boolean> cellAlreadyVisited)
    {
        if (evaluatedQueue.isEmpty())
        {
            return;
        }

        Stop stopInFrontOfTheQueue = evaluatedQueue.poll();
        List<Stop> adjacentList = _networkTransport.getAjdacentsByStop(stopInFrontOfTheQueue);

        /* Show the adjacents of vertex which is evaluating */
        for (Stop adjacent : adjacentList) {

            /* Check if it's already visited */
            if (!cellAlreadyVisited.containsKey(adjacent)) {
                evaluatedQueue.add(adjacent);
                cellAlreadyVisited.put(adjacent, true);
                _predessorStopsPath.add(adjacent);
            }
        }
        BFSRecursive(evaluatedQueue, cellAlreadyVisited);
    }

    private void BFSWithoutRecursivity(Queue<Stop> evaluatedQueue,  HashMap<Stop, Boolean> cellAlreadyVisited)
    {
        /* Treatment of potential stop visited */
        while(!evaluatedQueue.isEmpty()) {
            Stop stopInFrontOfTheQueue = evaluatedQueue.poll();
            List<Stop> adjacentList = _networkTransport.getAjdacentsByStop(stopInFrontOfTheQueue);

            /* Show the adjacents of vertex which is evaluating */
            for (Stop adjacent : adjacentList) {

                /* Check if it's already visited */
                if (!cellAlreadyVisited.containsKey(adjacent)) {
                    evaluatedQueue.add(adjacent);
                    cellAlreadyVisited.put(adjacent, true);
                    _predessorStopsPath.add(adjacent);
                }
            }
        }
    }

    public List<Stop> getShortestPath(Stop dest) {
        /* Check if the path is not empty */
        if (_predessorStopsPath.isEmpty()) return null;

        /* Initialization */
        ArrayList<Stop> shortestPathListToDestination = new ArrayList<Stop>();
        Stop currentDestination = dest;
        shortestPathListToDestination.add(dest);
        Stack<Stop> _savedPredecessorStops = (Stack<Stop>)_predessorStopsPath.clone();

        /* Treatment */
        while(!_savedPredecessorStops.isEmpty()) {
            Stop lastStopSave = _savedPredecessorStops.pop();

            List<Stop> adjacentStops = _networkTransport.getAjdacentsByStop(currentDestination);
            boolean isAdjacent = adjacentStops.contains(lastStopSave);

            if (isAdjacent) {
                shortestPathListToDestination.add(lastStopSave);
                currentDestination = lastStopSave;
            }
        }

        /* Return the shortest path */
        Collections.reverse(shortestPathListToDestination);

        return shortestPathListToDestination;
    }

    public double getShortestPathLength(List<Stop> stops) {
        return stops.size();
    }

    public boolean areConnected(Stop dest)
    {
        /* Check if dest exist on the path */
        if (_predessorStopsPath.contains(dest)) {
            return true;
        } else {
            return false;
        }
    }

    public void printShortestPathAndLength(List<Stop> stops, double length, Stop dest)
    {
        System.out.println("\n The shortest path of " + _src.getName() + " to " + dest.getName() + " is : \n");
        for (Stop stop: stops)
        {
            System.out.println(stop.getName());
        }
        System.out.println("Le nombre de station visité est : " + length);
    }
}
