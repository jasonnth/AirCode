package com.airbnb.p027n2.utils;

/* renamed from: com.airbnb.n2.utils.LocationUtil */
public class LocationUtil {
    public static double getXRadius(int radius, float degrees) {
        return ((double) radius) * Math.sin((((double) degrees) * 3.141592653589793d) / 180.0d);
    }

    public static double getYRadius(int radius, float degrees) {
        return ((double) radius) * Math.cos((((double) degrees) * 3.141592653589793d) / 180.0d);
    }

    public static LatLng getLocationDistanceFromLocation(LatLng start, double xDistance, double yDistance) {
        return LatLng.builder().lat(start.lat() + ((180.0d * (yDistance / 6378100.0d)) / 3.141592653589793d)).lng(start.lng() + ((180.0d * (xDistance / (6378100.0d * Math.cos((start.lat() * 3.141592653589793d) / 180.0d)))) / 3.141592653589793d)).build();
    }
}
