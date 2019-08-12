package android.support.p000v4.view;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.view.Gravity;

@TargetApi(17)
/* renamed from: android.support.v4.view.GravityCompatJellybeanMr1 */
class GravityCompatJellybeanMr1 {
    public static int getAbsoluteGravity(int gravity, int layoutDirection) {
        return Gravity.getAbsoluteGravity(gravity, layoutDirection);
    }

    public static void apply(int gravity, int w, int h, Rect container, Rect outRect, int layoutDirection) {
        Gravity.apply(gravity, w, h, container, outRect, layoutDirection);
    }
}
