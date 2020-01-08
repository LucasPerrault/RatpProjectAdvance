package GraphAlgorithms.DistanceHeuristic;

import HttpUrlConnection.model.Stop;

public class ManhattanDistance implements DistanceHeuristic<Stop> {
    public int distance(Stop v0, Stop v1) {
        // return the estimation of shortestPath
        return (Math.abs(Integer.parseInt(v0.getLat()) - Integer.parseInt(v1.getLat())) +
                Math.abs(Integer.parseInt(v0.getLng()) - Integer.parseInt(v1.getLng())));
    }
}