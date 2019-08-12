package com.facebook.react.uimanager.events;

import android.support.p000v4.util.Pools.SynchronizedPool;
import android.view.MotionEvent;
import com.facebook.infer.annotation.Assertions;

public class TouchEvent extends Event<TouchEvent> {
    private static final SynchronizedPool<TouchEvent> EVENTS_POOL = new SynchronizedPool<>(3);
    private static final int TOUCH_EVENTS_POOL_SIZE = 3;
    private short mCoalescingKey;
    private MotionEvent mMotionEvent;
    private TouchEventType mTouchEventType;
    private float mViewX;
    private float mViewY;

    public static TouchEvent obtain(int viewTag, TouchEventType touchEventType, MotionEvent motionEventToCopy, float viewX, float viewY, TouchEventCoalescingKeyHelper touchEventCoalescingKeyHelper) {
        TouchEvent event = (TouchEvent) EVENTS_POOL.acquire();
        if (event == null) {
            event = new TouchEvent();
        }
        event.init(viewTag, touchEventType, motionEventToCopy, viewX, viewY, touchEventCoalescingKeyHelper);
        return event;
    }

    private TouchEvent() {
    }

    private void init(int viewTag, TouchEventType touchEventType, MotionEvent motionEventToCopy, float viewX, float viewY, TouchEventCoalescingKeyHelper touchEventCoalescingKeyHelper) {
        super.init(viewTag);
        short coalescingKey = 0;
        int action = motionEventToCopy.getAction() & 255;
        switch (action) {
            case 0:
                touchEventCoalescingKeyHelper.addCoalescingKey(motionEventToCopy.getDownTime());
                break;
            case 1:
                touchEventCoalescingKeyHelper.removeCoalescingKey(motionEventToCopy.getDownTime());
                break;
            case 2:
                coalescingKey = touchEventCoalescingKeyHelper.getCoalescingKey(motionEventToCopy.getDownTime());
                break;
            case 3:
                touchEventCoalescingKeyHelper.removeCoalescingKey(motionEventToCopy.getDownTime());
                break;
            case 5:
            case 6:
                touchEventCoalescingKeyHelper.incrementCoalescingKey(motionEventToCopy.getDownTime());
                break;
            default:
                throw new RuntimeException("Unhandled MotionEvent action: " + action);
        }
        this.mTouchEventType = touchEventType;
        this.mMotionEvent = MotionEvent.obtain(motionEventToCopy);
        this.mCoalescingKey = coalescingKey;
        this.mViewX = viewX;
        this.mViewY = viewY;
    }

    public void onDispose() {
        ((MotionEvent) Assertions.assertNotNull(this.mMotionEvent)).recycle();
        this.mMotionEvent = null;
        EVENTS_POOL.release(this);
    }

    public String getEventName() {
        return ((TouchEventType) Assertions.assertNotNull(this.mTouchEventType)).getJSEventName();
    }

    public boolean canCoalesce() {
        switch ((TouchEventType) Assertions.assertNotNull(this.mTouchEventType)) {
            case START:
            case END:
            case CANCEL:
                return false;
            case MOVE:
                return true;
            default:
                throw new RuntimeException("Unknown touch event type: " + this.mTouchEventType);
        }
    }

    public short getCoalescingKey() {
        return this.mCoalescingKey;
    }

    public void dispatch(RCTEventEmitter rctEventEmitter) {
        TouchesHelper.sendTouchEvent(rctEventEmitter, (TouchEventType) Assertions.assertNotNull(this.mTouchEventType), getViewTag(), this);
    }

    public MotionEvent getMotionEvent() {
        Assertions.assertNotNull(this.mMotionEvent);
        return this.mMotionEvent;
    }

    public float getViewX() {
        return this.mViewX;
    }

    public float getViewY() {
        return this.mViewY;
    }
}
