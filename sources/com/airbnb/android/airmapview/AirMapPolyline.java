package com.airbnb.android.airmapview;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.List;

public class AirMapPolyline<T> {
    private static final int STROKE_COLOR = -16776961;
    private static final int STROKE_WIDTH = 1;
    private Polyline googlePolyline;

    /* renamed from: id */
    private long f2064id;
    private T object;
    private List<LatLng> points;
    private int strokeColor;
    private int strokeWidth;
    private String title;

    public AirMapPolyline(List<LatLng> points2, long id) {
        this(null, points2, id);
    }

    public AirMapPolyline(T object2, List<LatLng> points2, long id) {
        this(object2, points2, id, 1, STROKE_COLOR);
    }

    public AirMapPolyline(T object2, List<LatLng> points2, long id, int strokeWidth2, int strokeColor2) {
        this.object = object2;
        this.points = points2;
        this.f2064id = id;
        this.strokeWidth = strokeWidth2;
        this.strokeColor = strokeColor2;
    }

    public long getId() {
        return this.f2064id;
    }

    public void setId(long id) {
        this.f2064id = id;
    }

    public List<LatLng> getPoints() {
        return this.points;
    }

    public void setPoints(List<LatLng> points2) {
        this.points = points2;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title2) {
        this.title = title2;
    }

    public T getObject() {
        return this.object;
    }

    public void setObject(T object2) {
        this.object = object2;
    }

    public int getStrokeWidth() {
        return this.strokeWidth;
    }

    public int getStrokeColor() {
        return this.strokeColor;
    }

    public void addToGoogleMap(GoogleMap googleMap) {
        this.googlePolyline = googleMap.addPolyline(new PolylineOptions().addAll(this.points).width((float) this.strokeWidth).color(this.strokeColor));
    }

    public boolean removeFromGoogleMap() {
        if (this.googlePolyline == null) {
            return false;
        }
        this.googlePolyline.remove();
        return true;
    }
}
