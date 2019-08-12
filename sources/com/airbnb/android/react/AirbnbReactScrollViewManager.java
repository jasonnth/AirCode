package com.airbnb.android.react;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.scroll.ReactScrollView;
import com.facebook.react.views.scroll.ReactScrollViewManager;
import java.util.Map;

class AirbnbReactScrollViewManager extends ReactScrollViewManager {
    private static final int VERSION = 1;
    private final ReactNavigationCoordinator coordinator;

    AirbnbReactScrollViewManager(ReactNavigationCoordinator coordinator2) {
        this.coordinator = coordinator2;
    }

    public Map<String, Object> getExportedViewConstants() {
        return MapBuilder.builder().put("VERSION", Integer.valueOf(1)).build();
    }

    public String getName() {
        return "AirbnbScrollView";
    }

    public ReactScrollView createViewInstance(ThemedReactContext context) {
        return new AirbnbReactScrollView(context);
    }

    @ReactProp(name = "ignoreScrollDeltaAndroid")
    public void setIgnoreScrollDeltaAndroid(AirbnbReactScrollView scrollView, boolean ignore) {
        scrollView.setIgnoreScrollDelta(ignore);
    }

    @ReactProp(name = "airbnbSceneInstanceId")
    public void setAirbnbSceneInstanceId(AirbnbReactScrollView scrollView, String id) {
        ReactInterface component = this.coordinator.componentFromId(id);
        if (component != null) {
            component.getAirActivity().runOnUiThread(AirbnbReactScrollViewManager$$Lambda$1.lambdaFactory$(this, scrollView, component));
        }
    }
}
