package com.facebook.react.flat;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

abstract class FlatViewManager extends ViewGroupManager<FlatViewGroup> {
    FlatViewManager() {
    }

    /* access modifiers changed from: protected */
    public FlatViewGroup createViewInstance(ThemedReactContext reactContext) {
        return new FlatViewGroup(reactContext);
    }

    public void setBackgroundColor(FlatViewGroup view, int backgroundColor) {
    }

    public void removeAllViews(FlatViewGroup parent) {
        parent.removeAllViewsInLayout();
    }
}
