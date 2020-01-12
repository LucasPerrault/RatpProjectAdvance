package GraphAlgorithms;

import HttpUrlConnection.model.Stop;
import java.util.List;

public interface AlgorithmGraph
{
    void init();
    List<Stop> getShortestPath();
}
