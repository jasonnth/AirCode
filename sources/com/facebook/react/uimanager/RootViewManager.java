package com.facebook.react.uimanager;

import android.view.ViewGroup;

public class RootViewManager extends ViewGroupManager<ViewGroup> {
    public static final String REACT_CLASS = "RootView";

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public ViewGroup createViewInstance(ThemedReactContext reactContext) {
        return new SizeMonitoringFrameLayout(reactContext);
    }
}
