package android.support.p000v4.graphics.drawable;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;

@TargetApi(23)
/* renamed from: android.support.v4.graphics.drawable.DrawableCompatApi23 */
class DrawableCompatApi23 {
    public static boolean setLayoutDirection(Drawable drawable, int layoutDirection) {
        return drawable.setLayoutDirection(layoutDirection);
    }

    public static int getLayoutDirection(Drawable drawable) {
        return drawable.getLayoutDirection();
    }
}
