package com.google.maps.android.geojson;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;

public class GeoJsonLineString implements GeoJsonGeometry {
    private final List<LatLng> mCoordinates;

    public GeoJsonLineString(List<LatLng> coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Coordinates cannot be null");
        }
        this.mCoordinates = coordinates;
    }

    public String getType() {
        return "LineString";
    }

    public List<LatLng> getCoordinates() {
        return this.mCoordinates;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LineString").append("{");
        sb.append("\n coordinates=").append(this.mCoordinates);
        sb.append("\n}\n");
        return sb.toString();
    }
}
