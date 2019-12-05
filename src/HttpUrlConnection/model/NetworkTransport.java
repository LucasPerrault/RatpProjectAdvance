package HttpUrlConnection.model;

public class NetworkTransport {
    private Line[] _lines;
    private Stop[] _stops;
    private Correspondence[] _correspondences;

    public NetworkTransport(Line[] lines, Stop[] stops, Correspondence[] correspondences)
    {
        _lines = lines;
        _stops = stops;
        _correspondences = correspondences;
    }

    public Line[] getLines()
    {
        return _lines;
    }

    public Stop[] getStops()
    {
        return _stops;
    }

    public Correspondence[] getCorrespondences()
    {
        return _correspondences;
    }

    @Override
    public String toString() {
        return "The Network contains " + _lines.length + " lines which are " + _lines + " and " + _stops.length + " stops which are "
                + _stops + " and correspondences " + _correspondences + "(" + _correspondences.length + ").";
    }
}