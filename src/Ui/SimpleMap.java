package Ui;


import HttpUrlConnection.model.Line;
import HttpUrlConnection.model.NetworkTransport;
import HttpUrlConnection.model.Stop;
import com.esri.arcgisruntime.geometry.*;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.mapping.Viewpoint;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;

import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.esri.arcgisruntime.symbology.TextSymbol;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;
import RatpServices.RatpDataService;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.json.simple.parser.ParseException;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SimpleMap extends Application {

    private static ArrayList<Line> lines;
    private static ArrayList<Stop> stops;
    private static NetworkTransport network;
    private int hexRed = 0xFFFF0000;
    private int hexBlue = 0xFF00FF00;
    private int hexGreen = 0xFF0000FF;
    private SpatialReference spatialReference = SpatialReferences.getWgs84();
    private GraphicsOverlay graphicsOverlay;
    private MapView mapView;
    private static List<Stop> path;

    public SimpleMap() throws IOException, ParseException {
    }

    public SimpleMap(List<Stop> path_, ArrayList<Stop> stops_, ArrayList<Line> lines_, NetworkTransport network_) {
        path = path_;
        network = network_;
        lines = lines_;
        stops = stops_;


    }

    public static void main(String[] args) {

        Application.launch(args);
    }
    private void setupGraphicsOverlay() {
        if (mapView != null) {
            graphicsOverlay = new GraphicsOverlay();
            mapView.getGraphicsOverlays().add(graphicsOverlay);
        }
    }
    private void addTextToPoint(String station,double lon,double lat) {
        if (graphicsOverlay != null) {
            TextSymbol textSymbol = new TextSymbol();
            textSymbol.setText(station);
            textSymbol.setColor(hexRed);
            textSymbol.setSize(11);
            Point point = new Point(lon, lat, spatialReference);
            Graphic pointGraphic = new Graphic(point, textSymbol);
            graphicsOverlay.getGraphics().add(pointGraphic);
        }
    }
    private void addPointGraphic() {
        if (graphicsOverlay != null) {
            SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, hexRed, 10.0f);
            pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, hexBlue, 2.0f));
            Point point = new Point(2.333565, 48.8503712, spatialReference);
            Graphic pointGraphic = new Graphic(point, pointSymbol);
            graphicsOverlay.getGraphics().add(pointGraphic);
        }
    }
    private void addPointGraphic(float lon,float lat,int color) {
        if (graphicsOverlay != null) {
            SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, color, 10.0f);
            pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, color, 2.0f));
            Point point = new Point(lon, lat, spatialReference);
            Graphic pointGraphic = new Graphic(point, pointSymbol);
            graphicsOverlay.getGraphics().add(pointGraphic);
        }
    }
    private void addPolylineGraphic() {
        if (graphicsOverlay != null) {
            PointCollection polylinePoints = new PointCollection(spatialReference);
            polylinePoints.add(new Point(2.333565, 48.8503712));
            polylinePoints.add(new Point( 2.371801,48.863541));
            Polyline polyline = new Polyline(polylinePoints);
            SimpleLineSymbol polylineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, hexBlue, 3.0f);
            Graphic polylineGraphic = new Graphic(polyline, polylineSymbol);
            graphicsOverlay.getGraphics().add(polylineGraphic);
        }
    }
    private void addPolylineGraphic(Stop[] stops,int color) {
        ArrayList<Point> points;
        if (graphicsOverlay != null) {
            PointCollection polylinePoints = new PointCollection(spatialReference);
            for (Stop stop: stops
                 ) {
              Point  point = new Point( Double.parseDouble( stop.getLng()) , Double.parseDouble(stop.getLat() ) );
                polylinePoints.add(point);
            }
            Polyline polyline = new Polyline(polylinePoints);
            SimpleLineSymbol polylineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, color, 3.0f);
            Graphic polylineGraphic = new Graphic(polyline, polylineSymbol);
            graphicsOverlay.getGraphics().add(polylineGraphic);
        }
    }
    private void setCenter(){
         Viewpoint viewPoint = new Viewpoint(new Point(2.333565,48.8503712, spatialReference),100000);
         mapView.setViewpointAsync(viewPoint);
    }
    public int getIntFromColor(int Red, int Green, int Blue){
        Red = (Red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        Green = (Green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        Blue = Blue & 0x000000FF; //Mask out anything not blue.

        return 0xFF000000 | Red | Green | Blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
    }
    public void addPath() {
        System.out.println(path);
        ArrayList<Stop> stops = new ArrayList<Stop>(path);
        for (Stop stop :
                stops) {
            System.out.println(stop);
            //Stop stop = network.getStopById(stopStr);
            addPointGraphic(
                    Float.parseFloat(stop.getLng()),
                    Float.parseFloat(stop.getLat()),
                    hexRed);

        }
    }
    private void drawStop()  {
        // Draw Lines
        System.out.println(lines);
        for (Line line:
            lines) {

           int color =getIntFromColor(
                   Color.decode(line.getColor()).getRed(),
                   Color.decode(line.getColor()).getGreen(),
                   Color.decode(line.getColor()).getBlue()
           )

            ;
            ArrayList<ArrayList<String>> directions = line.getStopIds();
            for (ArrayList<String> direction:
                 directions){

                //Draw Lines
                for (String stopStr:
                        direction) {
                    Stop stop = network.getStopById(stopStr);
                    addPointGraphic(
                            Float.parseFloat(stop.getLng()),
                            Float.parseFloat(stop.getLat()),
                            color);
                    addTextToPoint(
                            stop.getName(),
                            Float.parseFloat(stop.getLng()),
                            Float.parseFloat(stop.getLat())
                    );
                    //To Draw lines we get Adjacent Stops
                    ArrayList<Stop> adjStops = network.getAdjacentsByStopAndLine(stop, line);
                    for (Stop adjStop :
                            adjStops) {
                        Stop[] stop2toDraw = {stop, adjStop};
                        // Actually Draw the Lines
                        addPolylineGraphic(stop2toDraw, color);
                    }
                }

            }

        }
    }

    @Override
    public void start(Stage stage) {
        // set the title and size of the stage and show it
        stage.setTitle("Ratp Project Advance");
        stage.setWidth(800);
        stage.setHeight(700);
        stage.show();
        Basemap basemapType = Basemap.createOpenStreetMap();

        // create a JavaFX scene with a stack pane as the root node and add it to the scene
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);


        // create a MapView to display the map and add it to the stack pane
        mapView = new MapView();
        stackPane.getChildren().add(mapView);

        // create an ArcGISMap with the default imagery basemap
        ArcGISMap map = new ArcGISMap(Basemap.createImagery().createStreets());
        //Set center
        setCenter();



        // display the map by setting the map on the map view


        // Add overlay
        setupGraphicsOverlay();
        /* ** ADD ** */
      //  addPointGraphic();
       // addPolylineGraphic();
        drawStop();
        addPath();
        mapView.setMap(map);

    }

    /**
     * Stops and releases all resources used in application.
     */
    @Override
    public void stop() {
        if (mapView != null) {
            mapView.dispose();
        }
    }

    public void main() {
        Application.launch();
    }
}