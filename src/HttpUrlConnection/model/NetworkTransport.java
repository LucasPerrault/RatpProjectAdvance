package HttpUrlConnection.model;

import java.util.ArrayList;

public class NetworkTransport {
    private Line[] _lines;
    private ArrayList<Stop> _stops;
    private ArrayList<Correspondence> _correspondences;

    public NetworkTransport(Line[] lines, ArrayList<Stop> stops, ArrayList<Correspondence> correspondences)
    {
        _lines = lines;
        _stops = stops;
        _correspondences = correspondences;
    }

    public Line[] getLines()
    {
        return _lines;
    }

    public ArrayList<Stop> getStops()
    {
        return _stops;
    }

    public ArrayList<Correspondence> getCorrespondences()
    {
        return _correspondences;
    }

    public ArrayList<Stop> getAjdacentsByStop(Stop stop)
    {
        ArrayList<Stop> stops = new ArrayList<Stop>();

        for (Line line: _lines)
        {
            for (ArrayList<String> stopIdsList: line.getStopIds())
            {
                int currentStopIndex = stopIdsList.indexOf(stop.getNum());
                if (currentStopIndex == 0)
                {
                    Stop adjacentStop = _stops.get(currentStopIndex + 1);
                    stops.add(adjacentStop);
                } else if(currentStopIndex == (stopIdsList.size() - 1)) {
                    Stop adjacentStop = getStops().get(currentStopIndex - 1);
                    stops.add(adjacentStop);
                } else {
                    Stop adjacentStopPrevious = getStops().get(currentStopIndex + 1);
                    stops.add(adjacentStopPrevious);
                    Stop adjacentStopNext = getStops().get(currentStopIndex - 1);
                    stops.add(adjacentStopNext);
                }
            }
        }
        return stops;
    }

    @Override
    public String toString() {
        return "The Network contains " + _lines.length + " lines which are " + _lines + " and " + _stops.size() + " stops which are "
                + _stops + " and correspondences " + _correspondences + "(" + _correspondences.size() + ").";
    }
}