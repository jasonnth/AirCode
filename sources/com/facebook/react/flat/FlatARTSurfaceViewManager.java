package com.facebook.react.flat;

import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.views.art.ARTSurfaceView;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNodeAPI;

class FlatARTSurfaceViewManager extends BaseViewManager<ARTSurfaceView, FlatARTSurfaceViewShadowNode> {
    private static final YogaMeasureFunction MEASURE_FUNCTION = new YogaMeasureFunction() {
        public long measure(YogaNodeAPI node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
            throw new IllegalStateException("SurfaceView should have explicit width and height set");
        }
    };
    private static final String REACT_CLASS = "ARTSurfaceView";

    FlatARTSurfaceViewManager() {
    }

    public String getName() {
        return REACT_CLASS;
    }

    public FlatARTSurfaceViewShadowNode createShadowNodeInstance() {
        FlatARTSurfaceViewShadowNode node = new FlatARTSurfaceViewShadowNode();
        node.setMeasureFunction(MEASURE_FUNCTION);
        return node;
    }

    public Class<FlatARTSurfaceViewShadowNode> getShadowNodeClass() {
        return FlatARTSurfaceViewShadowNode.class;
    }

    /* access modifiers changed from: protected */
    public ARTSurfaceView createViewInstance(ThemedReactContext reactContext) {
        return new ARTSurfaceView(reactContext);
    }

    public void updateExtraData(ARTSurfaceView root, Object extraData) {
        root.setSurfaceTextureListener((FlatARTSurfaceViewShadowNode) extraData);
    }
}
