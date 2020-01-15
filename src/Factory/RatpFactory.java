package Factory;

import GraphAlgorithms.AlgorithmGraph;
import GraphAlgorithms.BasicAlgorithmGraph.BreadthFirstSearch;
import GraphAlgorithms.BasicAlgorithmGraph.DepthFirstSearch;
import GraphAlgorithms.WeightedAlgorithmGraph.AStar;
import GraphAlgorithms.WeightedAlgorithmGraph.Dijkstra;
import HttpUrlConnection.model.Line;
import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;
import RatpServices.RatpDataService;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

public class RatpFactory
{
    Stop _stopGenerated;
    int _indexOfAlgorithmGenerated;
    public int _indexOfGraphType;
    AlgorithmGraph _algorithmGenerated;
    AlgorithmGraph _algorithmGeneratedForEccentricity;

    private void searchStopFactory(List<Stop> stops)
    {
        Scanner scan = new Scanner(System.in);
        Boolean hasStop = false;
        System.out.println("Please enter the correct and complete name of station : \n");
        while(!hasStop)
        {
            try {
                String inputStopName = scan.nextLine();
                for (Stop stop: stops)
                {
                    if (inputStopName.equals(stop.getName())) {
                        hasStop = true;
                        _stopGenerated = stop;
                        return;
                    }
                }
            }   catch (InputMismatchException e)   {
                System.out.println("Incorrect entry. Please input only a real station.");
                scan.nextLine();
            }
            System.out.println("Ooops, impossible to find your station. Try again !");

        }
    }

    public Stop getStopFactory(List<Stop> stops)
    {
        searchStopFactory(stops);
        System.out.println("Vous avez rentré la station : " + _stopGenerated.getName());
        return _stopGenerated;
    }

    private void inputAlgorithm(Optional<Integer> indexOfAlgorithmGenerated)
    {
        if (indexOfAlgorithmGenerated.isPresent())
        {
            _indexOfAlgorithmGenerated = indexOfAlgorithmGenerated.get();
        } else {
            Scanner scan = new Scanner(System.in);
            Boolean hasALgorithm = false;
            System.out.println("Please choose the number which corresponded at your algorithm in order to find the shortestPath : ");
            System.out.println("Instructions : \n [1]: Breadth First Search \n [2] Dijkstra \n [3]A*");
            while(!hasALgorithm)
            {
                try {
                    int inputAlgorithm = scan.nextInt();
                    if (((0 < inputAlgorithm) && (inputAlgorithm < 4)))
                    {
                        hasALgorithm = true;
                        _indexOfAlgorithmGenerated = inputAlgorithm;
                        return;

                    }
                }   catch (InputMismatchException e)   {
                    System.out.println("Incorrect entry. Please input only a positive integer between 1 and 3.");
                    scan.nextLine();
                }
                System.out.println("Ooops, impossible to find your algorithm. Try again !");

            }
        }
    }

    public AlgorithmGraph getAlgorithmFactory(NetworkTransport networkTransport, Stop src, Stop dest)
    {
        inputAlgorithm(Optional.empty());
        switch (_indexOfAlgorithmGenerated)
        {
            case(1):
                _algorithmGenerated = new BreadthFirstSearch(src, networkTransport);
                break;
            case(2):
                _algorithmGenerated = new Dijkstra(src, networkTransport, Optional.empty());
                break;
            case(3):
                _algorithmGenerated = new AStar(src, dest, networkTransport);
                break;
        }
        System.out.println(_algorithmGenerated);
        return _algorithmGenerated;
    }

    public List<Stop> getShortestPathWithRecursivityFactory(NetworkTransport networkTransport, Stop src, Stop dest)
    {
        AlgorithmGraph graph = getAlgorithmFactory(networkTransport, src, dest);
        graph.init(false);
        return graph.getShortestPath(dest);
    }

    public void inputGraphTypeForEccentricity()
    {
        Scanner scan = new Scanner(System.in);
        Boolean hasType = false;
        System.out.println("Structural Property part : \n");
        System.out.println("Please enter the type of graph analyzed : \n");
        System.out.println("Instruction (enter number) : \n [1]: unweighted graph [2] weighted graph : \n");
        while(!hasType)
        {
            try {
                int inputType = scan.nextInt();
                if (((0 < inputType) && (inputType < 3)))
                {
                    hasType = true;
                    _indexOfGraphType = inputType;
                    return;
                }

            }   catch (InputMismatchException e)   {
                System.out.println("Incorrect entry. Please input only a positive number.");
                scan.nextLine();
            }
            System.out.println("Ooops, impossible to find the graph type. Try again !");
        }
    }

    public AlgorithmGraph getAlgorithmForEccentricityFactory(NetworkTransport networkTransport, Stop src)
    {
        inputGraphTypeForEccentricity();
        switch (_indexOfGraphType)
        {
            case(1):
                _algorithmGeneratedForEccentricity = new BreadthFirstSearch(src, networkTransport);
                break;
            case(2):
                _algorithmGeneratedForEccentricity = new Dijkstra(src, networkTransport, Optional.empty());
                break;
        }
        return _algorithmGeneratedForEccentricity;
    }
}
