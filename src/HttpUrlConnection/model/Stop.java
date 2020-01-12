package HttpUrlConnection.model;

import GraphAlgorithms.DistanceHeuristic.StopComparator;
import Helpers.MathHelpers;

import java.util.ArrayList;

public class Stop implements Comparable<Stop>{

    private String _commune;
    private String _lat;
    private String _lng;
    private ArrayList<String> _lineIds;
    private String _name;
    private String _num;
    private String _type;
    private Boolean _isHub;

    public Stop(String commune, String lat, String lng, ArrayList<String> lineIds, String name, String num, String type, Boolean isHub)
    {
        _commune = commune;
        _lat = lat;
        _lng = lng;
        _lineIds = lineIds;
        _name = name;
        _num = num;
        _type = type;
        _isHub = isHub;
    }

    public String getName()
    {
        return _name;
    }

    public String getLat()
    {
        return _lat;
    }

    public String getLng()
    {
        return _lng;
    }

    public ArrayList<String> getLineIds()
    {
        return _lineIds;
    }

    public String getType()
    {
        return _type;
    }

    public String getNum()
    {
        return _num;
    }

    public boolean isAdjacent(Stop potentialAdjacentStop, ArrayList<Line> lines)
    {
        boolean isAdjacent = false;
        for (Line line: lines)
        {
            for (ArrayList<String> stopIdsList: line.getStopIds())
            {
                int currentIndexStop = stopIdsList.indexOf(this);
                String nextStopIds = stopIdsList.get(currentIndexStop + 1);
                String previousStopIds = stopIdsList.get(currentIndexStop - 1);

                if (nextStopIds.equals(potentialAdjacentStop.getNum()) || previousStopIds.equals(potentialAdjacentStop.getNum()))
                {
                    isAdjacent = true;
                }
            }
        }
        return isAdjacent;
    }

    public double getDistanceBetween(Stop stop)
    {
        return MathHelpers.distanceInKilometers(
                Double.parseDouble(stop.getLat()),
                Double.parseDouble(stop.getLng()),
                Double.parseDouble(_lat),
                Double.parseDouble(_lng)
        );
    }

    public int compareTo(Stop o) {
        return 0;
    }


    @Override
    public String toString() {
        return "The station is " + _name
        + " num " + _num
        + " with type " + _type + "\n"
        + "In " + _commune
        + " at the position (lat/lng)" +_lat + " / "
        + _lng + "\n"
        + "The lineIds are : " + _lineIds + "\n"
        + " It's hub : " + _isHub + "\n\n";
    }
}
