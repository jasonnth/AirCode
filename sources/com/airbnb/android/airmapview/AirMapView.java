package com.airbnb.android.airmapview;

import android.content.Context;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.airbnb.android.airmapview.listeners.InfoWindowCreator;
import com.airbnb.android.airmapview.listeners.OnCameraChangeListener;
import com.airbnb.android.airmapview.listeners.OnCameraMoveListener;
import com.airbnb.android.airmapview.listeners.OnInfoWindowClickListener;
import com.airbnb.android.airmapview.listeners.OnLatLngScreenLocationCallback;
import com.airbnb.android.airmapview.listeners.OnMapBoundsCallback;
import com.airbnb.android.airmapview.listeners.OnMapClickListener;
import com.airbnb.android.airmapview.listeners.OnMapInitializedListener;
import com.airbnb.android.airmapview.listeners.OnMapLoadedListener;
import com.airbnb.android.airmapview.listeners.OnMapMarkerClickListener;
import com.airbnb.android.airmapview.listeners.OnMapMarkerDragListener;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import org.json.JSONException;

public class AirMapView extends FrameLayout implements OnCameraChangeListener, OnInfoWindowClickListener, OnMapClickListener, OnMapLoadedListener, OnMapMarkerClickListener, OnMapMarkerDragListener {
    private static final int INVALID_ZOOM = -1;
    private boolean mOnCameraMoveTriggered;
    protected AirMapInterface mapInterface;
    private OnCameraChangeListener onCameraChangeListener;
    private OnCameraMoveListener onCameraMoveListener;
    private OnInfoWindowClickListener onInfoWindowClickListener;
    private OnMapClickListener onMapClickListener;
    private OnMapInitializedListener onMapInitializedListener;
    private OnMapMarkerClickListener onMapMarkerClickListener;
    private OnMapMarkerDragListener onMapMarkerDragListener;

    public AirMapView(Context context) {
        super(context);
        inflateView();
    }

