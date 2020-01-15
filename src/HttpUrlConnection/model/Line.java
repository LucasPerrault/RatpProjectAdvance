package HttpUrlConnection.model;

import java.util.ArrayList;

public class Line
{
    private ArrayList<ArrayList<String>> _stopIds;
    private String _name;
    private String _num;
    private String _color;
    private String _type;
    private ArrayList<ArrayList<String>> _labels;

    public Line(ArrayList<ArrayList<String>> stopIds, String name, String num, String color, String type, ArrayList<ArrayList<String>> labels)
    {
        _stopIds = stopIds;
        _name = name;
        _num = num;
        _color = color;
        _type = type;
        _labels = labels;
    }

    public String getName()
    {
        return _name;
    }

    public String getColor()
    {
        return _color;
    }

    public ArrayList<ArrayList<String>> getLabels()
    {
        return _labels;
    }

    public String getNum()
    {
        return _num;
    }

    public ArrayList<ArrayList<String>> getStopIds()
    {
        return _stopIds;
    }

    @Override
    public String toString() {
        return "Line n°" + _num
                + " with color " + _color
                + " named " + _name
                + ".\n There labels are : " + _labels
                + "it contains" + _stopIds +"\n";
    }
}
