package com.airbnb.android.lib.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.AppLaunchAnalytics;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.GeneralAnalytics;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.fragments.AppUpgradeDialogFragment;
import com.airbnb.android.utils.Strap;

public class AppUpgradeReceiver extends BroadcastReceiver {
    AppLaunchAnalytics appLaunchAnalytics;
    AppLaunchUtils mAppLaunchUtils;

    public void onReceive(Context context, Intent intent) {
        AirbnbEventLogger.track(GeneralAnalytics.AppOpen, BaseAnalytics.addDeviceType(context, Strap.make().mo11639kv(BaseAnalytics.OPERATION, "app_upgrade").mo11639kv("china_install_tag", BuildHelper.chinaInstallTag())), true);
        ((AirbnbGraph) AirbnbApplication.instance(context).component()).inject(this);
        this.appLaunchAnalytics.trackColdLaunchCancelled("app_upgrade");
        if (BuildHelper.isReleaseBuild()) {
            AppUpgradeDialogFragment.cleanupOldUpdateDialogPrefs();
        }
    }
}
