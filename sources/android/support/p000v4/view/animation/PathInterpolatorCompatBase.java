package android.support.p000v4.view.animation;

import android.annotation.TargetApi;
import android.view.animation.Interpolator;

@TargetApi(9)
/* renamed from: android.support.v4.view.animation.PathInterpolatorCompatBase */
class PathInterpolatorCompatBase {
    public static Interpolator create(float controlX1, float controlY1, float controlX2, float controlY2) {
        return new PathInterpolatorGingerbread(controlX1, controlY1, controlX2, controlY2);
    }
}
