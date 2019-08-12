package android.support.p000v4.content;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;

@TargetApi(23)
/* renamed from: android.support.v4.content.ContextCompatApi23 */
class ContextCompatApi23 {
    public static ColorStateList getColorStateList(Context context, int id) {
        return context.getColorStateList(id);
    }

    public static int getColor(Context context, int id) {
        return context.getColor(id);
    }
}
