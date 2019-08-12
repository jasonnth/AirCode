package p316jp.wasabeef.recyclerview.animators.internal;

import android.support.p000v4.view.ViewCompat;
import android.view.View;

/* renamed from: jp.wasabeef.recyclerview.animators.internal.ViewHelper */
public final class ViewHelper {
    public static void clear(View v) {
        ViewCompat.setAlpha(v, 1.0f);
        ViewCompat.setScaleY(v, 1.0f);
        ViewCompat.setScaleX(v, 1.0f);
        ViewCompat.setTranslationY(v, 0.0f);
        ViewCompat.setTranslationX(v, 0.0f);
        ViewCompat.setRotation(v, 0.0f);
        ViewCompat.setRotationY(v, 0.0f);
        ViewCompat.setRotationX(v, 0.0f);
        v.setPivotY((float) (v.getMeasuredHeight() / 2));
        ViewCompat.setPivotX(v, (float) (v.getMeasuredWidth() / 2));
        ViewCompat.animate(v).setInterpolator(null);
    }
}
