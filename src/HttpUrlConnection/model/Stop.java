package HttpUrlConnection.model;

import java.util.ArrayList;

public class Stop {

    private String _commune;
    private String _lat;
    private String _lng;
    private ArrayList<Line> _lines;
    private String _name;
    private String _num;
    private String _type;
    private Boolean _isHub;

    public Stop(String commune, String lat, String lng, ArrayList<Line> lines, String name, String num, String type, Boolean isHub)
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

    public ArrayList<Line> getLines()
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

    public boolean isAdjacent(Stop potentialAdjacentStop)
    {
        boolean isAdjacent = false;
        for (Line line: _lines)
        {
            for (ArrayList<String> stopIdsList: line.getStopIds())
            {
                int currentIndexStop = stopIdsList.indexOf(this);
                String nextStopIds = stopIdsList.get(currentIndexStop + 1);
                String previousStopIds = stopIdsList.get(currentIndexStop - 1);

                if (nextStopIds.equals(potentialAdjacentStop.getNum()) || previousStopIds.equals(potentialAdjacentStop.getNum()))
                {
                    isAdjacent = true;
                }
            }
        }
        return isAdjacent;
    }

    @Override
    public String toString() {
        return "In " + _commune + "\n"
        + "Position (lat/lng)" +_lat + " / "
        + _lng + "\n"
        + "The lines are " + _lines + "("+ _lines.size() +") \n"
        + " Which name is " + _name + "\n"
        + " With num " + _num + "\n"
        + " With type " + _type
        + " It's hub : " + _isHub + "\n";
    }
}
