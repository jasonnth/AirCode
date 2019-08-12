package android.support.p000v4.view;

import android.annotation.TargetApi;
import android.view.MotionEvent;

@TargetApi(12)
/* renamed from: android.support.v4.view.MotionEventCompatHoneycombMr1 */
class MotionEventCompatHoneycombMr1 {
    static float getAxisValue(MotionEvent event, int axis) {
        return event.getAxisValue(axis);
    }
}
