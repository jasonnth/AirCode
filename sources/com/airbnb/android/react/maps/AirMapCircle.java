package com.airbnb.android.react.maps;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

public class AirMapCircle extends AirMapFeature {
    private LatLng center;
    private Circle circle;
    private CircleOptions circleOptions;
    private int fillColor;
    private double radius;
    private int strokeColor;
    private float strokeWidth;
    private float zIndex;

    public AirMapCircle(Context context) {
        super(context);
    }

    public void setCenter(LatLng center2) {
        this.center = center2;
        if (this.circle != null) {
            this.circle.setCenter(this.center);
        }
    }

    public void setRadius(double radius2) {
        this.radius = radius2;
        if (this.circle != null) {
            this.circle.setRadius(this.radius);
        }
    }

    public void setFillColor(int color) {
        this.fillColor = color;
        if (this.circle != null) {
            this.circle.setFillColor(color);
        }
    }

    public void setStrokeColor(int color) {
        this.strokeColor = color;
        if (this.circle != null) {
            this.circle.setStrokeColor(color);
        }
    }

    public void setStrokeWidth(float width) {
        this.strokeWidth = width;
        if (this.circle != null) {
            this.circle.setStrokeWidth(width);
        }
    }

    public void setZIndex(float zIndex2) {
        this.zIndex = zIndex2;
        if (this.circle != null) {
            this.circle.setZIndex(zIndex2);
        }
    }

    public CircleOptions getCircleOptions() {
        if (this.circleOptions == null) {
            this.circleOptions = createCircleOptions();
        }
        return this.circleOptions;
    }

    private CircleOptions createCircleOptions() {
        CircleOptions options = new CircleOptions();
        options.center(this.center);
        options.radius(this.radius);
        options.fillColor(this.fillColor);
        options.strokeColor(this.strokeColor);
        options.strokeWidth(this.strokeWidth);
        options.zIndex(this.zIndex);
        return options;
    }

    public Object getFeature() {
        return this.circle;
    }

    public void addToMap(GoogleMap map) {
        this.circle = map.addCircle(getCircleOptions());
    }

    public void removeFromMap(GoogleMap map) {
        this.circle.remove();
    }
}
