package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.TouchEvent;
import com.facebook.react.uimanager.events.TouchEventCoalescingKeyHelper;
import com.facebook.react.uimanager.events.TouchEventType;

public class RNSVGSvgView extends View {
    private Bitmap mBitmap;
    private EventDispatcher mEventDispatcher;
    private RCTEventEmitter mEventEmitter;
    private int mTargetTag;
    private final TouchEventCoalescingKeyHelper mTouchEventCoalescingKeyHelper = new TouchEventCoalescingKeyHelper();

    public enum Events {
        EVENT_DATA_URL("onDataURL");
        
        private final String mName;

        private Events(String name) {
            this.mName = name;
        }

        public String toString() {
            return this.mName;
        }
    }

    public RNSVGSvgView(ReactContext reactContext) {
        super(reactContext);
        this.mEventEmitter = (RCTEventEmitter) reactContext.getJSModule(RCTEventEmitter.class);
        this.mEventDispatcher = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
    }

    private RNSVGSvgViewShadowNode getShadowNode() {
        return RNSVGSvgViewShadowNode.getShadowNodeByTag(getId());
    }

    public void setBitmap(Bitmap bitmap) {
        if (this.mBitmap != null) {
            this.mBitmap.recycle();
        }
        this.mBitmap = bitmap;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mBitmap != null) {
            canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, null);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getShadowNode() != null) {
            this.mTargetTag = getShadowNode().hitTest(new Point((int) ev.getX(), (int) ev.getY()));
            if (this.mTargetTag != -1) {
                handleTouchEvent(ev);
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private int getAbsoluteLeft(View view) {
        int left = view.getLeft() - view.getScrollX();
        return (view.getParent() == view.getRootView() || (view.getParent() instanceof ReactRootView)) ? left : left + getAbsoluteLeft((View) view.getParent());
    }

    private int getAbsoluteTop(View view) {
        int top = view.getTop() - view.getScrollY();
        return (view.getParent() == view.getRootView() || (view.getParent() instanceof ReactRootView)) ? top : top + getAbsoluteTop((View) view.getParent());
    }

    private void dispatch(MotionEvent ev, TouchEventType type) {
        ev.offsetLocation((float) getAbsoluteLeft(this), (float) getAbsoluteTop(this));
        this.mEventDispatcher.dispatchEvent(TouchEvent.obtain(this.mTargetTag, type, ev, ev.getX(), ev.getY(), this.mTouchEventCoalescingKeyHelper));
    }

    public void handleTouchEvent(MotionEvent ev) {
        int action = ev.getAction() & 255;
        if (action == 0) {
            dispatch(ev, TouchEventType.START);
        } else if (this.mTargetTag == -1) {
            Log.e("error", "Unexpected state: received touch event but didn't get starting ACTION_DOWN for this gesture before");
        } else if (action == 1) {
            dispatch(ev, TouchEventType.END);
            this.mTargetTag = -1;
        } else if (action == 2) {
            dispatch(ev, TouchEventType.MOVE);
        } else if (action == 5) {
            dispatch(ev, TouchEventType.START);
        } else if (action == 6) {
            dispatch(ev, TouchEventType.END);
            this.mTargetTag = -1;
        } else if (action == 3) {
            dispatchCancelEvent(ev);
            this.mTargetTag = -1;
        } else {
            Log.w("IGNORE", "Warning : touch event was ignored. Action=" + action + " Target=" + this.mTargetTag);
        }
    }

    private void dispatchCancelEvent(MotionEvent ev) {
        if (this.mTargetTag == -1) {
            Log.w("error", "Can't cancel already finished gesture. Is a child View trying to start a gesture from an UP/CANCEL event?");
        } else {
            dispatch(ev, TouchEventType.CANCEL);
        }
    }

    public void onDataURL() {
        WritableMap event = Arguments.createMap();
        event.putString("base64", getShadowNode().getBase64());
        this.mEventEmitter.receiveEvent(getId(), Events.EVENT_DATA_URL.toString(), event);
    }
}
