package com.airbnb.android.react.maps;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.p000v4.content.PermissionChecker;
import android.support.p000v4.view.GestureDetectorCompat;
import android.support.p000v4.view.MotionEventCompat;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.GoogleMap.OnPolygonClickListener;
import com.google.android.gms.maps.GoogleMap.OnPolylineClickListener;
import com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.VisibleRegion;
import com.jumio.gui.Drawables;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirMapView extends MapView implements InfoWindowAdapter, OnMarkerDragListener, OnMapReadyCallback {
    private static final String[] PERMISSIONS = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    private final int baseMapPadding = 50;
    private LatLngBounds boundsToMove;
    private boolean cacheEnabled = false;
    private ImageView cacheImageView;
    /* access modifiers changed from: private */
    public final ThemedReactContext context;
    private boolean destroyed = false;
    /* access modifiers changed from: private */
    public final EventDispatcher eventDispatcher;
    private final List<AirMapFeature> features = new ArrayList();
    private final GestureDetectorCompat gestureDetector;
    /* access modifiers changed from: private */
    public boolean handlePanDrag = false;
    /* access modifiers changed from: private */
    public Boolean isMapLoaded = Boolean.valueOf(false);
    private boolean isMonitoringRegion = false;
    /* access modifiers changed from: private */
    public boolean isTouchDown = false;
    /* access modifiers changed from: private */
    public LatLngBounds lastBoundsEmitted;
    private LifecycleEventListener lifecycleListener;
    private Integer loadingBackgroundColor = null;
    private Integer loadingIndicatorColor = null;
    /* access modifiers changed from: private */
    public final AirMapManager manager;
    public GoogleMap map;
    private RelativeLayout mapLoadingLayout;
    private ProgressBar mapLoadingProgressBar;
    /* access modifiers changed from: private */
    public final Map<Marker, AirMapMarker> markerMap = new HashMap();
    /* access modifiers changed from: private */
    public boolean moveOnMarkerPress = true;
    /* access modifiers changed from: private */
    public boolean paused = false;
    /* access modifiers changed from: private */
    public final Map<Polygon, AirMapPolygon> polygonMap = new HashMap();
    /* access modifiers changed from: private */
    public final Map<Polyline, AirMapPolyline> polylineMap = new HashMap();
    private final ScaleGestureDetector scaleDetector;
    /* access modifiers changed from: private */
    public boolean showUserLocation = false;
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        public void run() {
            VisibleRegion region;
            LatLngBounds bounds = null;
            Projection projection = AirMapView.this.map.getProjection();
            if (projection != null) {
                region = projection.getVisibleRegion();
            } else {
                region = null;
            }
            if (region != null) {
                bounds = region.latLngBounds;
            }
            if (bounds != null && (AirMapView.this.lastBoundsEmitted == null || LatLngBoundsUtils.BoundsAreDifferent(bounds, AirMapView.this.lastBoundsEmitted))) {
                LatLng center = AirMapView.this.map.getCameraPosition().target;
                AirMapView.this.lastBoundsEmitted = bounds;
                AirMapView.this.eventDispatcher.dispatchEvent(new RegionChangeEvent(AirMapView.this.getId(), bounds, center, true));
            }
            AirMapView.this.timerHandler.postDelayed(this, 100);
        }
    };

    private static boolean contextHasBug(Context context2) {
        return context2 == null || context2.getResources() == null || context2.getResources().getConfiguration() == null;
    }

    private static Context getNonBuggyContext(ThemedReactContext reactContext) {
        ThemedReactContext themedReactContext = reactContext;
        if (!contextHasBug(themedReactContext)) {
            return themedReactContext;
        }
        if (!contextHasBug(reactContext.getCurrentActivity())) {
            return reactContext.getCurrentActivity();
        }
        if (!contextHasBug(reactContext.getApplicationContext())) {
            return reactContext.getApplicationContext();
        }
        return themedReactContext;
    }

    public AirMapView(ThemedReactContext reactContext, AirMapManager manager2, GoogleMapOptions googleMapOptions) {
        super(getNonBuggyContext(reactContext), googleMapOptions);
        this.manager = manager2;
        this.context = reactContext;
        super.onCreate(null);
        super.onResume();
        super.getMapAsync(this);
        this.scaleDetector = new ScaleGestureDetector(reactContext, new SimpleOnScaleGestureListener() {
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                this.startMonitoringRegion();
                return true;
            }
        });
        this.gestureDetector = new GestureDetectorCompat(reactContext, new SimpleOnGestureListener() {
            public boolean onDoubleTap(MotionEvent e) {
                this.startMonitoringRegion();
                return false;
            }

            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (AirMapView.this.handlePanDrag) {
                    AirMapView.this.onPanDrag(e2);
                }
                this.startMonitoringRegion();
                return false;
            }
        });
        addOnLayoutChangeListener(new OnLayoutChangeListener() {
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (!AirMapView.this.paused) {
                    AirMapView.this.cacheView();
                }
            }
        });
        this.eventDispatcher = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
    }

    public void onMapReady(final GoogleMap map2) {
        if (!this.destroyed) {
            this.map = map2;
            this.map.setInfoWindowAdapter(this);
            this.map.setOnMarkerDragListener(this);
            this.manager.pushEvent(this.context, this, "onMapReady", new WritableNativeMap());
            map2.setOnMarkerClickListener(new OnMarkerClickListener() {
                public boolean onMarkerClick(Marker marker) {
                    AirMapMarker airMapMarker = (AirMapMarker) AirMapView.this.markerMap.get(marker);
                    WritableMap event = AirMapView.this.makeClickEventData(marker.getPosition());
                    event.putString("action", "marker-press");
                    event.putString("id", airMapMarker.getIdentifier());
                    AirMapView.this.manager.pushEvent(AirMapView.this.context, this, "onMarkerPress", event);
                    WritableMap event2 = AirMapView.this.makeClickEventData(marker.getPosition());
                    event2.putString("action", "marker-press");
                    event2.putString("id", airMapMarker.getIdentifier());
                    AirMapView.this.manager.pushEvent(AirMapView.this.context, (View) AirMapView.this.markerMap.get(marker), "onPress", event2);
                    if (this.moveOnMarkerPress) {
                        return false;
                    }
                    marker.showInfoWindow();
                    return true;
                }
            });
            map2.setOnPolygonClickListener(new OnPolygonClickListener() {
                public void onPolygonClick(Polygon polygon) {
                    WritableMap event = AirMapView.this.makeClickEventData((LatLng) polygon.getPoints().get(0));
                    event.putString("action", "polygon-press");
                    AirMapView.this.manager.pushEvent(AirMapView.this.context, (View) AirMapView.this.polygonMap.get(polygon), "onPress", event);
                }
            });
            map2.setOnPolylineClickListener(new OnPolylineClickListener() {
                public void onPolylineClick(Polyline polyline) {
                    WritableMap event = AirMapView.this.makeClickEventData((LatLng) polyline.getPoints().get(0));
                    event.putString("action", "polyline-press");
                    AirMapView.this.manager.pushEvent(AirMapView.this.context, (View) AirMapView.this.polylineMap.get(polyline), "onPress", event);
                }
            });
            map2.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
                public void onInfoWindowClick(Marker marker) {
                    WritableMap event = AirMapView.this.makeClickEventData(marker.getPosition());
                    event.putString("action", "callout-press");
                    AirMapView.this.manager.pushEvent(AirMapView.this.context, this, "onCalloutPress", event);
                    WritableMap event2 = AirMapView.this.makeClickEventData(marker.getPosition());
                    event2.putString("action", "callout-press");
                    AirMapMarker markerView = (AirMapMarker) AirMapView.this.markerMap.get(marker);
                    AirMapView.this.manager.pushEvent(AirMapView.this.context, markerView, "onCalloutPress", event2);
                    WritableMap event3 = AirMapView.this.makeClickEventData(marker.getPosition());
                    event3.putString("action", "callout-press");
                    AirMapCallout infoWindow = markerView.getCalloutView();
                    if (infoWindow != null) {
                        AirMapView.this.manager.pushEvent(AirMapView.this.context, infoWindow, "onPress", event3);
                    }
                }
            });
            map2.setOnMapClickListener(new OnMapClickListener() {
                public void onMapClick(LatLng point) {
                    WritableMap event = AirMapView.this.makeClickEventData(point);
                    event.putString("action", "press");
                    AirMapView.this.manager.pushEvent(AirMapView.this.context, this, "onPress", event);
                }
            });
            map2.setOnMapLongClickListener(new OnMapLongClickListener() {
                public void onMapLongClick(LatLng point) {
                    AirMapView.this.makeClickEventData(point).putString("action", "long-press");
                    AirMapView.this.manager.pushEvent(AirMapView.this.context, this, "onLongPress", AirMapView.this.makeClickEventData(point));
                }
            });
            map2.setOnCameraChangeListener(new OnCameraChangeListener() {
                public void onCameraChange(CameraPosition position) {
                    LatLngBounds bounds = map2.getProjection().getVisibleRegion().latLngBounds;
                    LatLng center = position.target;
                    AirMapView.this.lastBoundsEmitted = bounds;
                    AirMapView.this.eventDispatcher.dispatchEvent(new RegionChangeEvent(AirMapView.this.getId(), bounds, center, AirMapView.this.isTouchDown));
                    this.stopMonitoringRegion();
                }
            });
            map2.setOnMapLoadedCallback(new OnMapLoadedCallback() {
                public void onMapLoaded() {
                    AirMapView.this.isMapLoaded = Boolean.valueOf(true);
                    AirMapView.this.cacheView();
                }
            });
            this.lifecycleListener = new LifecycleEventListener() {
                public void onHostResume() {
                    if (AirMapView.this.hasPermissions()) {
                        map2.setMyLocationEnabled(AirMapView.this.showUserLocation);
                    }
                    synchronized (AirMapView.this) {
                        AirMapView.this.onResume();
                        AirMapView.this.paused = false;
                    }
                }

                public void onHostPause() {
                    if (AirMapView.this.hasPermissions()) {
                        map2.setMyLocationEnabled(false);
                    }
                    synchronized (AirMapView.this) {
                        AirMapView.this.onPause();
                        AirMapView.this.paused = true;
                    }
                }

                public void onHostDestroy() {
                    AirMapView.this.doDestroy();
                }
            };
            this.context.addLifecycleEventListener(this.lifecycleListener);
        }
    }

    /* access modifiers changed from: private */
    public boolean hasPermissions() {
        if (PermissionChecker.checkSelfPermission(getContext(), PERMISSIONS[0]) == 0 || PermissionChecker.checkSelfPermission(getContext(), PERMISSIONS[1]) == 0) {
            return true;
        }
        return false;
    }

    public synchronized void doDestroy() {
        if (!this.destroyed) {
            this.destroyed = true;
            if (!(this.lifecycleListener == null || this.context == null)) {
                this.context.removeLifecycleEventListener(this.lifecycleListener);
                this.lifecycleListener = null;
            }
            if (!this.paused) {
                onPause();
                this.paused = true;
            }
            onDestroy();
        }
    }

    public void setRegion(ReadableMap region) {
        if (region != null) {
            Double lng = Double.valueOf(region.getDouble("longitude"));
            Double lat = Double.valueOf(region.getDouble("latitude"));
            Double lngDelta = Double.valueOf(region.getDouble("longitudeDelta"));
            Double latDelta = Double.valueOf(region.getDouble("latitudeDelta"));
            LatLngBounds bounds = new LatLngBounds(new LatLng(lat.doubleValue() - (latDelta.doubleValue() / 2.0d), lng.doubleValue() - (lngDelta.doubleValue() / 2.0d)), new LatLng(lat.doubleValue() + (latDelta.doubleValue() / 2.0d), lng.doubleValue() + (lngDelta.doubleValue() / 2.0d)));
            if (super.getHeight() <= 0 || super.getWidth() <= 0) {
                this.map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat.doubleValue(), lng.doubleValue()), 10.0f));
                this.boundsToMove = bounds;
                return;
            }
            this.map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0));
            this.boundsToMove = null;
        }
    }

    public void setShowsUserLocation(boolean showUserLocation2) {
        this.showUserLocation = showUserLocation2;
        if (hasPermissions()) {
            this.map.setMyLocationEnabled(showUserLocation2);
        }
    }

    public void setShowsMyLocationButton(boolean showMyLocationButton) {
        if (hasPermissions()) {
            this.map.getUiSettings().setMyLocationButtonEnabled(showMyLocationButton);
        }
    }

    public void setToolbarEnabled(boolean toolbarEnabled) {
        if (hasPermissions()) {
            this.map.getUiSettings().setMapToolbarEnabled(toolbarEnabled);
        }
    }

    public void setCacheEnabled(boolean cacheEnabled2) {
        this.cacheEnabled = cacheEnabled2;
        cacheView();
    }

    public void enableMapLoading(boolean loadingEnabled) {
        if (loadingEnabled && !this.isMapLoaded.booleanValue()) {
            getMapLoadingLayoutView().setVisibility(0);
        }
    }

    public void setMoveOnMarkerPress(boolean moveOnPress) {
        this.moveOnMarkerPress = moveOnPress;
    }

    public void setLoadingBackgroundColor(Integer loadingBackgroundColor2) {
        this.loadingBackgroundColor = loadingBackgroundColor2;
        if (this.mapLoadingLayout == null) {
            return;
        }
        if (loadingBackgroundColor2 == null) {
            this.mapLoadingLayout.setBackgroundColor(-1);
        } else {
            this.mapLoadingLayout.setBackgroundColor(this.loadingBackgroundColor.intValue());
        }
    }

    public void setLoadingIndicatorColor(Integer loadingIndicatorColor2) {
        this.loadingIndicatorColor = loadingIndicatorColor2;
        if (this.mapLoadingProgressBar != null) {
            Integer color = loadingIndicatorColor2;
            if (color == null) {
                color = Integer.valueOf(Color.parseColor("#606060"));
            }
            if (VERSION.SDK_INT >= 21) {
                ColorStateList progressTintList = ColorStateList.valueOf(loadingIndicatorColor2.intValue());
                ColorStateList secondaryProgressTintList = ColorStateList.valueOf(loadingIndicatorColor2.intValue());
                ColorStateList indeterminateTintList = ColorStateList.valueOf(loadingIndicatorColor2.intValue());
                this.mapLoadingProgressBar.setProgressTintList(progressTintList);
                this.mapLoadingProgressBar.setSecondaryProgressTintList(secondaryProgressTintList);
                this.mapLoadingProgressBar.setIndeterminateTintList(indeterminateTintList);
                return;
            }
            Mode mode = Mode.SRC_IN;
            if (VERSION.SDK_INT <= 10) {
                mode = Mode.MULTIPLY;
            }
            if (this.mapLoadingProgressBar.getIndeterminateDrawable() != null) {
                this.mapLoadingProgressBar.getIndeterminateDrawable().setColorFilter(color.intValue(), mode);
            }
            if (this.mapLoadingProgressBar.getProgressDrawable() != null) {
                this.mapLoadingProgressBar.getProgressDrawable().setColorFilter(color.intValue(), mode);
            }
        }
    }

    public void setHandlePanDrag(boolean handlePanDrag2) {
        this.handlePanDrag = handlePanDrag2;
    }

    public void addFeature(View child, int index) {
        if (child instanceof AirMapMarker) {
            AirMapMarker annotation = (AirMapMarker) child;
            annotation.addToMap(this.map);
            this.features.add(index, annotation);
            this.markerMap.put((Marker) annotation.getFeature(), annotation);
        } else if (child instanceof AirMapPolyline) {
            AirMapPolyline polylineView = (AirMapPolyline) child;
            polylineView.addToMap(this.map);
            this.features.add(index, polylineView);
            this.polylineMap.put((Polyline) polylineView.getFeature(), polylineView);
        } else if (child instanceof AirMapPolygon) {
            AirMapPolygon polygonView = (AirMapPolygon) child;
            polygonView.addToMap(this.map);
            this.features.add(index, polygonView);
            this.polygonMap.put((Polygon) polygonView.getFeature(), polygonView);
        } else if (child instanceof AirMapCircle) {
            AirMapCircle circleView = (AirMapCircle) child;
            circleView.addToMap(this.map);
            this.features.add(index, circleView);
        } else if (child instanceof AirMapUrlTile) {
            AirMapUrlTile urlTileView = (AirMapUrlTile) child;
            urlTileView.addToMap(this.map);
            this.features.add(index, urlTileView);
        } else {
            ViewGroup children = (ViewGroup) child;
            for (int i = 0; i < children.getChildCount(); i++) {
                addFeature(children.getChildAt(i), index);
            }
        }
    }

    public int getFeatureCount() {
        return this.features.size();
    }

    public View getFeatureAt(int index) {
        return (View) this.features.get(index);
    }

    public void removeFeatureAt(int index) {
        AirMapFeature feature = (AirMapFeature) this.features.remove(index);
        if (feature instanceof AirMapMarker) {
            this.markerMap.remove(feature.getFeature());
        }
        feature.removeFromMap(this.map);
    }

    public WritableMap makeClickEventData(LatLng point) {
        WritableMap event = new WritableNativeMap();
        WritableMap coordinate = new WritableNativeMap();
        coordinate.putDouble("latitude", point.latitude);
        coordinate.putDouble("longitude", point.longitude);
        event.putMap("coordinate", coordinate);
        Point screenPoint = this.map.getProjection().toScreenLocation(point);
        WritableMap position = new WritableNativeMap();
        position.putDouble("x", (double) screenPoint.x);
        position.putDouble("y", (double) screenPoint.y);
        event.putMap(ViewProps.POSITION, position);
        return event;
    }

    public void updateExtraData(Object extraData) {
        if (this.boundsToMove != null) {
            HashMap<String, Float> data = (HashMap) extraData;
            this.map.moveCamera(CameraUpdateFactory.newLatLngBounds(this.boundsToMove, (int) ((Float) data.get("width")).floatValue(), (int) ((Float) data.get("height")).floatValue(), 0));
            this.boundsToMove = null;
        }
    }

    public void animateToRegion(LatLngBounds bounds, int duration) {
        if (this.map != null) {
            startMonitoringRegion();
            this.map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0), duration, null);
        }
    }

    public void animateToCoordinate(LatLng coordinate, int duration) {
        if (this.map != null) {
            startMonitoringRegion();
            this.map.animateCamera(CameraUpdateFactory.newLatLng(coordinate), duration, null);
        }
    }

    public void fitToElements(boolean animated) {
        Builder builder = new Builder();
        boolean addedPosition = false;
        for (AirMapFeature feature : this.features) {
            if (feature instanceof AirMapMarker) {
                builder.include(((Marker) feature.getFeature()).getPosition());
                addedPosition = true;
            }
        }
        if (addedPosition) {
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(builder.build(), 50);
            if (animated) {
                startMonitoringRegion();
                this.map.animateCamera(cu);
                return;
            }
            this.map.moveCamera(cu);
        }
    }

    public void fitToSuppliedMarkers(ReadableArray markerIDsArray, boolean animated) {
        Builder builder = new Builder();
        String[] markerIDs = new String[markerIDsArray.size()];
        for (int i = 0; i < markerIDsArray.size(); i++) {
            markerIDs[i] = markerIDsArray.getString(i);
        }
        boolean addedPosition = false;
        List<String> markerIDList = Arrays.asList(markerIDs);
        for (AirMapFeature feature : this.features) {
            if (feature instanceof AirMapMarker) {
                Marker marker = (Marker) feature.getFeature();
                if (markerIDList.contains(((AirMapMarker) feature).getIdentifier())) {
                    builder.include(marker.getPosition());
                    addedPosition = true;
                }
            }
        }
        if (addedPosition) {
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(builder.build(), 50);
            if (animated) {
                startMonitoringRegion();
                this.map.animateCamera(cu);
                return;
            }
            this.map.moveCamera(cu);
        }
    }

    public void fitToCoordinates(ReadableArray coordinatesArray, ReadableMap edgePadding, boolean animated) {
        Builder builder = new Builder();
        for (int i = 0; i < coordinatesArray.size(); i++) {
            ReadableMap latLng = coordinatesArray.getMap(i);
            builder.include(new LatLng(Double.valueOf(latLng.getDouble("latitude")).doubleValue(), Double.valueOf(latLng.getDouble("longitude")).doubleValue()));
        }
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(builder.build(), 50);
        if (edgePadding != null) {
            this.map.setPadding(edgePadding.getInt(ViewProps.LEFT), edgePadding.getInt(ViewProps.TOP), edgePadding.getInt(ViewProps.RIGHT), edgePadding.getInt(ViewProps.BOTTOM));
        }
        if (animated) {
            startMonitoringRegion();
            this.map.animateCamera(cu);
        } else {
            this.map.moveCamera(cu);
        }
        this.map.setPadding(0, 0, 0, 0);
    }

    public View getInfoWindow(Marker marker) {
        return ((AirMapMarker) this.markerMap.get(marker)).getCallout();
    }

    public View getInfoContents(Marker marker) {
        return ((AirMapMarker) this.markerMap.get(marker)).getInfoContents();
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean z = false;
        this.scaleDetector.onTouchEvent(ev);
        this.gestureDetector.onTouchEvent(ev);
        switch (MotionEventCompat.getActionMasked(ev)) {
            case 0:
                ViewParent parent = getParent();
                if (this.map != null && this.map.getUiSettings().isScrollGesturesEnabled()) {
                    z = true;
                }
                parent.requestDisallowInterceptTouchEvent(z);
                this.isTouchDown = true;
                break;
            case 1:
                getParent().requestDisallowInterceptTouchEvent(false);
                this.isTouchDown = false;
                break;
            case 2:
                startMonitoringRegion();
                break;
        }
        super.dispatchTouchEvent(ev);
        return true;
    }

    public void startMonitoringRegion() {
        if (!this.isMonitoringRegion) {
            this.timerHandler.postDelayed(this.timerRunnable, 100);
            this.isMonitoringRegion = true;
        }
    }

    public void stopMonitoringRegion() {
        if (this.isMonitoringRegion) {
            this.timerHandler.removeCallbacks(this.timerRunnable);
            this.isMonitoringRegion = false;
        }
    }

    public void onMarkerDragStart(Marker marker) {
        this.manager.pushEvent(this.context, this, "onMarkerDragStart", makeClickEventData(marker.getPosition()));
        this.manager.pushEvent(this.context, (AirMapMarker) this.markerMap.get(marker), "onDragStart", makeClickEventData(marker.getPosition()));
    }

    public void onMarkerDrag(Marker marker) {
        this.manager.pushEvent(this.context, this, "onMarkerDrag", makeClickEventData(marker.getPosition()));
        this.manager.pushEvent(this.context, (AirMapMarker) this.markerMap.get(marker), "onDrag", makeClickEventData(marker.getPosition()));
    }

    public void onMarkerDragEnd(Marker marker) {
        this.manager.pushEvent(this.context, this, "onMarkerDragEnd", makeClickEventData(marker.getPosition()));
        this.manager.pushEvent(this.context, (AirMapMarker) this.markerMap.get(marker), "onDragEnd", makeClickEventData(marker.getPosition()));
    }

    private ProgressBar getMapLoadingProgressBar() {
        if (this.mapLoadingProgressBar == null) {
            this.mapLoadingProgressBar = new ProgressBar(getContext());
            this.mapLoadingProgressBar.setIndeterminate(true);
        }
        if (this.loadingIndicatorColor != null) {
            setLoadingIndicatorColor(this.loadingIndicatorColor);
        }
        return this.mapLoadingProgressBar;
    }

    private RelativeLayout getMapLoadingLayoutView() {
        if (this.mapLoadingLayout == null) {
            this.mapLoadingLayout = new RelativeLayout(getContext());
            this.mapLoadingLayout.setBackgroundColor(Drawables.DEFAULT_LIST_ROW_COLOR_PRESSED);
            addView(this.mapLoadingLayout, new LayoutParams(-1, -1));
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-2, -2);
            params.addRule(13);
            this.mapLoadingLayout.addView(getMapLoadingProgressBar(), params);
            this.mapLoadingLayout.setVisibility(4);
        }
        setLoadingBackgroundColor(this.loadingBackgroundColor);
        return this.mapLoadingLayout;
    }

    private ImageView getCacheImageView() {
        if (this.cacheImageView == null) {
            this.cacheImageView = new ImageView(getContext());
            addView(this.cacheImageView, new LayoutParams(-1, -1));
            this.cacheImageView.setVisibility(4);
        }
        return this.cacheImageView;
    }

    private void removeCacheImageView() {
        if (this.cacheImageView != null) {
            ((ViewGroup) this.cacheImageView.getParent()).removeView(this.cacheImageView);
            this.cacheImageView = null;
        }
    }

    private void removeMapLoadingProgressBar() {
        if (this.mapLoadingProgressBar != null) {
            ((ViewGroup) this.mapLoadingProgressBar.getParent()).removeView(this.mapLoadingProgressBar);
            this.mapLoadingProgressBar = null;
        }
    }

    private void removeMapLoadingLayoutView() {
        removeMapLoadingProgressBar();
        if (this.mapLoadingLayout != null) {
            ((ViewGroup) this.mapLoadingLayout.getParent()).removeView(this.mapLoadingLayout);
            this.mapLoadingLayout = null;
        }
    }

    /* access modifiers changed from: private */
    public void cacheView() {
        if (this.cacheEnabled) {
            final ImageView cacheImageView2 = getCacheImageView();
            final RelativeLayout mapLoadingLayout2 = getMapLoadingLayoutView();
            cacheImageView2.setVisibility(4);
            mapLoadingLayout2.setVisibility(0);
            if (this.isMapLoaded.booleanValue()) {
                this.map.snapshot(new SnapshotReadyCallback() {
                    public void onSnapshotReady(Bitmap bitmap) {
                        cacheImageView2.setImageBitmap(bitmap);
                        cacheImageView2.setVisibility(0);
                        mapLoadingLayout2.setVisibility(4);
                    }
                });
                return;
            }
            return;
        }
        removeCacheImageView();
        if (this.isMapLoaded.booleanValue()) {
            removeMapLoadingLayoutView();
        }
    }

    public void onPanDrag(MotionEvent ev) {
        this.manager.pushEvent(this.context, this, "onPanDrag", makeClickEventData(this.map.getProjection().fromScreenLocation(new Point((int) ev.getX(), (int) ev.getY()))));
    }
}
