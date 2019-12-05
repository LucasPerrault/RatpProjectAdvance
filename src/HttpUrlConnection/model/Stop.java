package HttpUrlConnection.model;

public class Stop {

    private String _commune;
    private String _lat;
    private String _lng;
    private Line[] _lines;
    private String _name;
    private String _num;
    private String _type;
    private Boolean _isHub;

    public Stop(String commune, String lat, String lng, Line[] lines, String name, String num, String type, Boolean isHub)
    {
        _commune = commune;
        _lat = lat;
        _lng = lng;
        _lines = lines;
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

    public Line[] getLines()
    {
        return _lines;
    }

    public String getType()
    {
        return _type;
    }

    public String getNum()
    {
        return _num;
    }

    @Override
    public String toString() {
        return "In " + _commune + "\n"
        + "Position (lat/lng)" +_lat + " / "
        + _lng + "\n"
        + "The lines are " + _lines + "("+ _lines.length +") \n"
        + " Which name is " + _name + "\n"
        + " With num " + _num + "\n"
        + " With type " + _type
        + " It's hub : " + _isHub + "\n";
    }
}
