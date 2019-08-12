package com.airbnb.android.react.maps;

import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;

public class AirMapPolylineManager extends ViewGroupManager<AirMapPolyline> {
    private final DisplayMetrics metrics;

    public AirMapPolylineManager(ReactApplicationContext reactContext) {
        if (VERSION.SDK_INT >= 17) {
            this.metrics = new DisplayMetrics();
            ((WindowManager) reactContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(this.metrics);
            return;
        }
        this.metrics = reactContext.getResources().getDisplayMetrics();
    }

    public String getName() {
        return "AIRMapPolyline";
    }

    public AirMapPolyline createViewInstance(ThemedReactContext context) {
        return new AirMapPolyline(context);
    }

    @ReactProp(name = "coordinates")
    public void setCoordinate(AirMapPolyline view, ReadableArray coordinates) {
        view.setCoordinates(coordinates);
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeWidth")
    public void setStrokeWidth(AirMapPolyline view, float widthInPoints) {
        view.setWidth(this.metrics.density * widthInPoints);
    }

    @ReactProp(customType = "Color", defaultInt = -65536, name = "strokeColor")
    public void setStrokeColor(AirMapPolyline view, int color) {
        view.setColor(color);
    }

    @ReactProp(defaultBoolean = false, name = "geodesic")
    public void setGeodesic(AirMapPolyline view, boolean geodesic) {
        view.setGeodesic(geodesic);
    }

    @ReactProp(defaultFloat = 1.0f, name = "zIndex")
    public void setZIndex(AirMapPolyline view, float zIndex) {
        view.setZIndex(zIndex);
    }

    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.m1882of("onPress", MapBuilder.m1882of("registrationName", "onPress"));
    }
}
