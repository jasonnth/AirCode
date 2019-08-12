package com.airbnb.android.core.utils;

import android.annotation.TargetApi;
import android.app.job.JobScheduler;
import android.content.Context;
import android.content.SharedPreferences;
import com.airbnb.android.airdate.AirDateConstants;
import com.airbnb.android.core.services.PullStringsOneOffService;
import com.airbnb.android.core.services.PullStringsPeriodicService;
import com.airbnb.android.lib.DynamicStringsHelper;
import com.airbnb.android.utils.LocaleUtil;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask.Builder;

public class PullStringsScheduler {
    public static final String FETCHED_LANGUAGE_KEY = "fetched_language";
    private static final String PULL_STRINGS_PERIODIC_TAG = "pull_strings_service_periodic";
    private final Context context;
    private final SharedPreferences stringsPreferences;

    public PullStringsScheduler(Context context2, SharedPreferences stringsPreferences2) {
        this.context = context2;
        this.stringsPreferences = stringsPreferences2;
    }

    public void pullStringsIfNeeded() {
        if (!DynamicStringsHelper.hasFetchedStrings(LocaleUtil.getCurrentDeviceLocale(this.context).getLanguage(), this.stringsPreferences)) {
            startOneOffPullStrings();
            startPeriodicPullStrings();
        }
    }

    private void startOneOffPullStrings() {
        this.context.startService(PullStringsOneOffService.intent(this.context));
    }

    private void startPeriodicPullStrings() {
        if (MiscUtils.hasGooglePlayServices(this.context)) {
            GcmNetworkManager.getInstance(this.context).schedule(new Builder().setPeriod(AirDateConstants.SECONDS_PER_DAY).setRequiredNetwork(1).setRequiresCharging(true).setUpdateCurrent(true).setFlex(AirDateConstants.SECONDS_PER_HOUR).setTag(PULL_STRINGS_PERIODIC_TAG).setService(PullStringsPeriodicService.class).build());
            return;
        }
        startServiceWithoutGooglePlay();
    }

    @TargetApi(21)
    private void startServiceWithoutGooglePlay() {
        if (ViewLibUtils.isAtLeastLollipop()) {
            JobScheduler jobScheduler = (JobScheduler) this.context.getSystemService("jobscheduler");
        }
    }
}
