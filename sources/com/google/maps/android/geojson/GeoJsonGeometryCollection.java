package com.google.maps.android.geojson;

import java.util.List;

public class GeoJsonGeometryCollection implements GeoJsonGeometry {
    private final List<GeoJsonGeometry> mGeometries;

    public GeoJsonGeometryCollection(List<GeoJsonGeometry> geometries) {
        if (geometries == null) {
            throw new IllegalArgumentException("Geometries cannot be null");
        }
        this.mGeometries = geometries;
    }

    public String getType() {
        return "GeometryCollection";
    }

    public List<GeoJsonGeometry> getGeometries() {
        return this.mGeometries;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GeometryCollection").append("{");
        sb.append("\n Geometries=").append(this.mGeometries);
        sb.append("\n}\n");
        return sb.toString();
    }
}
