package StructuralProperties;

import HttpUrlConnection.model.Stop;

import java.util.List;

public abstract class AbstractStructuralPropertie implements StructualPropertie
{
    protected List<Stop> _path;
    protected double _lengthPath = 0;

    public double getLength()
    {
        return _lengthPath;
    }

    public List<Stop> getPath()
    {
        return _path;
    }

    public void printPathAndLength(List<Stop> stops, double length)
    {
        System.out.println("Stops acrossed which are : \n");
        for(Stop stop: stops)
        {
            System.out.println(stop.getName());
        }
        System.out.println("The total length of the path is : " + length);
    }
}
