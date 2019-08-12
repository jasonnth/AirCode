package com.horcrux.svg;

import android.graphics.Bitmap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.MapBuilder.Builder;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNodeAPI;
import com.horcrux.svg.RNSVGSvgView.Events;
import java.util.HashMap;
import java.util.Map;

public class RNSVGSvgViewManager extends BaseViewManager<RNSVGSvgView, RNSVGSvgViewShadowNode> {
    private static final int COMMAND_TO_DATA_URL = 100;
    private static final YogaMeasureFunction MEASURE_FUNCTION = new YogaMeasureFunction() {
        public long measure(YogaNodeAPI node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
            throw new IllegalStateException("SurfaceView should have explicit width and height set");
        }
    };
    private static final String REACT_CLASS = "RNSVGSvgView";

    public String getName() {
        return REACT_CLASS;
    }

    public Class<RNSVGSvgViewShadowNode> getShadowNodeClass() {
        return RNSVGSvgViewShadowNode.class;
    }

    public RNSVGSvgViewShadowNode createShadowNodeInstance() {
        RNSVGSvgViewShadowNode node = new RNSVGSvgViewShadowNode();
        node.setMeasureFunction(MEASURE_FUNCTION);
        return node;
    }

    /* access modifiers changed from: protected */
    public RNSVGSvgView createViewInstance(ThemedReactContext reactContext) {
        return new RNSVGSvgView(reactContext);
    }

    public void updateExtraData(RNSVGSvgView root, Object extraData) {
        root.setBitmap((Bitmap) extraData);
    }

    public Map<String, Integer> getCommandsMap() {
        Map<String, Integer> commandsMap = super.getCommandsMap();
        if (commandsMap == null) {
            commandsMap = new HashMap<>();
        }
        commandsMap.put("toDataURL", Integer.valueOf(100));
        return commandsMap;
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Events[] values;
        Builder<String, Object> builder = MapBuilder.builder();
        for (Events event : Events.values()) {
            builder.put(event.toString(), MapBuilder.m1882of("registrationName", event.toString()));
        }
        return builder.build();
    }

    public void receiveCommand(RNSVGSvgView root, int commandId, ReadableArray args) {
        super.receiveCommand(root, commandId, args);
        switch (commandId) {
            case 100:
                root.onDataURL();
                return;
            default:
                return;
        }
    }
}
