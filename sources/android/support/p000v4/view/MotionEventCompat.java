package android.support.p000v4.view;

import android.os.Build.VERSION;
import android.view.MotionEvent;
import com.airbnb.android.explore.views.ExploreBaseRangeSeekBar;

/* renamed from: android.support.v4.view.MotionEventCompat */
public final class MotionEventCompat {
    static final MotionEventVersionImpl IMPL;

    /* renamed from: android.support.v4.view.MotionEventCompat$BaseMotionEventVersionImpl */
    static class BaseMotionEventVersionImpl implements MotionEventVersionImpl {
        BaseMotionEventVersionImpl() {
        }

        public float getAxisValue(MotionEvent event, int axis) {
            return 0.0f;
        }
    }

    /* renamed from: android.support.v4.view.MotionEventCompat$HoneycombMr1MotionEventVersionImpl */
    static class HoneycombMr1MotionEventVersionImpl extends BaseMotionEventVersionImpl {
        HoneycombMr1MotionEventVersionImpl() {
        }

        public float getAxisValue(MotionEvent event, int axis) {
            return MotionEventCompatHoneycombMr1.getAxisValue(event, axis);
        }
    }

    /* renamed from: android.support.v4.view.MotionEventCompat$ICSMotionEventVersionImpl */
    private static class ICSMotionEventVersionImpl extends HoneycombMr1MotionEventVersionImpl {
        ICSMotionEventVersionImpl() {
        }
    }

    /* renamed from: android.support.v4.view.MotionEventCompat$MotionEventVersionImpl */
    interface MotionEventVersionImpl {
        float getAxisValue(MotionEvent motionEvent, int i);
    }

    static {
        if (VERSION.SDK_INT >= 14) {
            IMPL = new ICSMotionEventVersionImpl();
        } else if (VERSION.SDK_INT >= 12) {
            IMPL = new HoneycombMr1MotionEventVersionImpl();
        } else {
            IMPL = new BaseMotionEventVersionImpl();
        }
    }

    public static int getActionMasked(MotionEvent event) {
        return event.getAction() & 255;
    }

    public static int getActionIndex(MotionEvent event) {
        return (event.getAction() & ExploreBaseRangeSeekBar.ACTION_POINTER_INDEX_MASK) >> 8;
    }

    @Deprecated
    public static int getPointerId(MotionEvent event, int pointerIndex) {
        return event.getPointerId(pointerIndex);
    }

    public static float getAxisValue(MotionEvent event, int axis) {
        return IMPL.getAxisValue(event, axis);
    }
}
