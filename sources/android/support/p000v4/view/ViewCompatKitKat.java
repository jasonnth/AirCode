package android.support.p000v4.view;

import android.annotation.TargetApi;
import android.view.View;

@TargetApi(19)
/* renamed from: android.support.v4.view.ViewCompatKitKat */
class ViewCompatKitKat {
    public static void setAccessibilityLiveRegion(View view, int mode) {
        view.setAccessibilityLiveRegion(mode);
    }

    public static boolean isLaidOut(View view) {
        return view.isLaidOut();
    }

    public static boolean isAttachedToWindow(View view) {
        return view.isAttachedToWindow();
    }
}
