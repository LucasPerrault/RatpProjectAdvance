package HttpUrlConnection.model;

public class Line
{
    private String[] _stopIds;
    private String _name;
    private Integer _num;
    private String _color;
    private String[] _labels;

    public Line(String[] stopIds, String name, Integer num, String color, String[] labels)
    {
        _stopIds = stopIds;
        _name = name;
        _num = num;
        _color = color;
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

    public String[] getLabels()
    {
        return _labels;
    }

    public Integer getNum()
    {
        return _num;
    }

    public String[] getStopIds()
    {
        return _stopIds;
    }

    @Override
    public String toString() {
        return "Line n°" + _num + "with" + _color + "named " + _name + ". There labels are : " + _labels + "it contains" + _stopIds;
    }
}
