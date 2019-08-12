package android.support.p000v4.view;

import android.annotation.TargetApi;
import android.view.ViewGroup;

@TargetApi(11)
/* renamed from: android.support.v4.view.ViewGroupCompatHC */
class ViewGroupCompatHC {
    public static void setMotionEventSplittingEnabled(ViewGroup group, boolean split) {
        group.setMotionEventSplittingEnabled(split);
    }
}
