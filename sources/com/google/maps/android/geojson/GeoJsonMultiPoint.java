package com.google.maps.android.geojson;

import java.util.List;

public class GeoJsonMultiPoint implements GeoJsonGeometry {
    private final List<GeoJsonPoint> mGeoJsonPoints;

    public GeoJsonMultiPoint(List<GeoJsonPoint> geoJsonPoints) {
        if (geoJsonPoints == null) {
            throw new IllegalArgumentException("GeoJsonPoints cannot be null");
        }
        this.mGeoJsonPoints = geoJsonPoints;
    }

    public String getType() {
        return "MultiPoint";
    }

    public List<GeoJsonPoint> getPoints() {
        return this.mGeoJsonPoints;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("MultiPoint").append("{");
        sb.append("\n points=").append(this.mGeoJsonPoints);
        sb.append("\n}\n");
        return sb.toString();
    }
}
