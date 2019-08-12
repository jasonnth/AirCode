package android.support.p000v4.content.res;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;

@TargetApi(23)
/* renamed from: android.support.v4.content.res.ResourcesCompatApi23 */
class ResourcesCompatApi23 {
    public static int getColor(Resources res, int id, Theme theme) throws NotFoundException {
        return res.getColor(id, theme);
    }
}
