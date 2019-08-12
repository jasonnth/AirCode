package com.airbnb.android.react;

import android.view.View;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

final class NoOpTextInlineImageViewManager extends SimpleViewManager<View> {
    NoOpTextInlineImageViewManager() {
    }

    public String getName() {
        return "RCTTextInlineImage";
    }

    /* access modifiers changed from: protected */
    public View createViewInstance(ThemedReactContext reactContext) {
        return new View(reactContext);
    }
}
