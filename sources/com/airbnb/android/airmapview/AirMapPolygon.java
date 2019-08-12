package com.airbnb.android.airmapview;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class AirMapPolygon<T> {
    private static final int STROKE_COLOR = -16776961;
    private static final int STROKE_WIDTH = 1;
    private Polygon googlePolygon;

    /* renamed from: id */
    private final long f2062id;
    private final T object;
    private final PolygonOptions polygonOptions;

    public static class Builder<T> {

        /* renamed from: id */
        private long f2063id;
        private T object;
        private final PolygonOptions polygonOptions = new PolygonOptions();

        public Builder() {
            this.polygonOptions.strokeWidth(1.0f);
            this.polygonOptions.strokeColor(AirMapPolygon.STROKE_COLOR);
        }

        public Builder<T> object(T object2) {
            this.object = object2;
            return this;
        }

        /* renamed from: id */
        public Builder<T> mo19476id(long id) {
            this.f2063id = id;
            return this;
        }

        public Builder<T> strokeColor(int color) {
            this.polygonOptions.strokeColor(color);
            return this;
        }

        public Builder<T> strokeWidth(float width) {
            this.polygonOptions.strokeWidth(width);
            return this;
        }

        public Builder<T> fillColor(int color) {
            this.polygonOptions.fillColor(color);
            return this;
        }

        public Builder<T> geodesic(boolean geodesic) {
            this.polygonOptions.geodesic(geodesic);
            return this;
        }

        public Builder<T> zIndex(float zIndex) {
            this.polygonOptions.zIndex(zIndex);
            return this;
        }

        public Builder<T> visible(boolean visible) {
            this.polygonOptions.visible(visible);
            return this;
        }

        public Builder<T> add(LatLng point) {
            this.polygonOptions.add(point);
            return this;
        }

        public Builder<T> add(LatLng... points) {
            this.polygonOptions.add(points);
            return this;
        }

        public Builder<T> addAll(Iterable<LatLng> points) {
            this.polygonOptions.addAll(points);
            return this;
        }

        public Builder<T> addHole(Iterable<LatLng> points) {
            this.polygonOptions.addHole(points);
            return this;
        }

        public AirMapPolygon<T> build() {
            return new AirMapPolygon<>(this.object, this.f2063id, this.polygonOptions);
        }
    }

    public AirMapPolygon(T object2, long id, PolygonOptions polygonOptions2) {
        this.object = object2;
        this.f2062id = id;
        this.polygonOptions = polygonOptions2;
    }

    public T getObject() {
        return this.object;
    }

    public long getId() {
        return this.f2062id;
    }

    public PolygonOptions getPolygonOptions() {
        return this.polygonOptions;
    }

    public Polygon getGooglePolygon() {
        return this.googlePolygon;
    }

    public void setGooglePolygon(Polygon googlePolygon2) {
        this.googlePolygon = googlePolygon2;
    }
}
