package com.airbnb.android.react.maps;

import android.content.Context;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.List;

public class AirMapPolyline extends AirMapFeature {
    private int color;
    private List<LatLng> coordinates;
    private boolean geodesic;
    private Polyline polyline;
    private PolylineOptions polylineOptions;
    private float width;
    private float zIndex;

    public AirMapPolyline(Context context) {
        super(context);
    }

    public void setCoordinates(ReadableArray coordinates2) {
        this.coordinates = new ArrayList(coordinates2.size());
        for (int i = 0; i < coordinates2.size(); i++) {
            ReadableMap coordinate = coordinates2.getMap(i);
            this.coordinates.add(i, new LatLng(coordinate.getDouble("latitude"), coordinate.getDouble("longitude")));
        }
        if (this.polyline != null) {
            this.polyline.setPoints(this.coordinates);
        }
    }

    public void setColor(int color2) {
        this.color = color2;
        if (this.polyline != null) {
            this.polyline.setColor(color2);
        }
    }

    public void setWidth(float width2) {
        this.width = width2;
        if (this.polyline != null) {
            this.polyline.setWidth(width2);
        }
    }

    public void setZIndex(float zIndex2) {
        this.zIndex = zIndex2;
        if (this.polyline != null) {
            this.polyline.setZIndex(zIndex2);
        }
    }

    public void setGeodesic(boolean geodesic2) {
        this.geodesic = geodesic2;
        if (this.polyline != null) {
            this.polyline.setGeodesic(geodesic2);
        }
    }

    public PolylineOptions getPolylineOptions() {
        if (this.polylineOptions == null) {
            this.polylineOptions = createPolylineOptions();
        }
        return this.polylineOptions;
    }

    private PolylineOptions createPolylineOptions() {
        PolylineOptions options = new PolylineOptions();
        options.addAll(this.coordinates);
        options.color(this.color);
        options.width(this.width);
        options.geodesic(this.geodesic);
        options.zIndex(this.zIndex);
        return options;
    }

    public Object getFeature() {
        return this.polyline;
    }

    public void addToMap(GoogleMap map) {
        this.polyline = map.addPolyline(getPolylineOptions());
        this.polyline.setClickable(true);
    }

    public void removeFromMap(GoogleMap map) {
        this.polyline.remove();
    }
}
