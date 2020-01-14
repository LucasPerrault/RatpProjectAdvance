package DistanceHeuristic;

import HttpUrlConnection.model.Stop;

import java.util.Comparator;

public class StopComparatorForShortestDistance implements Comparator<Stop>
{
    private DistanceHeuristic<Stop> _distanceHeuristic;
    private Stop _destination;

    public StopComparatorForShortestDistance(DistanceHeuristic<Stop> h, Stop dest) {
        _distanceHeuristic = h;
        _destination = dest;
    }

    public int compare(Stop c0, Stop c1) {
        return compareWithDistanceHeuristic(c0, c1);
    }

    public int compareWithDistanceHeuristic(Stop c0, Stop c1) {
        boolean isSuperiorOfTheFirstStop = _distanceHeuristic.distance(c0, _destination)
                < _distanceHeuristic.distance(c1, _destination);
        boolean isInferiorOfTheFirstStop = _distanceHeuristic.distance(c0, _destination)
                > _distanceHeuristic.distance(c1, _destination);

        if (isSuperiorOfTheFirstStop) {
            return -1;
        } else if (isInferiorOfTheFirstStop){
            return 1;
        } else {
            return 0;
        }
    }
}

