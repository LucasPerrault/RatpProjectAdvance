package GraphAlgorithms;

import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.util.*;

public class Dijkstra
{
    NetworkTransport _networkTransport;
    Stop _src;
    Stop _dest;

    public Dijkstra(Stop src, Stop dest, NetworkTransport networkTransport)
    {
        _src = src;
        _dest = dest;
        _networkTransport = networkTransport;
        init();
    }

    private void init()
    {
        // Initialization of priority queue.
        PriorityQueue<Stop> priorityQueue = new PriorityQueue<Stop>();
        HashMap<Stop, Boolean> cellAreadyVisited = new HashMap<Stop, Boolean>();
        priorityQueue.add(_src);
        cellAreadyVisited.put(_src, true);

        // Init of distance to INFINITE for all.
        _networkTransport.resetDistanceTraveledByStops();

        // Set distance 0 for source
        _networkTransport.setTravelledDistanceByStop(_src, 0.0);

        while(!priorityQueue.isEmpty() ){
            // Getting the minimum distance vertex from priority queue
            Stop actualStop = priorityQueue.poll();

            // Treatment for all adjacents
            for(Stop adjacent: _networkTransport.getAjdacentsByStop(actualStop)) {

                // Check if it's already visited
                if(!cellAreadyVisited.containsKey(adjacent))
                {
                    // Calculate the distance of next vertex
                    double adjacentDistance = actualStop.getDistanceBetween(adjacent);

                    // Calculate the new distance
                    double actualDistance = _networkTransport.getTravelledDistanceByStop(actualStop);
                    double newDistance = actualDistance + adjacentDistance;

                    // Check if the newDistance is inferior of adjacentDistance
                    if( newDistance < adjacentDistance){

                        // Safety
                        priorityQueue.remove(adjacent);

                        // The value of adjacent's distance become the new distance
                        _networkTransport.setTravelledDistanceByStop(adjacent, newDistance);

                        // Saved the predecessor of adjacent
                        _networkTransport.setPredecessorStops(adjacent, actualStop);

                        // Add it to priority queue in order to treat it.
                        priorityQueue.add(adjacent);
                    }
                }
            }
            cellAreadyVisited.put(actualStop, true);
        }
    }

    public List<Stop> getShortestPath() {

        // Initialization
        List<Stop> shortestPath = new LinkedList<Stop>();
        Stop predecessor = _dest;
        shortestPath.add(predecessor);

        // Read all the predecessors fill by the algorithm
        while(!predecessor.equals(_src)) {
            predecessor = _networkTransport.getPredecessorByStop(predecessor);
            shortestPath.add(predecessor);
        }

        // Reverse the path for reading in the good sens
        Collections.reverse(shortestPath);

        // return the path
        return shortestPath;
    }

}
