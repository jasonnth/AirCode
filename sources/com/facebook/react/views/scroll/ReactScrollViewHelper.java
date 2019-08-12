package com.facebook.react.views.scroll;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;

public class ReactScrollViewHelper {
    public static final long MOMENTUM_DELAY = 20;

    public static void emitScrollEvent(ViewGroup scrollView) {
        emitScrollEvent(scrollView, ScrollEventType.SCROLL);
    }

    public static void emitScrollBeginDragEvent(ViewGroup scrollView) {
        emitScrollEvent(scrollView, ScrollEventType.BEGIN_DRAG);
    }

    public static void emitScrollEndDragEvent(ViewGroup scrollView) {
        emitScrollEvent(scrollView, ScrollEventType.END_DRAG);
    }

    public static void emitScrollMomentumBeginEvent(ViewGroup scrollView) {
        emitScrollEvent(scrollView, ScrollEventType.MOMENTUM_BEGIN);
    }

    public static void emitScrollMomentumEndEvent(ViewGroup scrollView) {
        emitScrollEvent(scrollView, ScrollEventType.MOMENTUM_END);
    }

    private static void emitScrollEvent(ViewGroup scrollView, ScrollEventType scrollEventType) {
        View contentView = scrollView.getChildAt(0);
        if (contentView != null) {
            ((UIManagerModule) ((ReactContext) scrollView.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(ScrollEvent.obtain(scrollView.getId(), scrollEventType, scrollView.getScrollX(), scrollView.getScrollY(), contentView.getWidth(), contentView.getHeight(), scrollView.getWidth(), scrollView.getHeight()));
        }
    }
}
