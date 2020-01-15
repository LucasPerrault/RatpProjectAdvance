package GraphAlgorithms.BasicAlgorithmGraph;

import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;

import java.util.*;

public class DepthFirstSearch
{
    HashMap<Stop, Boolean> _cellAlreadyVisited;
    Stack<Stop> _stopStock = new Stack<Stop> ();
    NetworkTransport _networkTransport;
    Stop _src;

    public DepthFirstSearch(NetworkTransport networkTransport, Stop src)
    {
        _networkTransport = networkTransport;
        _src = src;
    }

    public void init()
    {
        _cellAlreadyVisited = new HashMap<Stop, Boolean>();

        DFSRecursive(_src, _cellAlreadyVisited);
    }

    private void DFSRecursive(Stop actualStop, HashMap<Stop, Boolean> cellAlreadyVisited)
    {
        cellAlreadyVisited.put(actualStop, true);
        List<Stop> adjacentsStop = _networkTransport.getAjdacentsByStop(actualStop);

        Iterator<Stop> it = adjacentsStop.iterator();
        while(it.hasNext())
        {
            Stop adjacentStop = it.next();
            if (!cellAlreadyVisited.containsKey(adjacentStop))
            {
                DFSRecursive(adjacentStop, cellAlreadyVisited);
            }
        }
    }
}
