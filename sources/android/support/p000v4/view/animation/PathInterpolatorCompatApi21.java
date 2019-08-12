package android.support.p000v4.view.animation;

import android.annotation.TargetApi;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

@TargetApi(21)
/* renamed from: android.support.v4.view.animation.PathInterpolatorCompatApi21 */
class PathInterpolatorCompatApi21 {
    public static Interpolator create(float controlX1, float controlY1, float controlX2, float controlY2) {
        return new PathInterpolator(controlX1, controlY1, controlX2, controlY2);
    }
}
