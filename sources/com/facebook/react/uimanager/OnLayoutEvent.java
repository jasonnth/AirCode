package com.facebook.react.uimanager;

import android.support.p000v4.util.Pools.SynchronizedPool;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class OnLayoutEvent extends Event<OnLayoutEvent> {
    private static final SynchronizedPool<OnLayoutEvent> EVENTS_POOL = new SynchronizedPool<>(20);
    private int mHeight;
    private int mWidth;

    /* renamed from: mX */
    private int f3127mX;

    /* renamed from: mY */
    private int f3128mY;

    public static OnLayoutEvent obtain(int viewTag, int x, int y, int width, int height) {
        OnLayoutEvent event = (OnLayoutEvent) EVENTS_POOL.acquire();
        if (event == null) {
            event = new OnLayoutEvent();
        }
        event.init(viewTag, x, y, width, height);
        return event;
    }

    public void onDispose() {
        EVENTS_POOL.release(this);
    }

    private OnLayoutEvent() {
    }

    /* access modifiers changed from: protected */
    public void init(int viewTag, int x, int y, int width, int height) {
        super.init(viewTag);
        this.f3127mX = x;
        this.f3128mY = y;
        this.mWidth = width;
        this.mHeight = height;
    }

    public String getEventName() {
        return "topLayout";
    }

    public void dispatch(RCTEventEmitter rctEventEmitter) {
        WritableMap layout = Arguments.createMap();
        layout.putDouble("x", (double) PixelUtil.toDIPFromPixel((float) this.f3127mX));
        layout.putDouble("y", (double) PixelUtil.toDIPFromPixel((float) this.f3128mY));
        layout.putDouble("width", (double) PixelUtil.toDIPFromPixel((float) this.mWidth));
        layout.putDouble("height", (double) PixelUtil.toDIPFromPixel((float) this.mHeight));
        WritableMap event = Arguments.createMap();
        event.putMap("layout", layout);
        event.putInt(BaseAnalytics.TARGET, getViewTag());
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), event);
    }
}
