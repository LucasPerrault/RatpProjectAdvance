package StructuralProperties;

import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.util.List;

public abstract class AbstractStructuralPropertie implements StructualPropertie
{
    protected double _lengthPath;
    protected List<Stop> _stopList;

    public AbstractStructuralPropertie()
    {
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
