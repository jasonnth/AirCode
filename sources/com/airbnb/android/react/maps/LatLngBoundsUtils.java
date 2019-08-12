package com.airbnb.android.react.maps;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class LatLngBoundsUtils {
    public static boolean BoundsAreDifferent(LatLngBounds a, LatLngBounds b) {
        LatLng centerA = a.getCenter();
        double latA = centerA.latitude;
        double lngA = centerA.longitude;
        double latDeltaA = a.northeast.latitude - a.southwest.latitude;
        double lngDeltaA = a.northeast.longitude - a.southwest.longitude;
        LatLng centerB = b.getCenter();
        double latB = centerB.latitude;
        double lngB = centerB.longitude;
        double latDeltaB = b.northeast.latitude - b.southwest.latitude;
        double lngDeltaB = b.northeast.longitude - b.southwest.longitude;
        double latEps = LatitudeEpsilon(a, b);
        double lngEps = LongitudeEpsilon(a, b);
        return different(latA, latB, latEps) || different(lngA, lngB, lngEps) || different(latDeltaA, latDeltaB, latEps) || different(lngDeltaA, lngDeltaB, lngEps);
    }

    private static boolean different(double a, double b, double epsilon) {
        return Math.abs(a - b) > epsilon;
    }

    private static double LatitudeEpsilon(LatLngBounds a, LatLngBounds b) {
        return Math.min(Math.abs(a.northeast.latitude - a.southwest.latitude), Math.abs(b.northeast.latitude - b.southwest.latitude)) / 2560.0d;
    }

    private static double LongitudeEpsilon(LatLngBounds a, LatLngBounds b) {
        return Math.min(Math.abs(a.northeast.longitude - a.southwest.longitude), Math.abs(b.northeast.longitude - b.southwest.longitude)) / 2560.0d;
    }
}
