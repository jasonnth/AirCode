package android.support.p000v4.graphics.drawable;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;

@TargetApi(11)
/* renamed from: android.support.v4.graphics.drawable.DrawableCompatHoneycomb */
class DrawableCompatHoneycomb {
    public static void jumpToCurrentState(Drawable drawable) {
        drawable.jumpToCurrentState();
    }

    public static Drawable wrapForTinting(Drawable drawable) {
        if (!(drawable instanceof TintAwareDrawable)) {
            return new DrawableWrapperHoneycomb(drawable);
        }
        return drawable;
    }
}
