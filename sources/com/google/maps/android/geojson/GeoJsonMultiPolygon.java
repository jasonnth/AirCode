package com.google.maps.android.geojson;

import java.util.List;

public class GeoJsonMultiPolygon implements GeoJsonGeometry {
    private final List<GeoJsonPolygon> mGeoJsonPolygons;

    public GeoJsonMultiPolygon(List<GeoJsonPolygon> geoJsonPolygons) {
        if (geoJsonPolygons == null) {
            throw new IllegalArgumentException("GeoJsonPolygons cannot be null");
        }
        this.mGeoJsonPolygons = geoJsonPolygons;
    }

    public String getType() {
        return "MultiPolygon";
    }

    public List<GeoJsonPolygon> getPolygons() {
        return this.mGeoJsonPolygons;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("MultiPolygon").append("{");
        sb.append("\n Polygons=").append(this.mGeoJsonPolygons);
        sb.append("\n}\n");
        return sb.toString();
    }
}
