package com.airbnb.android.airmapview;

import com.airbnb.android.airmapview.listeners.InfoWindowCreator;
import com.airbnb.android.airmapview.listeners.OnCameraChangeListener;
import com.airbnb.android.airmapview.listeners.OnInfoWindowClickListener;
import com.airbnb.android.airmapview.listeners.OnLatLngScreenLocationCallback;
import com.airbnb.android.airmapview.listeners.OnMapBoundsCallback;
import com.airbnb.android.airmapview.listeners.OnMapClickListener;
import com.airbnb.android.airmapview.listeners.OnMapLoadedListener;
import com.airbnb.android.airmapview.listeners.OnMapMarkerClickListener;
import com.airbnb.android.airmapview.listeners.OnMapMarkerDragListener;
import com.airbnb.android.airmapview.listeners.OnSnapshotReadyListener;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import org.json.JSONException;

public interface AirMapInterface {
    public static final int CIRCLE_BORDER_COLOR = -16777216;
    public static final int CIRCLE_BORDER_WIDTH = 0;
    public static final int CIRCLE_FILL_COLOR = -16723519;

    void addMarker(AirMapMarker<?> airMapMarker);

    <T> void addPolygon(AirMapPolygon<T> airMapPolygon);

    <T> void addPolyline(AirMapPolyline<T> airMapPolyline);

    void animateCenter(LatLng latLng);

    void animateCenterZoom(LatLng latLng, int i);

    void clearGeoJsonLayer();

    void clearMarkers();

    void drawCircle(LatLng latLng, int i);

    void drawCircle(LatLng latLng, int i, int i2);

    void drawCircle(LatLng latLng, int i, int i2, int i3);

    void drawCircle(LatLng latLng, int i, int i2, int i3, int i4);

    LatLng getCenter();

    void getMapScreenBounds(OnMapBoundsCallback onMapBoundsCallback);

    void getScreenLocation(LatLng latLng, OnLatLngScreenLocationCallback onLatLngScreenLocationCallback);

    void getSnapshot(OnSnapshotReadyListener onSnapshotReadyListener);

    int getZoom();

    boolean isInitialized();

    boolean isMyLocationEnabled();

    void moveMarker(AirMapMarker<?> airMapMarker, LatLng latLng);

    void onLocationPermissionsGranted();

    void removeMarker(AirMapMarker<?> airMapMarker);

    <T> void removePolygon(AirMapPolygon<T> airMapPolygon);

    <T> void removePolyline(AirMapPolyline<T> airMapPolyline);

    void setCenter(LatLng latLng);

    void setCenter(LatLngBounds latLngBounds, int i);

    void setCenterZoom(LatLng latLng, int i);

    void setGeoJsonLayer(AirMapGeoJsonLayer airMapGeoJsonLayer) throws JSONException;

    void setInfoWindowCreator(InfoWindowAdapter infoWindowAdapter, InfoWindowCreator infoWindowCreator);

    void setMapToolbarEnabled(boolean z);

    void setMapType(MapType mapType);

    void setMyLocationEnabled(boolean z);

    void setOnCameraChangeListener(OnCameraChangeListener onCameraChangeListener);

    void setOnInfoWindowClickListener(OnInfoWindowClickListener onInfoWindowClickListener);

    void setOnMapClickListener(OnMapClickListener onMapClickListener);

    void setOnMapLoadedListener(OnMapLoadedListener onMapLoadedListener);

    void setOnMarkerClickListener(OnMapMarkerClickListener onMapMarkerClickListener);

    void setOnMarkerDragListener(OnMapMarkerDragListener onMapMarkerDragListener);

    void setPadding(int i, int i2, int i3, int i4);

    void setZoom(int i);
}
