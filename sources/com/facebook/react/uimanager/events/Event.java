package com.facebook.react.uimanager.events;

import com.facebook.react.common.SystemClock;
import com.facebook.react.uimanager.events.Event;

public abstract class Event<T extends Event> {
    private static int sUniqueID = 0;
    private boolean mInitialized;
    private long mTimestampMs;
    private int mUniqueID;
    private int mViewTag;

    public abstract void dispatch(RCTEventEmitter rCTEventEmitter);

    public abstract String getEventName();

    protected Event() {
        int i = sUniqueID;
        sUniqueID = i + 1;
        this.mUniqueID = i;
    }

    protected Event(int viewTag) {
        int i = sUniqueID;
        sUniqueID = i + 1;
        this.mUniqueID = i;
        init(viewTag);
    }

    /* access modifiers changed from: protected */
    public void init(int viewTag) {
        this.mViewTag = viewTag;
        this.mTimestampMs = SystemClock.uptimeMillis();
        this.mInitialized = true;
    }

    public final int getViewTag() {
        return this.mViewTag;
    }

    public final long getTimestampMs() {
        return this.mTimestampMs;
    }

    public boolean canCoalesce() {
        return true;
    }

    public T coalesce(T otherEvent) {
        return getTimestampMs() >= otherEvent.getTimestampMs() ? this : otherEvent;
    }

    public short getCoalescingKey() {
        return 0;
    }

    public int getUniqueID() {
        return this.mUniqueID;
    }

    public void onDispose() {
    }

    /* access modifiers changed from: 0000 */
    public boolean isInitialized() {
        return this.mInitialized;
    }

    /* access modifiers changed from: 0000 */
    public final void dispose() {
        this.mInitialized = false;
        onDispose();
    }
}
