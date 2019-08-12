package android.support.p000v4.content.res;

import android.annotation.TargetApi;
import android.content.res.Resources;

@TargetApi(13)
/* renamed from: android.support.v4.content.res.ConfigurationHelperHoneycombMr2 */
class ConfigurationHelperHoneycombMr2 {
    static int getScreenHeightDp(Resources resources) {
        return resources.getConfiguration().screenHeightDp;
    }

    static int getScreenWidthDp(Resources resources) {
        return resources.getConfiguration().screenWidthDp;
    }

    static int getSmallestScreenWidthDp(Resources resources) {
        return resources.getConfiguration().smallestScreenWidthDp;
    }
}
