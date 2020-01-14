package GraphAlgorithms;

import HttpUrlConnection.model.Stop;
import java.util.List;

public interface AlgorithmGraph
{
    void init(Boolean withoutRecursivity);
    List<Stop> getShortestPath(Stop dest);
}
