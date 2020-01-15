package Helpers;

public class MathHelpers
{
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function calculate the distance with two positions      ::*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    public static double distanceInKilometers(double lat1, double lon1, double lat2, double lon2) {
        // Init params
        double longitudesCosinus = Math.cos(deg2rad(lon1 - lon2));
        double latitudesSinus = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2));
        double latitudesCosinus = Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2));

        // Treatment of the distance
        double dist = latitudesSinus +  latitudesCosinus * longitudesCosinus ;
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        // Convert to kilometers
        dist = dist * 1.609344;

        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians            ::*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    public static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees            ::*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    public static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
