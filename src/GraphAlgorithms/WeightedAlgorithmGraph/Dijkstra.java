package GraphAlgorithms.WeightedAlgorithmGraph;

import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;
import java.util.*;

public class Dijkstra extends AbstractAlgorithmWeightedGraph
{
    Optional<PriorityQueue<Stop>> _optionalPriorityQueue;

    public Dijkstra(Stop src, Stop dest, NetworkTransport networkTransport, Optional<PriorityQueue<Stop>> optionalPriorityQueue)
    {
        super(src, dest, networkTransport);
        _optionalPriorityQueue = optionalPriorityQueue;
        init();
    }

    public void init()
    {
        // Initialization of priority queue.
        PriorityQueue<Stop> priorityQueue;
        if (_optionalPriorityQueue.isPresent())
        {
            priorityQueue = _optionalPriorityQueue.get();
        } else {
            priorityQueue = new PriorityQueue<>();
        }
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
                    double adjacentDistance = _networkTransport.getTravelledDistanceByStop(adjacent);

                    // Calculate the new distance
                    double actualDistance = _networkTransport.getTravelledDistanceByStop(actualStop);

                    double newDistance = actualDistance + actualStop.getDistanceBetween(adjacent);

                    // Check if the newDistance is inferior of adjacentDistance
                    if( newDistance < adjacentDistance)
                    {
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

}
