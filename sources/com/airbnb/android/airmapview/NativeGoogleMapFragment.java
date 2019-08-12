package com.airbnb.android.airmapview;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polygon;
import com.google.maps.android.geojson.GeoJsonLayer;
import com.google.maps.android.geojson.GeoJsonPolygonStyle;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class NativeGoogleMapFragment extends SupportMapFragment implements AirMapInterface {
    /* access modifiers changed from: private */
    public GoogleMap googleMap;
    private GeoJsonLayer layerOnMap;
    /* access modifiers changed from: private */
    public final Map<Marker, AirMapMarker<?>> markers = new HashMap();
    private boolean myLocationEnabled;
    /* access modifiers changed from: private */
    public OnMapLoadedListener onMapLoadedListener;

    public static NativeGoogleMapFragment newInstance(AirGoogleMapOptions options) {
        return new NativeGoogleMapFragment().setArguments(options);
    }

    public NativeGoogleMapFragment setArguments(AirGoogleMapOptions options) {
        setArguments(options.toBundle());
        return this;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        init();
        return v;
    }

    public void init() {
        getMapAsync(new OnMapReadyCallback() {
            public void onMapReady(GoogleMap googleMap) {
                if (googleMap != null && NativeGoogleMapFragment.this.getActivity() != null) {
                    NativeGoogleMapFragment.this.googleMap = googleMap;
                    UiSettings settings = NativeGoogleMapFragment.this.googleMap.getUiSettings();
                    settings.setZoomControlsEnabled(false);
                    settings.setMyLocationButtonEnabled(false);
                    NativeGoogleMapFragment.this.setMyLocationEnabled(true);
                    if (NativeGoogleMapFragment.this.onMapLoadedListener != null) {
                        NativeGoogleMapFragment.this.onMapLoadedListener.onMapLoaded();
                    }
                }
            }
        });
    }

    public boolean isInitialized() {
        return (this.googleMap == null || getActivity() == null) ? false : true;
    }

    public void clearMarkers() {
        this.markers.clear();
        this.googleMap.clear();
    }

    public void addMarker(AirMapMarker<?> airMarker) {
        Marker marker = this.googleMap.addMarker(airMarker.getMarkerOptions());
        airMarker.setGoogleMarker(marker);
        this.markers.put(marker, airMarker);
    }

    public void moveMarker(AirMapMarker<?> marker, LatLng to) {
        marker.setLatLng(to);
        marker.getMarker().setPosition(to);
    }

    public void removeMarker(AirMapMarker<?> marker) {
        Marker nativeMarker = marker.getMarker();
        if (nativeMarker != null) {
            nativeMarker.remove();
            this.markers.remove(nativeMarker);
        }
    }

    public void setOnInfoWindowClickListener(final OnInfoWindowClickListener listener) {
        this.googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            public void onInfoWindowClick(Marker marker) {
                AirMapMarker<?> airMarker = (AirMapMarker) NativeGoogleMapFragment.this.markers.get(marker);
                if (airMarker != null) {
                    listener.onInfoWindowClick(airMarker);
                }
            }
        });
    }

    public void setInfoWindowCreator(InfoWindowAdapter adapter, InfoWindowCreator creator) {
        this.googleMap.setInfoWindowAdapter(adapter);
    }

    public void drawCircle(LatLng latLng, int radius) {
        drawCircle(latLng, radius, AirMapInterface.CIRCLE_BORDER_COLOR);
    }

    public void drawCircle(LatLng latLng, int radius, int borderColor) {
        drawCircle(latLng, radius, borderColor, 0);
    }

    public void drawCircle(LatLng latLng, int radius, int borderColor, int borderWidth) {
        drawCircle(latLng, radius, borderColor, borderWidth, AirMapInterface.CIRCLE_FILL_COLOR);
    }

    public void drawCircle(LatLng latLng, int radius, int borderColor, int borderWidth, int fillColor) {
        this.googleMap.addCircle(new CircleOptions().center(latLng).strokeColor(borderColor).strokeWidth((float) borderWidth).fillColor(fillColor).radius((double) radius));
    }

    public void getMapScreenBounds(OnMapBoundsCallback callback) {
        Projection projection = this.googleMap.getProjection();
        int hOffset = getResources().getDimensionPixelOffset(C1666R.dimen.map_horizontal_padding);
        int vOffset = getResources().getDimensionPixelOffset(C1666R.dimen.map_vertical_padding);
        Builder builder = LatLngBounds.builder();
        builder.include(projection.fromScreenLocation(new Point(hOffset, vOffset)));
        builder.include(projection.fromScreenLocation(new Point(getView().getWidth() - hOffset, vOffset)));
        builder.include(projection.fromScreenLocation(new Point(hOffset, getView().getHeight() - vOffset)));
        builder.include(projection.fromScreenLocation(new Point(getView().getWidth() - hOffset, getView().getHeight() - vOffset)));
        callback.onMapBoundsReady(builder.build());
    }

    public void getScreenLocation(LatLng latLng, OnLatLngScreenLocationCallback callback) {
        callback.onLatLngScreenLocationReady(this.googleMap.getProjection().toScreenLocation(latLng));
    }

    public void setCenter(LatLngBounds latLngBounds, int boundsPadding) {
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, boundsPadding));
    }

    public void setZoom(int zoom) {
        this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(this.googleMap.getCameraPosition().target, (float) zoom));
    }

    public void animateCenter(LatLng latLng) {
        this.googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    public void setCenter(LatLng latLng) {
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    public LatLng getCenter() {
        return this.googleMap.getCameraPosition().target;
    }

    public int getZoom() {
        return (int) this.googleMap.getCameraPosition().zoom;
    }

    public void setOnCameraChangeListener(final OnCameraChangeListener onCameraChangeListener) {
        this.googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            public void onCameraChange(CameraPosition cameraPosition) {
                if (NativeGoogleMapFragment.this.isResumed()) {
                    onCameraChangeListener.onCameraChanged(cameraPosition.target, (int) cameraPosition.zoom);
                }
            }
        });
    }

    public void setOnMapLoadedListener(OnMapLoadedListener onMapLoadedListener2) {
        this.onMapLoadedListener = onMapLoadedListener2;
    }

    public void setCenterZoom(LatLng latLng, int zoom) {
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, (float) zoom));
    }

    public void animateCenterZoom(LatLng latLng, int zoom) {
        this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, (float) zoom));
    }

    public void setOnMarkerClickListener(final OnMapMarkerClickListener listener) {
        this.googleMap.setOnMarkerClickListener(new OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                AirMapMarker<?> airMarker = (AirMapMarker) NativeGoogleMapFragment.this.markers.get(marker);
                if (airMarker != null) {
                    listener.onMapMarkerClick(airMarker);
                }
                return false;
            }
        });
    }

    public void setOnMarkerDragListener(final OnMapMarkerDragListener listener) {
        if (listener == null) {
            this.googleMap.setOnMarkerDragListener(null);
        } else {
            this.googleMap.setOnMarkerDragListener(new OnMarkerDragListener() {
                public void onMarkerDragStart(Marker marker) {
                    listener.onMapMarkerDragStart(marker);
                }

                public void onMarkerDrag(Marker marker) {
                    listener.onMapMarkerDrag(marker);
                }

                public void onMarkerDragEnd(Marker marker) {
                    listener.onMapMarkerDragEnd(marker);
                }
            });
        }
    }

    public void setOnMapClickListener(final OnMapClickListener listener) {
        this.googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            public void onMapClick(LatLng latLng) {
                listener.onMapClick(latLng);
            }
        });
    }

    public void setPadding(int left, int top, int right, int bottom) {
        this.googleMap.setPadding(left, top, right, bottom);
    }

    public void setMyLocationEnabled(boolean enabled) {
        if (this.myLocationEnabled != enabled && RuntimePermissionUtils.checkLocationPermissions(getActivity(), this)) {
            this.myLocationEnabled = enabled;
        }
    }

    public void onLocationPermissionsGranted() {
        this.googleMap.setMyLocationEnabled(this.myLocationEnabled);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        RuntimePermissionUtils.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    public boolean isMyLocationEnabled() {
        return this.googleMap.isMyLocationEnabled();
    }

    public void setMapToolbarEnabled(boolean enabled) {
        this.googleMap.getUiSettings().setMapToolbarEnabled(enabled);
    }

    public <T> void addPolyline(AirMapPolyline<T> polyline) {
        polyline.addToGoogleMap(this.googleMap);
    }

    public <T> void removePolyline(AirMapPolyline<T> polyline) {
        polyline.removeFromGoogleMap();
    }

    public <T> void addPolygon(AirMapPolygon<T> polygon) {
        polygon.setGooglePolygon(this.googleMap.addPolygon(polygon.getPolygonOptions()));
    }

    public <T> void removePolygon(AirMapPolygon<T> polygon) {
        Polygon nativePolygon = polygon.getGooglePolygon();
        if (nativePolygon != null) {
            nativePolygon.remove();
        }
    }

    public void setMapType(MapType type) {
        int nativeType;
        if (type == MapType.MAP_TYPE_NORMAL) {
            nativeType = 1;
        } else if (type == MapType.MAP_TYPE_SATELLITE) {
            nativeType = 2;
        } else if (type == MapType.MAP_TYPE_TERRAIN) {
            nativeType = 3;
        } else {
            nativeType = 1;
        }
        this.googleMap.setMapType(nativeType);
    }

    public GoogleMap getGoogleMap() {
        return this.googleMap;
    }

    public void setGeoJsonLayer(AirMapGeoJsonLayer airMapGeoJsonLayer) throws JSONException {
        clearGeoJsonLayer();
        this.layerOnMap = new GeoJsonLayer(this.googleMap, new JSONObject(airMapGeoJsonLayer.geoJson));
        GeoJsonPolygonStyle style = this.layerOnMap.getDefaultPolygonStyle();
        style.setStrokeColor(airMapGeoJsonLayer.strokeColor);
        style.setStrokeWidth(airMapGeoJsonLayer.strokeWidth);
        style.setFillColor(airMapGeoJsonLayer.fillColor);
        this.layerOnMap.addLayerToMap();
    }

    public void clearGeoJsonLayer() {
        if (this.layerOnMap != null) {
            this.layerOnMap.removeLayerFromMap();
            this.layerOnMap = null;
        }
    }

    public void getSnapshot(final OnSnapshotReadyListener listener) {
        getGoogleMap().snapshot(new SnapshotReadyCallback() {
            public void onSnapshotReady(Bitmap bitmap) {
                listener.onSnapshotReady(bitmap);
            }
        });
    }

    public void onDestroyView() {
        clearGeoJsonLayer();
        super.onDestroyView();
    }
}
