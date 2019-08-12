package com.google.maps.android.geojson;

import com.google.android.gms.maps.model.LatLng;

public class GeoJsonPoint implements GeoJsonGeometry {
    private final LatLng mCoordinates;

    public GeoJsonPoint(LatLng coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate cannot be null");
        }
        this.mCoordinates = coordinate;
    }

    public String getType() {
        return "Point";
    }

    public LatLng getCoordinates() {
        return this.mCoordinates;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Point").append("{");
        sb.append("\n coordinates=").append(this.mCoordinates);
        sb.append("\n}\n");
        return sb.toString();
    }
}
