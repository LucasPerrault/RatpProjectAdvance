package StructuralProperties;

import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Diameter implements StructualPropertie
{
    protected double _lengthPath = 0;
    protected List<Stop> _stopList;

    public Diameter(NetworkTransport networkTransport)
    {
        init(networkTransport);
    }

    private void init(NetworkTransport networkTransport)
    {
        for (Stop stop: networkTransport.getStops())
        {
            Eccentricity eccentricity = new Eccentricity(networkTransport, stop);

            if (_lengthPath < eccentricity.getLength())
            {
                _lengthPath = eccentricity.getLength();
                _stopList = eccentricity.getPath();
            }
        }
    }


    public double getLength()
    {
        return _lengthPath;
    }

    public List<Stop> getPath()
    {
        return _stopList;
    }
}
