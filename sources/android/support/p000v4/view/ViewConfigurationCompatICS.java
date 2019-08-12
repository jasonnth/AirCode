package android.support.p000v4.view;

import android.annotation.TargetApi;
import android.view.ViewConfiguration;

@TargetApi(14)
/* renamed from: android.support.v4.view.ViewConfigurationCompatICS */
class ViewConfigurationCompatICS {
    static boolean hasPermanentMenuKey(ViewConfiguration config) {
        return config.hasPermanentMenuKey();
    }
}
