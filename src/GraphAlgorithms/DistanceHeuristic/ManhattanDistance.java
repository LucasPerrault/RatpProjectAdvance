package GraphAlgorithms.DistanceHeuristic;

import HttpUrlConnection.model.Stop;

public class ManhattanDistance implements DistanceHeuristic<Stop> {
    public double distance(Stop v0, Stop v1) {
        // return the estimation of shortestPath
        return (Math.abs(Double.parseDouble(v0.getLat()) - Double.parseDouble(v1.getLat())) +
                Math.abs(Double.parseDouble(v0.getLng()) - Double.parseDouble(v1.getLng())));
    }
}