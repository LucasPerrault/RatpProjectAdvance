package HttpUrlConnection.model;

import java.util.ArrayList;
import java.util.HashMap;

public class NetworkTransport {
    private ArrayList<Line> _lines;
    private ArrayList<Stop> _stops;
    private double _travelledDistance;
    private HashMap<Stop, Double> _travelledStopsAndDistances = new HashMap<Stop, Double>();
    public HashMap<Stop, Stop> predecessorByStops = new HashMap<Stop, Stop>();


    public NetworkTransport(ArrayList<Line> lines, ArrayList<Stop> stops)
    {
        _lines = lines;
        _stops = stops;
    }

    public ArrayList<Line> getLines()
    {
        return _lines;
    }

    public ArrayList<Stop> getStops()
    {
        return _stops;
    }

    public ArrayList<Stop> getAjdacentsByStop(Stop stop)
    {
        ArrayList<Stop> stops = new ArrayList<Stop>();

        for (Line line: getLinesByLineId(stop.getLineIds()))
        {
            for (ArrayList<String> stopIdsList: line.getStopIds())
            {
                if (stopIdsList.contains(stop.getNum()))
                {
                    int currentStopIndex = stopIdsList.indexOf(stop.getNum());
                    if (currentStopIndex == 0)
                    {
                        Stop adjacentStop = getStopById(stopIdsList.get(currentStopIndex + 1));
                        stops.add(adjacentStop);
                    } else if(currentStopIndex == (stopIdsList.size() - 1)) {
                        Stop adjacentStop = getStopById(stopIdsList.get(currentStopIndex - 1));
                        stops.add(adjacentStop);
                    } else {
                        Stop adjacentStopPrevious = getStopById(stopIdsList.get(currentStopIndex + 1));
                        stops.add(adjacentStopPrevious);
                        Stop adjacentStopNext = getStopById(stopIdsList.get(currentStopIndex - 1));
                        stops.add(adjacentStopNext);
                    }

                }
            }
        }
        return stops;
    }
    public ArrayList<Stop> getAdjacentsByStopAndLine(Stop stop, Line line)
    {
        ArrayList<Stop> stops = new ArrayList<Stop>();
        for (ArrayList<String> stopIdsList: line.getStopIds())
        {
            if (stopIdsList.contains(stop.getNum()))
            {
                int currentStopIndex = stopIdsList.indexOf(stop.getNum());
                if (currentStopIndex == 0)
                {
                    Stop adjacentStop = getStopById(stopIdsList.get(currentStopIndex + 1));
                    stops.add(adjacentStop);
                } else if(currentStopIndex == (stopIdsList.size() - 1)) {
                    Stop adjacentStop = getStopById(stopIdsList.get(currentStopIndex - 1));
                    stops.add(adjacentStop);
                } else {
                    Stop adjacentStopPrevious = getStopById(stopIdsList.get(currentStopIndex + 1));
                    stops.add(adjacentStopPrevious);
                    Stop adjacentStopNext = getStopById(stopIdsList.get(currentStopIndex - 1));
                    stops.add(adjacentStopNext);
                }

            }
        }
        return stops;
    }
    private ArrayList<Line> getLinesByLineId(ArrayList<String> lineIds)
    {
        ArrayList<Line> linesFound = new ArrayList<>();
        for (String lineId : lineIds)
        {
            for (Line line: _lines)
            {
                if (line.getNum().equals(lineId))
                {
                    linesFound.add(line);
                }
            }
        }
        return linesFound;
    }

    public Stop getStopById(String stopId)
    {
        Stop stopFound = null;
        for (Stop stop : _stops)
        {
            if (stop.getNum().equals(stopId))
            {
                stopFound = stop;
            }
        }
        if (stopFound == null)
        {
            System.out.println("Ooops, you are looking for stop id which not exist");
        }
        return stopFound;
    }

    public HashMap<Stop, Double> getTravelledStopsAndDistances()
    {
        return _travelledStopsAndDistances;
    }

    public void resetDistanceTraveledByStops()
    {
        for (Stop stop: _stops) {
            setTravelledDistanceByStop(stop, Double.POSITIVE_INFINITY);
        }
    }

    public void setTravelledDistanceByStop(Stop stop, Double distance)
    {
        _travelledStopsAndDistances.put(stop, distance);
    }

    public double getTravelledDistanceByStop(Stop stop)
    {
        return _travelledStopsAndDistances.get(stop);

    }

    public void setPredecessorStops(Stop adjacent, Stop predecessor)
    {
        predecessorByStops.put(adjacent, predecessor);
    }

    public Stop getPredecessorByStop(Stop stop)
    {
        return predecessorByStops.get(stop);
    }

    @Override
    public String toString() {
        return "The Network contains " + _lines.size()
                + " lines which are " + _lines
                + " and " + _stops.size()
                + " stops which are " + _stops;
    }
}
