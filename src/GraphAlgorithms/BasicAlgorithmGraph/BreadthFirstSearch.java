package GraphAlgorithms.BasicAlgorithmGraph;

import GraphAlgorithms.AlgorithmGraph;
import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.util.*;

public class BreadthFirstSearch implements AlgorithmGraph
{
    private Stop _src;
    private Stop _dest;
    private NetworkTransport _networkTransport;
    private Stack<Stop> _predessorStopsPath;

    public BreadthFirstSearch(Stop src, Stop dest, NetworkTransport networkTransport)
    {
        _src = src;
        _dest = dest;
        _networkTransport = networkTransport;
    }

    public void init() {
        Queue<Stop> evaluatedQueue = new LinkedList<Stop>();
        HashMap<Stop, Boolean> cellAlreadyVisited = new HashMap<Stop, Boolean>();

        /* Initialization of differents queues */
        evaluatedQueue.add(_src);
        _predessorStopsPath.add(_src);
        cellAlreadyVisited.put(_src, true);

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

    public List<Stop> getShortestPath() {

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

            if (_networkTransport.getAjdacentsByStop(currentDestination).contains(lastStopSave)) {
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
}
