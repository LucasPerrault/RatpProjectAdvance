package Factory;

import HttpUrlConnection.model.Line;
import HttpUrlConnection.model.Stop;
import RatpServices.RatpDataService;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RatpFactory
{
    ArrayList<Line> _lines;
    ArrayList<Stop> _stops;
    Stop _stopGenerated;

    public RatpFactory(ArrayList<Line> lines, ArrayList<Stop> stops) throws IOException, ParseException
    {
        _lines = lines;
        _stops = stops;
    }

    private void searchStopFactory()
    {
        Scanner scan = new Scanner(System.in);
        Boolean hasStop = false;
        System.out.println("Please enter the correct and complete name of station : \n");
        while(!hasStop)
        {
            try {
                String inputStopName = scan.nextLine();
                for (Stop stop: _stops)
                {
                    if (inputStopName.equals(stop.getName())) {
                        hasStop = true;
                        _stopGenerated = stop;
                        return;
                    }
                }
            }   catch (InputMismatchException e)   {
                System.out.println("Incorrect entry. Please input only a positive integer.");
                scan.nextLine();
            }
            System.out.println("Ooops, impossible to find your station. Try again !");

        }
    }

    public Stop getStopFactory()
    {
        searchStopFactory();
        System.out.println("Vous avez rentré la station : " + _stopGenerated.getName());
        return _stopGenerated;
    }
}
