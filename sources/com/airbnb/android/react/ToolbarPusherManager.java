package com.airbnb.android.react;

import android.view.View;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.views.view.ReactViewGroup;
import java.util.Map;

class ToolbarPusherManager extends SimpleViewManager<ReactViewGroup> {
    private static final String REACT_CLASS = "RCTToolbarPusherView";
    private static final int VERSION = 1;

    ToolbarPusherManager() {
    }

    public Map<String, Object> getExportedViewConstants() {
        return MapBuilder.builder().put("VERSION", Integer.valueOf(1)).build();
    }

    public String getName() {
        return REACT_CLASS;
    }

    public ReactViewGroup createViewInstance(ThemedReactContext context) {
        ReactViewGroup view = new ReactViewGroup(context);
        View innerView = new View(context);
        view.addView(innerView);
        innerView.setId(C7663R.C7665id.n2_toolbar_pusher);
        return view;
    }
}
