package com.google.maps.android.geojson;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

public class GeoJsonLayer {
    private LatLngBounds mBoundingBox;
    private final GeoJsonRenderer mRenderer;

    public GeoJsonLayer(GoogleMap map, JSONObject geoJsonFile) {
        if (geoJsonFile == null) {
            throw new IllegalArgumentException("GeoJSON file cannot be null");
        }
        this.mBoundingBox = null;
        GeoJsonParser parser = new GeoJsonParser(geoJsonFile);
        this.mBoundingBox = parser.getBoundingBox();
        HashMap<GeoJsonFeature, Object> geoJsonFeatures = new HashMap<>();
        Iterator i$ = parser.getFeatures().iterator();
        while (i$.hasNext()) {
            geoJsonFeatures.put((GeoJsonFeature) i$.next(), null);
        }
        this.mRenderer = new GeoJsonRenderer(map, geoJsonFeatures);
    }

    public void addLayerToMap() {
        this.mRenderer.addLayerToMap();
    }

    public void removeLayerFromMap() {
        this.mRenderer.removeLayerFromMap();
    }

    public GeoJsonPolygonStyle getDefaultPolygonStyle() {
        return this.mRenderer.getDefaultPolygonStyle();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Collection{");
        sb.append("\n Bounding box=").append(this.mBoundingBox);
        sb.append("\n}\n");
        return sb.toString();
    }
}
