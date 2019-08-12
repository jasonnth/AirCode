package android.support.p000v4.content.res;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;

@TargetApi(21)
/* renamed from: android.support.v4.content.res.ResourcesCompatApi21 */
class ResourcesCompatApi21 {
    public static Drawable getDrawable(Resources res, int id, Theme theme) throws NotFoundException {
        return res.getDrawable(id, theme);
    }
}
