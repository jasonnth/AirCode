package com.airbnb.android.react.maps;

import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import java.util.Map;

public class AirMapManager extends ViewGroupManager<AirMapView> {
    private static final int ANIMATE_TO_COORDINATE = 2;
    private static final int ANIMATE_TO_REGION = 1;
    private static final int FIT_TO_COORDINATES = 5;
    private static final int FIT_TO_ELEMENTS = 3;
    private static final int FIT_TO_SUPPLIED_MARKERS = 4;
    private static final String REACT_CLASS = "AIRMap";
    private final Map<String, Integer> MAP_TYPES = MapBuilder.m1886of("standard", Integer.valueOf(1), "satellite", Integer.valueOf(2), "hybrid", Integer.valueOf(4), "terrain", Integer.valueOf(3), "none", Integer.valueOf(0));
    private final ReactApplicationContext appContext;
    protected GoogleMapOptions googleMapOptions;

    public AirMapManager(ReactApplicationContext context) {
        this.appContext = context;
        this.googleMapOptions = new GoogleMapOptions();
    }

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public AirMapView createViewInstance(ThemedReactContext context) {
        return new AirMapView(context, this, this.googleMapOptions);
    }

    private void emitMapError(ThemedReactContext context, String message, String type) {
        WritableMap error = Arguments.createMap();
        error.putString("message", message);
        error.putString("type", type);
        ((RCTDeviceEventEmitter) context.getJSModule(RCTDeviceEventEmitter.class)).emit("onError", error);
    }

    @ReactProp(name = "region")
    public void setRegion(AirMapView view, ReadableMap region) {
        view.setRegion(region);
    }

    @ReactProp(name = "mapType")
    public void setMapType(AirMapView view, String mapType) {
        view.map.setMapType(((Integer) this.MAP_TYPES.get(mapType)).intValue());
    }

    @ReactProp(name = "customMapStyleString")
    public void setMapStyle(AirMapView view, String customMapStyleString) {
        view.map.setMapStyle(new MapStyleOptions(customMapStyleString));
    }

    @ReactProp(defaultBoolean = false, name = "showsUserLocation")
    public void setShowsUserLocation(AirMapView view, boolean showUserLocation) {
        view.setShowsUserLocation(showUserLocation);
    }

    @ReactProp(defaultBoolean = true, name = "showsMyLocationButton")
    public void setShowsMyLocationButton(AirMapView view, boolean showMyLocationButton) {
        view.setShowsMyLocationButton(showMyLocationButton);
    }

    @ReactProp(defaultBoolean = true, name = "toolbarEnabled")
    public void setToolbarEnabled(AirMapView view, boolean toolbarEnabled) {
        view.setToolbarEnabled(toolbarEnabled);
    }

    @ReactProp(defaultBoolean = false, name = "handlePanDrag")
    public void setHandlePanDrag(AirMapView view, boolean handlePanDrag) {
        view.setHandlePanDrag(handlePanDrag);
    }

    @ReactProp(defaultBoolean = false, name = "showsTraffic")
    public void setShowTraffic(AirMapView view, boolean showTraffic) {
        view.map.setTrafficEnabled(showTraffic);
    }

    @ReactProp(defaultBoolean = false, name = "showsBuildings")
    public void setShowBuildings(AirMapView view, boolean showBuildings) {
        view.map.setBuildingsEnabled(showBuildings);
    }

    @ReactProp(defaultBoolean = false, name = "showsIndoors")
    public void setShowIndoors(AirMapView view, boolean showIndoors) {
        view.map.setIndoorEnabled(showIndoors);
    }

    @ReactProp(defaultBoolean = false, name = "showsIndoorLevelPicker")
    public void setShowsIndoorLevelPicker(AirMapView view, boolean showsIndoorLevelPicker) {
        view.map.getUiSettings().setIndoorLevelPickerEnabled(showsIndoorLevelPicker);
    }

    @ReactProp(defaultBoolean = false, name = "showsCompass")
    public void setShowsCompass(AirMapView view, boolean showsCompass) {
        view.map.getUiSettings().setCompassEnabled(showsCompass);
    }

    @ReactProp(defaultBoolean = false, name = "scrollEnabled")
    public void setScrollEnabled(AirMapView view, boolean scrollEnabled) {
        view.map.getUiSettings().setScrollGesturesEnabled(scrollEnabled);
    }

    @ReactProp(defaultBoolean = false, name = "zoomEnabled")
    public void setZoomEnabled(AirMapView view, boolean zoomEnabled) {
        view.map.getUiSettings().setZoomGesturesEnabled(zoomEnabled);
    }

    @ReactProp(defaultBoolean = false, name = "rotateEnabled")
    public void setRotateEnabled(AirMapView view, boolean rotateEnabled) {
        view.map.getUiSettings().setRotateGesturesEnabled(rotateEnabled);
    }

    @ReactProp(defaultBoolean = false, name = "cacheEnabled")
    public void setCacheEnabled(AirMapView view, boolean cacheEnabled) {
        view.setCacheEnabled(cacheEnabled);
    }

    @ReactProp(defaultBoolean = false, name = "loadingEnabled")
    public void setLoadingEnabled(AirMapView view, boolean loadingEnabled) {
        view.enableMapLoading(loadingEnabled);
    }

