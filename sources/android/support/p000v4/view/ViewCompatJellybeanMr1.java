package android.support.p000v4.view;

import android.annotation.TargetApi;
import android.graphics.Paint;
import android.view.Display;
import android.view.View;

@TargetApi(17)
/* renamed from: android.support.v4.view.ViewCompatJellybeanMr1 */
class ViewCompatJellybeanMr1 {
    public static void setLayerPaint(View view, Paint paint) {
        view.setLayerPaint(paint);
    }

    public static int getLayoutDirection(View view) {
        return view.getLayoutDirection();
    }

    public static int getPaddingStart(View view) {
        return view.getPaddingStart();
    }

    public static int getPaddingEnd(View view) {
        return view.getPaddingEnd();
    }

    public static void setPaddingRelative(View view, int start, int top, int end, int bottom) {
        view.setPaddingRelative(start, top, end, bottom);
    }

    public static int getWindowSystemUiVisibility(View view) {
        return view.getWindowSystemUiVisibility();
    }

    public static boolean isPaddingRelative(View view) {
        return view.isPaddingRelative();
    }

    public static Display getDisplay(View view) {
        return view.getDisplay();
    }
}