package com.facebook.react.views.recyclerview;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper.ScrollCommandHandler;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper.ScrollToCommandData;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper.ScrollToEndCommandData;
import com.facebook.react.views.scroll.ScrollEventType;
import java.util.Map;

public class RecyclerViewBackedScrollViewManager extends ViewGroupManager<RecyclerViewBackedScrollView> implements ScrollCommandHandler<RecyclerViewBackedScrollView> {
    private static final String REACT_CLASS = "AndroidRecyclerViewBackedScrollView";

    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "onContentSizeChange")
    public void setOnContentSizeChange(RecyclerViewBackedScrollView view, boolean value) {
        view.setSendContentSizeChangeEvents(value);
    }

    /* access modifiers changed from: protected */
    public RecyclerViewBackedScrollView createViewInstance(ThemedReactContext reactContext) {
        return new RecyclerViewBackedScrollView(reactContext);
    }

    public void addView(RecyclerViewBackedScrollView parent, View child, int index) {
        parent.addViewToAdapter(child, index);
    }

    public int getChildCount(RecyclerViewBackedScrollView parent) {
        return parent.getChildCountFromAdapter();
    }

    public View getChildAt(RecyclerViewBackedScrollView parent, int index) {
        return parent.getChildAtFromAdapter(index);
    }

    public void removeViewAt(RecyclerViewBackedScrollView parent, int index) {
        parent.removeViewFromAdapter(index);
    }

    public void receiveCommand(RecyclerViewBackedScrollView view, int commandId, ReadableArray args) {
        ReactScrollViewCommandHelper.receiveCommand(this, view, commandId, args);
    }

    public void scrollTo(RecyclerViewBackedScrollView scrollView, ScrollToCommandData data) {
        scrollView.scrollTo(data.mDestX, data.mDestY, data.mAnimated);
    }

    public void scrollToEnd(RecyclerViewBackedScrollView scrollView, ScrollToEndCommandData data) {
    }

    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put(ScrollEventType.SCROLL.getJSEventName(), MapBuilder.m1882of("registrationName", "onScroll")).build();
    }
}