    @ReactProp(defaultBoolean = true, name = "moveOnMarkerPress")
    public void setMoveOnMarkerPress(AirMapView view, boolean moveOnPress) {
        view.setMoveOnMarkerPress(moveOnPress);
    }

    @ReactProp(customType = "Color", name = "loadingBackgroundColor")
    public void setLoadingBackgroundColor(AirMapView view, Integer loadingBackgroundColor) {
        view.setLoadingBackgroundColor(loadingBackgroundColor);
    }

    @ReactProp(customType = "Color", name = "loadingIndicatorColor")
    public void setLoadingIndicatorColor(AirMapView view, Integer loadingIndicatorColor) {
        view.setLoadingIndicatorColor(loadingIndicatorColor);
    }

    @ReactProp(defaultBoolean = false, name = "pitchEnabled")
    public void setPitchEnabled(AirMapView view, boolean pitchEnabled) {
        view.map.getUiSettings().setTiltGesturesEnabled(pitchEnabled);
    }

    public void receiveCommand(AirMapView view, int commandId, ReadableArray args) {
        switch (commandId) {
            case 1:
                ReadableMap region = args.getMap(0);
                Integer duration = Integer.valueOf(args.getInt(1));
                Double lng = Double.valueOf(region.getDouble("longitude"));
                Double lat = Double.valueOf(region.getDouble("latitude"));
                Double lngDelta = Double.valueOf(region.getDouble("longitudeDelta"));
                Double latDelta = Double.valueOf(region.getDouble("latitudeDelta"));
                view.animateToRegion(new LatLngBounds(new LatLng(lat.doubleValue() - (latDelta.doubleValue() / 2.0d), lng.doubleValue() - (lngDelta.doubleValue() / 2.0d)), new LatLng(lat.doubleValue() + (latDelta.doubleValue() / 2.0d), lng.doubleValue() + (lngDelta.doubleValue() / 2.0d))), duration.intValue());
                return;
            case 2:
                ReadableMap region2 = args.getMap(0);
                Integer duration2 = Integer.valueOf(args.getInt(1));
                AirMapView airMapView = view;
                airMapView.animateToCoordinate(new LatLng(Double.valueOf(region2.getDouble("latitude")).doubleValue(), Double.valueOf(region2.getDouble("longitude")).doubleValue()), duration2.intValue());
                return;
            case 3:
                view.fitToElements(args.getBoolean(0));
                return;
            case 4:
                view.fitToSuppliedMarkers(args.getArray(0), args.getBoolean(1));
                return;
            case 5:
                view.fitToCoordinates(args.getArray(0), args.getMap(1), args.getBoolean(2));
                return;
            default:
                return;
        }
    }

    public Map getExportedCustomDirectEventTypeConstants() {
        Map<String, Map<String, String>> map = MapBuilder.m1888of("onMapReady", MapBuilder.m1882of("registrationName", "onMapReady"), "onPress", MapBuilder.m1882of("registrationName", "onPress"), "onLongPress", MapBuilder.m1882of("registrationName", "onLongPress"), "onMarkerPress", MapBuilder.m1882of("registrationName", "onMarkerPress"), "onMarkerSelect", MapBuilder.m1882of("registrationName", "onMarkerSelect"), "onMarkerDeselect", MapBuilder.m1882of("registrationName", "onMarkerDeselect"), "onCalloutPress", MapBuilder.m1882of("registrationName", "onCalloutPress"));
        map.putAll(MapBuilder.m1885of("onMarkerDragStart", MapBuilder.m1882of("registrationName", "onMarkerDragStart"), "onMarkerDrag", MapBuilder.m1882of("registrationName", "onMarkerDrag"), "onMarkerDragEnd", MapBuilder.m1882of("registrationName", "onMarkerDragEnd"), "onPanDrag", MapBuilder.m1882of("registrationName", "onPanDrag")));
        return map;
    }

    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.m1886of("animateToRegion", Integer.valueOf(1), "animateToCoordinate", Integer.valueOf(2), "fitToElements", Integer.valueOf(3), "fitToSuppliedMarkers", Integer.valueOf(4), "fitToCoordinates", Integer.valueOf(5));
    }

    public LayoutShadowNode createShadowNodeInstance() {
        return new SizeReportingShadowNode();
    }

    public void addView(AirMapView parent, View child, int index) {
        parent.addFeature(child, index);
    }

    public int getChildCount(AirMapView view) {
        return view.getFeatureCount();
    }

    public View getChildAt(AirMapView view, int index) {
        return view.getFeatureAt(index);
    }

    public void removeViewAt(AirMapView parent, int index) {
        parent.removeFeatureAt(index);
    }

    public void updateExtraData(AirMapView view, Object extraData) {
        view.updateExtraData(extraData);
    }

    /* access modifiers changed from: 0000 */
    public void pushEvent(ThemedReactContext context, View view, String name, WritableMap data) {
        ((RCTEventEmitter) context.getJSModule(RCTEventEmitter.class)).receiveEvent(view.getId(), name, data);
    }

    public void onDropViewInstance(AirMapView view) {
        view.doDestroy();
        super.onDropViewInstance(view);
    }
}
