package android.support.p000v4.view;

import android.annotation.TargetApi;
import android.view.View;

@TargetApi(23)
/* renamed from: android.support.v4.view.ViewCompatMarshmallow */
class ViewCompatMarshmallow {
    public static void setScrollIndicators(View view, int indicators, int mask) {
        view.setScrollIndicators(indicators, mask);
    }

    static void offsetTopAndBottom(View view, int offset) {
        view.offsetTopAndBottom(offset);
    }

    static void offsetLeftAndRight(View view, int offset) {
        view.offsetLeftAndRight(offset);
    }
}
