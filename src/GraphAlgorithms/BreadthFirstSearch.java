package GraphAlgorithms;

import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.lang.reflect.Array;
import java.util.*;

public class BreadthFirstSearch
{

    NetworkTransport _networkTransport;
    Stop _src;
    Stop _dest;

    Stack<Stop> _predessorStopsPath;


    public BreadthFirstSearch(Stop src, Stop dest, NetworkTransport networkTransport)
    {
        _networkTransport = networkTransport;
        _src = src;
        _dest = dest;
        _predessorStopsPath = getPredecessorStopsPathWithBFS(src);
    }

    public String getShortestPathOfStopsToString()
    {
        String result = "";
        for (Stop stop: getShortestPathOfStops()) {
            result.concat(stop.getName() + "\n");
        }
        return result;
    }

    public List<Stop> getShortestPathOfStops() {

        /* Check if the path is not empty */
        if (_predessorStopsPath.isEmpty()) return null;

        /* Initialization */
        ArrayList<Stop> shortestPathListToDestination = new ArrayList<Stop>();
        Stop currentDestination = _dest;

        /* Treatment */
        while(!_predessorStopsPath.isEmpty()) {
            Stop lastStopSave = _predessorStopsPath.pop();

            if (lastStopSave.equals(_dest)) {
                shortestPathListToDestination = new ArrayList<Stop>();
            }

            if (currentDestination.isAdjacent(lastStopSave)) {
                shortestPathListToDestination.add(lastStopSave);
                currentDestination = lastStopSave;
            }
        }

        /* Return the shortest path */
        Collections.reverse(shortestPathListToDestination);
        return shortestPathListToDestination;
    }

    public boolean areConnected()
    {
        /* Check if dest exist on the path */
        if (_predessorStopsPath.contains(_dest)) {
            return true;
        } else {
            return false;
        }
    }

    private Stack<Stop> getPredecessorStopsPathWithBFS(Stop src) {
        Queue<Stop> evaluatedQueue = new LinkedList<Stop>();
        HashMap<Stop, Boolean> cellAlreadyVisited = new HashMap<Stop, Boolean>();
        Stack<Stop> savedQueue = new Stack<Stop>();

        /* Initialization of differents queues */
        evaluatedQueue.add(src);
        savedQueue.add(src);
        cellAlreadyVisited.put(src, true);

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
                    savedQueue.add(adjacent);
                }
            }
        }

        /* return savedQueue at the end */
        return savedQueue;
    }
}
