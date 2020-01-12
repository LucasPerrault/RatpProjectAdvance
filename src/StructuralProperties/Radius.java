package StructuralProperties;

import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.util.List;

public class Radius implements StructualPropertie
{
    private NetworkTransport _networkTransport;
    private double _shortestLengthPath = 0;
    private List<Stop> _shortestStopListPath;

    public Radius(NetworkTransport networkTransport)
    {
        for (Stop stop: _networkTransport.getStops())
        {
            Eccentricity eccentricity = new Eccentricity(_networkTransport, stop);

            if (_shortestLengthPath > eccentricity.getLength())
            {
                _shortestLengthPath = eccentricity.getLength();
                _shortestStopListPath = eccentricity.getPath();
            }
        }
    }

    private void init()
    {

    }

    public double getLength()
    {
        return _shortestLengthPath;
    }

    public List<Stop> getPath()
    {
        return _shortestStopListPath;
    }
}
