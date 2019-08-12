package com.airbnb.android.react.maps;

import android.content.Context;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import java.util.ArrayList;
import java.util.List;

public class AirMapPolygon extends AirMapFeature {
    private List<LatLng> coordinates;
    private int fillColor;
    private boolean geodesic;
    private Polygon polygon;
    private PolygonOptions polygonOptions;
    private int strokeColor;
    private float strokeWidth;
    private float zIndex;

    public AirMapPolygon(Context context) {
        super(context);
    }

    public void setCoordinates(ReadableArray coordinates2) {
        this.coordinates = new ArrayList(coordinates2.size());
        for (int i = 0; i < coordinates2.size(); i++) {
            ReadableMap coordinate = coordinates2.getMap(i);
            this.coordinates.add(i, new LatLng(coordinate.getDouble("latitude"), coordinate.getDouble("longitude")));
        }
        if (this.polygon != null) {
            this.polygon.setPoints(this.coordinates);
        }
    }

    public void setFillColor(int color) {
        this.fillColor = color;
        if (this.polygon != null) {
            this.polygon.setFillColor(color);
        }
    }

    public void setStrokeColor(int color) {
        this.strokeColor = color;
        if (this.polygon != null) {
            this.polygon.setStrokeColor(color);
        }
    }

    public void setStrokeWidth(float width) {
        this.strokeWidth = width;
        if (this.polygon != null) {
            this.polygon.setStrokeWidth(width);
        }
    }

    public void setGeodesic(boolean geodesic2) {
        this.geodesic = geodesic2;
        if (this.polygon != null) {
            this.polygon.setGeodesic(geodesic2);
        }
    }

    public void setZIndex(float zIndex2) {
        this.zIndex = zIndex2;
        if (this.polygon != null) {
            this.polygon.setZIndex(zIndex2);
        }
    }

    public PolygonOptions getPolygonOptions() {
        if (this.polygonOptions == null) {
            this.polygonOptions = createPolygonOptions();
        }
        return this.polygonOptions;
    }

    private PolygonOptions createPolygonOptions() {
        PolygonOptions options = new PolygonOptions();
        options.addAll(this.coordinates);
        options.fillColor(this.fillColor);
        options.strokeColor(this.strokeColor);
        options.strokeWidth(this.strokeWidth);
        options.geodesic(this.geodesic);
        options.zIndex(this.zIndex);
        return options;
    }

    public Object getFeature() {
        return this.polygon;
    }

    public void addToMap(GoogleMap map) {
        this.polygon = map.addPolygon(getPolygonOptions());
        this.polygon.setClickable(true);
    }

    public void removeFromMap(GoogleMap map) {
        this.polygon.remove();
    }
}
