package android.support.p000v4.content;

import android.annotation.TargetApi;
import android.content.Context;
import java.io.File;

@TargetApi(19)
/* renamed from: android.support.v4.content.ContextCompatKitKat */
class ContextCompatKitKat {
    public static File[] getExternalCacheDirs(Context context) {
        return context.getExternalCacheDirs();
    }

    public static File[] getExternalFilesDirs(Context context, String type) {
        return context.getExternalFilesDirs(type);
    }
}
