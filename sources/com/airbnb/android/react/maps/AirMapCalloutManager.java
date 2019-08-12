package com.airbnb.android.react.maps;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;

public class AirMapCalloutManager extends ViewGroupManager<AirMapCallout> {
    public String getName() {
        return "AIRMapCallout";
    }

    public AirMapCallout createViewInstance(ThemedReactContext context) {
        return new AirMapCallout(context);
    }

    @ReactProp(defaultBoolean = false, name = "tooltip")
    public void setTooltip(AirMapCallout view, boolean tooltip) {
        view.setTooltip(tooltip);
    }

    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.m1882of("onPress", MapBuilder.m1882of("registrationName", "onPress"));
    }

    public LayoutShadowNode createShadowNodeInstance() {
        return new SizeReportingShadowNode();
    }

    public void updateExtraData(AirMapCallout view, Object extraData) {
        Map<String, Float> data = (Map) extraData;
        float width = ((Float) data.get("width")).floatValue();
        float height = ((Float) data.get("height")).floatValue();
        view.width = (int) width;
        view.height = (int) height;
    }
}
