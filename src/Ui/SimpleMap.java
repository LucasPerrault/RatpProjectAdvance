package Ui;


import HttpUrlConnection.model.Line;
import HttpUrlConnection.model.Stop;
import com.esri.arcgisruntime.geometry.*;
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
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;
import RatpServices.RatpDataService;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;


public class SimpleMap extends Application {
    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "http://vasyenmetro.com/data/reseau.json";
    private final  RatpDataService ratpDataService =new RatpDataService(GET_URL, USER_AGENT);
    private final ArrayList stations =  ratpDataService.getRatpData("stations" );
    private final ArrayList lines = ratpDataService.getRatpData("lignes" );
    private int hexRed = 0xFFFF0000;
    private int hexBlue = 0xFF00FF00;
    private int hexGreen = 0xFF0000FF;
    private SpatialReference spatialReference = SpatialReferences.getWgs84();
    private GraphicsOverlay graphicsOverlay;
    private MapView mapView;

    public SimpleMap() throws IOException, ParseException {
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
    private void addPointGraphic() {
        if (graphicsOverlay != null) {
            SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, hexRed, 10.0f);
            pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, hexBlue, 2.0f));
            Point point = new Point(2.333565, 48.8503712, spatialReference);
            Graphic pointGraphic = new Graphic(point, pointSymbol);
            graphicsOverlay.getGraphics().add(pointGraphic);
        }
    }
    private void addPointGraphic(int lon,int lat,int color) {
        if (graphicsOverlay != null) {
            SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, hexRed, 10.0f);
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
    private void addPolylineGraphic(ArrayList<Point> points) {
        if (graphicsOverlay != null) {
            PointCollection polylinePoints = new PointCollection(spatialReference);
            for (Point point: points
                 ) {
                polylinePoints.add(point);
            }
            Polyline polyline = new Polyline(polylinePoints);
            SimpleLineSymbol polylineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, hexBlue, 3.0f);
            Graphic polylineGraphic = new Graphic(polyline, polylineSymbol);
            graphicsOverlay.getGraphics().add(polylineGraphic);
        }
    }
    private void setCenter(){
         Viewpoint viewPoint = new Viewpoint(new Point(2.333565,48.8503712, spatialReference),100000);
         mapView.setViewpointAsync(viewPoint);
    }
    @Override
    public void start(Stage stage) {
        // set the title and size of the stage and show it
        stage.setTitle("My Map App");
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
        addPointGraphic();
        addPolylineGraphic();

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
}