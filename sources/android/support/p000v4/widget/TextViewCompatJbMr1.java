package android.support.p000v4.widget;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

@TargetApi(17)
/* renamed from: android.support.v4.widget.TextViewCompatJbMr1 */
class TextViewCompatJbMr1 {
    public static void setCompoundDrawablesRelative(TextView textView, Drawable start, Drawable top, Drawable end, Drawable bottom) {
        Drawable drawable;
        boolean rtl = true;
        if (textView.getLayoutDirection() != 1) {
            rtl = false;
        }
        if (rtl) {
            drawable = end;
        } else {
            drawable = start;
        }
        if (!rtl) {
            start = end;
        }
        textView.setCompoundDrawables(drawable, top, start, bottom);
    }

    public static Drawable[] getCompoundDrawablesRelative(TextView textView) {
        boolean rtl = true;
        if (textView.getLayoutDirection() != 1) {
            rtl = false;
        }
        Drawable[] compounds = textView.getCompoundDrawables();
        if (rtl) {
            Drawable start = compounds[2];
            Drawable end = compounds[0];
            compounds[0] = start;
            compounds[2] = end;
        }
        return compounds;
    }
}
