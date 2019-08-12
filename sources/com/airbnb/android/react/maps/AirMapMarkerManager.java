package com.airbnb.android.react.maps;

import android.graphics.Color;
import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.google.android.gms.maps.model.Marker;
import java.util.HashMap;
import java.util.Map;

public class AirMapMarkerManager extends ViewGroupManager<AirMapMarker> {
    private static final int HIDE_INFO_WINDOW = 2;
    private static final int SHOW_INFO_WINDOW = 1;

    public String getName() {
        return "AIRMapMarker";
    }

    public AirMapMarker createViewInstance(ThemedReactContext context) {
        return new AirMapMarker(context);
    }

    @ReactProp(name = "coordinate")
    public void setCoordinate(AirMapMarker view, ReadableMap map) {
        view.setCoordinate(map);
    }

    @ReactProp(name = "title")
    public void setTitle(AirMapMarker view, String title) {
        view.setTitle(title);
    }

    @ReactProp(name = "identifier")
    public void setIdentifier(AirMapMarker view, String identifier) {
        view.setIdentifier(identifier);
    }

    @ReactProp(name = "description")
    public void setDescription(AirMapMarker view, String description) {
        view.setSnippet(description);
    }

    @ReactProp(name = "anchor")
    public void setAnchor(AirMapMarker view, ReadableMap map) {
        view.setAnchor((map == null || !map.hasKey("x")) ? 0.5d : map.getDouble("x"), (map == null || !map.hasKey("y")) ? 1.0d : map.getDouble("y"));
    }

    @ReactProp(name = "calloutAnchor")
    public void setCalloutAnchor(AirMapMarker view, ReadableMap map) {
        view.setCalloutAnchor((map == null || !map.hasKey("x")) ? 0.5d : map.getDouble("x"), (map == null || !map.hasKey("y")) ? 0.0d : map.getDouble("y"));
    }

    @ReactProp(name = "image")
    public void setImage(AirMapMarker view, String source) {
        view.setImage(source);
    }

    @ReactProp(customType = "Color", defaultInt = -65536, name = "pinColor")
    public void setPinColor(AirMapMarker view, int pinColor) {
        float[] hsv = new float[3];
        Color.colorToHSV(pinColor, hsv);
        view.setMarkerHue(hsv[0]);
    }

    @ReactProp(defaultFloat = 0.0f, name = "rotation")
    public void setMarkerRotation(AirMapMarker view, float rotation) {
        view.setRotation(rotation);
    }

    @ReactProp(defaultBoolean = false, name = "flat")
    public void setFlat(AirMapMarker view, boolean flat) {
        view.setFlat(flat);
    }

    @ReactProp(defaultBoolean = false, name = "draggable")
    public void setDraggable(AirMapMarker view, boolean draggable) {
        view.setDraggable(draggable);
    }

    @ReactProp(defaultFloat = 0.0f, name = "zIndex")
    public void setZIndex(AirMapMarker view, float zIndex) {
        super.setZIndex(view, zIndex);
        view.setZIndex(Math.round(zIndex));
    }

    @ReactProp(defaultFloat = 1.0f, name = "opacity")
    public void setOpacity(AirMapMarker view, float opacity) {
        super.setOpacity(view, opacity);
        view.setOpacity(opacity);
    }

    public void addView(AirMapMarker parent, View child, int index) {
        if (child instanceof AirMapCallout) {
            parent.setCalloutView((AirMapCallout) child);
            return;
        }
        super.addView(parent, child, index);
        parent.update();
    }

    public void removeViewAt(AirMapMarker parent, int index) {
        super.removeViewAt(parent, index);
        parent.update();
    }

    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.m1883of("showCallout", Integer.valueOf(1), "hideCallout", Integer.valueOf(2));
    }

    public void receiveCommand(AirMapMarker view, int commandId, ReadableArray args) {
        switch (commandId) {
            case 1:
                ((Marker) view.getFeature()).showInfoWindow();
                return;
            case 2:
                ((Marker) view.getFeature()).hideInfoWindow();
                return;
            default:
                return;
        }
    }

    public Map getExportedCustomDirectEventTypeConstants() {
        Map<String, Map<String, String>> map = MapBuilder.m1886of("onPress", MapBuilder.m1882of("registrationName", "onPress"), "onCalloutPress", MapBuilder.m1882of("registrationName", "onCalloutPress"), "onDragStart", MapBuilder.m1882of("registrationName", "onDragStart"), "onDrag", MapBuilder.m1882of("registrationName", "onDrag"), "onDragEnd", MapBuilder.m1882of("registrationName", "onDragEnd"));
        map.putAll(MapBuilder.m1884of("onDragStart", MapBuilder.m1882of("registrationName", "onDragStart"), "onDrag", MapBuilder.m1882of("registrationName", "onDrag"), "onDragEnd", MapBuilder.m1882of("registrationName", "onDragEnd")));
        return map;
    }

    public LayoutShadowNode createShadowNodeInstance() {
        return new SizeReportingShadowNode();
    }

    public void updateExtraData(AirMapMarker view, Object extraData) {
        HashMap<String, Float> data = (HashMap) extraData;
        view.update((int) ((Float) data.get("width")).floatValue(), (int) ((Float) data.get("height")).floatValue());
    }
}
