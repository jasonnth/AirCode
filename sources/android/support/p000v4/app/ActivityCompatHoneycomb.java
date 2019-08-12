package android.support.p000v4.app;

import android.annotation.TargetApi;
import android.app.Activity;

@TargetApi(11)
/* renamed from: android.support.v4.app.ActivityCompatHoneycomb */
class ActivityCompatHoneycomb {
    static void invalidateOptionsMenu(Activity activity) {
        activity.invalidateOptionsMenu();
    }
}
