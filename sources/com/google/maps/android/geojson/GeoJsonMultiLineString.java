package com.google.maps.android.geojson;

import java.util.List;

public class GeoJsonMultiLineString implements GeoJsonGeometry {
    private final List<GeoJsonLineString> mGeoJsonLineStrings;

    public GeoJsonMultiLineString(List<GeoJsonLineString> geoJsonLineStrings) {
        if (geoJsonLineStrings == null) {
            throw new IllegalArgumentException("GeoJsonLineStrings cannot be null");
        }
        this.mGeoJsonLineStrings = geoJsonLineStrings;
    }

    public String getType() {
        return "MultiLineString";
    }

    public List<GeoJsonLineString> getLineStrings() {
        return this.mGeoJsonLineStrings;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("MultiLineString").append("{");
        sb.append("\n LineStrings=").append(this.mGeoJsonLineStrings);
        sb.append("\n}\n");
        return sb.toString();
    }
}
