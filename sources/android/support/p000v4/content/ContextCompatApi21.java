package android.support.p000v4.content;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;

@TargetApi(21)
/* renamed from: android.support.v4.content.ContextCompatApi21 */
class ContextCompatApi21 {
    public static Drawable getDrawable(Context context, int id) {
        return context.getDrawable(id);
    }
}
