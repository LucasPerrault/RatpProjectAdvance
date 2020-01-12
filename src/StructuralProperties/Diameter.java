package StructuralProperties;

import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.util.List;

public class Diameter extends AbstractStructuralPropertie
{
    protected double _lengthPath = 0;
    protected List<Stop> _stopList;

    public Diameter(NetworkTransport networkTransport)
    {
        super();
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
}
