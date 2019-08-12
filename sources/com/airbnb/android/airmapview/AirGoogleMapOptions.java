package com.airbnb.android.airmapview;

import android.os.Bundle;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.CameraPosition;

public class AirGoogleMapOptions {
    private final GoogleMapOptions options;

    public AirGoogleMapOptions(GoogleMapOptions options2) {
        this.options = options2;
    }

    public AirGoogleMapOptions zOrderOnTop(boolean zOrderOnTop) {
        this.options.zOrderOnTop(zOrderOnTop);
        return this;
    }

    public AirGoogleMapOptions useViewLifecycleInFragment(boolean useViewLifecycleInFragment) {
        this.options.useViewLifecycleInFragment(useViewLifecycleInFragment);
        return this;
    }

    public AirGoogleMapOptions mapType(int mapType) {
        this.options.mapType(mapType);
        return this;
    }

    public AirGoogleMapOptions camera(CameraPosition camera) {
        this.options.camera(camera);
        return this;
    }

    public AirGoogleMapOptions zoomControlsEnabled(boolean enabled) {
        this.options.zoomControlsEnabled(enabled);
        return this;
    }

    public AirGoogleMapOptions compassEnabled(boolean enabled) {
        this.options.compassEnabled(enabled);
        return this;
    }

    public AirGoogleMapOptions scrollGesturesEnabled(boolean enabled) {
        this.options.scrollGesturesEnabled(enabled);
        return this;
    }

    public AirGoogleMapOptions zoomGesturesEnabled(boolean enabled) {
        this.options.zoomGesturesEnabled(enabled);
        return this;
    }

    public AirGoogleMapOptions tiltGesturesEnabled(boolean enabled) {
        this.options.tiltGesturesEnabled(enabled);
        return this;
    }

    public AirGoogleMapOptions rotateGesturesEnabled(boolean enabled) {
        this.options.rotateGesturesEnabled(enabled);
        return this;
    }

    public AirGoogleMapOptions liteMode(boolean enabled) {
        this.options.liteMode(enabled);
        return this;
    }

    public AirGoogleMapOptions mapToolbarEnabled(boolean enabled) {
        this.options.mapToolbarEnabled(enabled);
        return this;
    }

    public Boolean getZOrderOnTop() {
        return this.options.getZOrderOnTop();
    }

    public Boolean getUseViewLifecycleInFragment() {
        return this.options.getUseViewLifecycleInFragment();
    }

    public int getMapType() {
        return this.options.getMapType();
    }

    public CameraPosition getCamera() {
        return this.options.getCamera();
    }

    public Boolean getZoomControlsEnabled() {
        return this.options.getZoomControlsEnabled();
    }

    public Boolean getCompassEnabled() {
        return this.options.getCompassEnabled();
    }

    public Boolean getScrollGesturesEnabled() {
        return this.options.getScrollGesturesEnabled();
    }

    public Boolean getZoomGesturesEnabled() {
        return this.options.getZoomGesturesEnabled();
    }

    public Boolean getTiltGesturesEnabled() {
        return this.options.getTiltGesturesEnabled();
    }

    public Boolean getRotateGesturesEnabled() {
        return this.options.getRotateGesturesEnabled();
    }

    public Boolean getLiteMode() {
        return this.options.getLiteMode();
    }

    public Boolean getMapToolbarEnabled() {
        return this.options.getMapToolbarEnabled();
    }

    public Bundle toBundle() {
        Bundle args = new Bundle();
        args.putParcelable("MapOptions", this.options);
        return args;
    }
}
