package com.airbnb.android.airmapview.listeners;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public interface OnMapMarkerDragListener {
    void onMapMarkerDrag(long j, LatLng latLng);

    void onMapMarkerDrag(Marker marker);

    void onMapMarkerDragEnd(long j, LatLng latLng);

    void onMapMarkerDragEnd(Marker marker);

    void onMapMarkerDragStart(long j, LatLng latLng);

    void onMapMarkerDragStart(Marker marker);
}