    public AirMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateView();
    }

    public AirMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflateView();
    }

    private void inflateView() {
        LayoutInflater.from(getContext()).inflate(C1666R.layout.map_view, this);
    }

    public void initialize(FragmentManager fragmentManager, AirMapInterface mapInterface2) {
        if (mapInterface2 == null || fragmentManager == null) {
            throw new IllegalArgumentException("Either mapInterface or fragmentManager is null");
        }
        this.mapInterface = mapInterface2;
        this.mapInterface.setOnMapLoadedListener(this);
        fragmentManager.beginTransaction().replace(getId(), (Fragment) this.mapInterface).commit();
        fragmentManager.executePendingTransactions();
    }

    public void initialize(FragmentManager fragmentManager) {
        AirMapInterface mapInterface2 = (AirMapInterface) fragmentManager.findFragmentById(C1666R.C1668id.map_frame);
        if (mapInterface2 != null) {
            initialize(fragmentManager, mapInterface2);
        } else {
            initialize(fragmentManager, new DefaultAirMapViewBuilder(getContext()).builder().build());
        }
    }

    public void setOnMapInitializedListener(OnMapInitializedListener mapInitializedListener) {
        this.onMapInitializedListener = mapInitializedListener;
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 2) {
            if (this.onCameraMoveListener != null && !this.mOnCameraMoveTriggered) {
                this.onCameraMoveListener.onCameraMove();
                this.mOnCameraMoveTriggered = true;
            }
        } else if (ev.getAction() == 1) {
            this.mOnCameraMoveTriggered = false;
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setOnCameraChangeListener(OnCameraChangeListener onCameraChangeListener2) {
        this.onCameraChangeListener = onCameraChangeListener2;
    }

    public void setOnCameraMoveListener(OnCameraMoveListener onCameraMoveListener2) {
        this.onCameraMoveListener = onCameraMoveListener2;
    }

    public final AirMapInterface getMapInterface() {
        return this.mapInterface;
    }

    public void onDestroyView() {
        if (isInitialized()) {
            this.mapInterface.setMyLocationEnabled(false);
        }
    }

    public int getZoom() {
        if (isInitialized()) {
            return this.mapInterface.getZoom();
        }
        return -1;
    }

    public LatLng getCenter() {
        if (isInitialized()) {
            return this.mapInterface.getCenter();
        }
        return null;
    }

    public boolean setCenter(LatLng latLng) {
        if (!isInitialized()) {
            return false;
        }
        this.mapInterface.setCenter(latLng);
        return true;
    }

    public boolean animateCenter(LatLng latLng) {
        if (!isInitialized()) {
            return false;
        }
        this.mapInterface.animateCenter(latLng);
        return true;
    }

    public boolean setZoom(int zoom) {
        if (!isInitialized()) {
            return false;
        }
        this.mapInterface.setZoom(zoom);
        return true;
    }

    public boolean setCenterZoom(LatLng latLng, int zoom) {
        if (!isInitialized()) {
            return false;
        }
        this.mapInterface.setCenterZoom(latLng, zoom);
        return true;
    }

    public boolean animateCenterZoom(LatLng latLng, int zoom) {
        if (!isInitialized()) {
            return false;
        }
        this.mapInterface.animateCenterZoom(latLng, zoom);
        return true;
    }

    public boolean setBounds(LatLngBounds latLngBounds, int boundsPadding) {
        if (!isInitialized()) {
            return false;
        }
        this.mapInterface.setCenter(latLngBounds, boundsPadding);
        return true;
    }

    public void getScreenBounds(OnMapBoundsCallback callback) {
        if (isInitialized()) {
            this.mapInterface.getMapScreenBounds(callback);
        }
    }

    public void getMapMarkerScreenLocation(LatLng latLng, OnLatLngScreenLocationCallback callback) {
        if (isInitialized()) {
            this.mapInterface.getScreenLocation(latLng, callback);
        }
    }

    public void drawCircle(LatLng latLng, int radius) {
        if (isInitialized()) {
            this.mapInterface.drawCircle(latLng, radius);
        }
    }

    public void drawCircle(LatLng latLng, int radius, int strokeColor) {
        if (isInitialized()) {
            this.mapInterface.drawCircle(latLng, radius, strokeColor);
        }
    }

    public void drawCircle(LatLng latLng, int radius, int strokeColor, int strokeWidth) {
        if (isInitialized()) {
            this.mapInterface.drawCircle(latLng, radius, strokeColor, strokeWidth);
        }
    }

    public void drawCircle(LatLng latLng, int radius, int strokeColor, int strokeWidth, int fillColor) {
        if (isInitialized()) {
            this.mapInterface.drawCircle(latLng, radius, strokeColor, strokeWidth, fillColor);
        }
    }

    public void setPadding(int left, int top, int right, int bottom) {
        if (isInitialized()) {
            this.mapInterface.setPadding(left, top, right, bottom);
        }
    }

    public void setOnMarkerClickListener(OnMapMarkerClickListener listener) {
        this.onMapMarkerClickListener = listener;
    }

    public void setOnMarkerDragListener(OnMapMarkerDragListener listener) {
        this.onMapMarkerDragListener = listener;
    }

    public void setOnMapClickListener(OnMapClickListener listener) {
        this.onMapClickListener = listener;
    }

    public void setInfoWindowAdapter(InfoWindowAdapter adapter, InfoWindowCreator creator) {
        if (isInitialized()) {
            this.mapInterface.setInfoWindowCreator(adapter, creator);
        }
    }

    public void setOnInfoWindowClickListener(OnInfoWindowClickListener listener) {
        this.onInfoWindowClickListener = listener;
    }

    public void clearMarkers() {
        if (isInitialized()) {
            this.mapInterface.clearMarkers();
        }
    }

    public <T> boolean addPolyline(AirMapPolyline<T> polyline) {
        if (!isInitialized()) {
            return false;
        }
        this.mapInterface.addPolyline(polyline);
        return true;
    }

    public void setMapType(MapType mapType) {
        this.mapInterface.setMapType(mapType);
    }

    public <T> boolean removePolyline(AirMapPolyline<T> polyline) {
        if (!isInitialized()) {
            return false;
        }
        this.mapInterface.removePolyline(polyline);
        return true;
    }

    public <T> boolean addPolygon(AirMapPolygon<T> polygon) {
        if (!isInitialized()) {
            return false;
        }
        this.mapInterface.addPolygon(polygon);
        return true;
    }

    public <T> boolean removePolygon(AirMapPolygon<T> polygon) {
        if (!isInitialized()) {
            return false;
        }
        this.mapInterface.removePolygon(polygon);
        return true;
    }

    public void setGeoJsonLayer(AirMapGeoJsonLayer layer) throws JSONException {
        if (isInitialized()) {
            this.mapInterface.setGeoJsonLayer(layer);
        }
    }

    public void clearGeoJsonLayer() {
        if (isInitialized()) {
            this.mapInterface.clearGeoJsonLayer();
        }
    }

    public boolean isInitialized() {
        return this.mapInterface != null && this.mapInterface.isInitialized();
    }

    public boolean addMarker(AirMapMarker<?> marker) {
        if (!isInitialized()) {
            return false;
        }
        this.mapInterface.addMarker(marker);
        return true;
    }

    public boolean removeMarker(AirMapMarker<?> marker) {
        if (!isInitialized()) {
            return false;
        }
        this.mapInterface.removeMarker(marker);
        return true;
    }

    public boolean moveMarker(AirMapMarker<?> marker, LatLng to) {
        if (!isInitialized()) {
            return false;
        }
        this.mapInterface.moveMarker(marker, to);
        return true;
    }

    public void setMyLocationEnabled(boolean trackUserLocation) {
        this.mapInterface.setMyLocationEnabled(trackUserLocation);
    }

    public void onCameraChanged(LatLng latLng, int zoom) {
        if (this.onCameraChangeListener != null) {
            this.onCameraChangeListener.onCameraChanged(latLng, zoom);
        }
    }

    public void onMapClick(LatLng latLng) {
        if (this.onMapClickListener != null) {
            this.onMapClickListener.onMapClick(latLng);
        }
    }

    public void onMapMarkerClick(AirMapMarker<?> airMarker) {
        if (this.onMapMarkerClickListener != null) {
            this.onMapMarkerClickListener.onMapMarkerClick(airMarker);
        }
    }

    public void onMapMarkerDragStart(Marker marker) {
        if (this.onMapMarkerDragListener != null) {
            this.onMapMarkerDragListener.onMapMarkerDragStart(marker);
        }
    }

    public void onMapMarkerDrag(Marker marker) {
        if (this.onMapMarkerDragListener != null) {
            this.onMapMarkerDragListener.onMapMarkerDrag(marker);
        }
    }

    public void onMapMarkerDragEnd(Marker marker) {
        if (this.onMapMarkerDragListener != null) {
            this.onMapMarkerDragListener.onMapMarkerDragEnd(marker);
        }
    }

    public void onMapMarkerDragStart(long id, LatLng latLng) {
        if (this.onMapMarkerDragListener != null) {
            this.onMapMarkerDragListener.onMapMarkerDragStart(id, latLng);
        }
    }

    public void onMapMarkerDrag(long id, LatLng latLng) {
        if (this.onMapMarkerDragListener != null) {
            this.onMapMarkerDragListener.onMapMarkerDrag(id, latLng);
        }
    }

    public void onMapMarkerDragEnd(long id, LatLng latLng) {
        if (this.onMapMarkerDragListener != null) {
            this.onMapMarkerDragListener.onMapMarkerDragEnd(id, latLng);
        }
    }

    public void onMapLoaded() {
        if (isInitialized()) {
            this.mapInterface.setOnCameraChangeListener(this);
            this.mapInterface.setOnMapClickListener(this);
            this.mapInterface.setOnMarkerClickListener(this);
            this.mapInterface.setOnMarkerDragListener(this);
            this.mapInterface.setOnInfoWindowClickListener(this);
            if (this.onMapInitializedListener != null) {
                this.onMapInitializedListener.onMapInitialized();
            }
        }
    }

    public void onInfoWindowClick(AirMapMarker<?> airMarker) {
        if (this.onInfoWindowClickListener != null) {
            this.onInfoWindowClickListener.onInfoWindowClick(airMarker);
        }
    }
}
