package com.airbnb.android.airmapview;

import android.graphics.Bitmap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class AirMapMarker<T> {

    /* renamed from: id */
    private final long f2060id;
    private Marker marker;
    private final MarkerOptions markerOptions;
    private final T object;

    public static class Builder<T> {

        /* renamed from: id */
        private long f2061id;
        private final MarkerOptions markerOptions = new MarkerOptions();
        private T object;

        public Builder<T> object(T object2) {
            this.object = object2;
            return this;
        }

        /* renamed from: id */
        public Builder<T> mo19456id(long id) {
            this.f2061id = id;
            return this;
        }

        public Builder<T> position(LatLng position) {
            this.markerOptions.position(position);
            return this;
        }

        public Builder<T> anchor(float u, float v) {
            this.markerOptions.anchor(u, v);
            return this;
        }

        public Builder<T> infoWindowAnchor(float u, float v) {
            this.markerOptions.infoWindowAnchor(u, v);
            return this;
        }

        public Builder<T> title(String title) {
            this.markerOptions.title(title);
            return this;
        }

        public Builder<T> snippet(String snippet) {
            this.markerOptions.snippet(snippet);
            return this;
        }

        public Builder<T> iconId(int iconId) {
            try {
                this.markerOptions.icon(BitmapDescriptorFactory.fromResource(iconId));
            } catch (NullPointerException e) {
            }
            return this;
        }

        public Builder<T> bitmap(Bitmap bitmap) {
            try {
                bitmapDescriptor(BitmapDescriptorFactory.fromBitmap(bitmap));
            } catch (NullPointerException e) {
            }
            return this;
        }

        public Builder<T> bitmapDescriptor(BitmapDescriptor bitmap) {
            this.markerOptions.icon(bitmap);
            return this;
        }

        public Builder<T> draggable(boolean draggable) {
            this.markerOptions.draggable(draggable);
            return this;
        }

        public Builder<T> visible(boolean visible) {
            this.markerOptions.visible(visible);
            return this;
        }

        public Builder<T> flat(boolean flat) {
            this.markerOptions.flat(flat);
            return this;
        }

        public Builder<T> rotation(float rotation) {
            this.markerOptions.rotation(rotation);
            return this;
        }

        public Builder<T> alpha(float alpha) {
            this.markerOptions.alpha(alpha);
            return this;
        }

        public AirMapMarker<T> build() {
            return new AirMapMarker<>(this.object, this.f2061id, this.markerOptions);
        }
    }

    private AirMapMarker(T object2, long id, MarkerOptions markerOptions2) {
        this.object = object2;
        this.f2060id = id;
        this.markerOptions = markerOptions2;
    }

    public T object() {
        return this.object;
    }

    public long getId() {
        return this.f2060id;
    }

    public LatLng getLatLng() {
        return this.markerOptions.getPosition();
    }

    /* access modifiers changed from: 0000 */
    public void setLatLng(LatLng latLng) {
        this.markerOptions.position(latLng);
    }

    public String getTitle() {
        return this.markerOptions.getTitle();
    }

    public String getSnippet() {
        return this.markerOptions.getSnippet();
    }

    public MarkerOptions getMarkerOptions() {
        return this.markerOptions;
    }

    /* access modifiers changed from: 0000 */
    public void setGoogleMarker(Marker marker2) {
        this.marker = marker2;
    }

    public Builder<T> toBuilder() {
        return new Builder().mo19456id(this.f2060id).object(this.object).position(this.markerOptions.getPosition()).alpha(this.markerOptions.getAlpha()).anchor(this.markerOptions.getAnchorU(), this.markerOptions.getAnchorV()).bitmapDescriptor(this.markerOptions.getIcon()).infoWindowAnchor(this.markerOptions.getInfoWindowAnchorU(), this.markerOptions.getInfoWindowAnchorV()).snippet(this.markerOptions.getSnippet()).title(this.markerOptions.getTitle()).draggable(this.markerOptions.isDraggable()).visible(this.markerOptions.isVisible()).alpha(this.markerOptions.getAlpha()).rotation(this.markerOptions.getRotation()).flat(this.markerOptions.isFlat());
    }

    public Marker getMarker() {
        return this.marker;
    }
}
