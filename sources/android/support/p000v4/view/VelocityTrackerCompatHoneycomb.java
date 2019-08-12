package android.support.p000v4.view;

import android.annotation.TargetApi;
import android.view.VelocityTracker;

@TargetApi(11)
/* renamed from: android.support.v4.view.VelocityTrackerCompatHoneycomb */
class VelocityTrackerCompatHoneycomb {
    public static float getXVelocity(VelocityTracker tracker, int pointerId) {
        return tracker.getXVelocity(pointerId);
    }

    public static float getYVelocity(VelocityTracker tracker, int pointerId) {
        return tracker.getYVelocity(pointerId);
    }
}
