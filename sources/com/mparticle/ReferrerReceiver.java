package com.mparticle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.analytics.MParticleAnalytics;
import com.mparticle.MParticle.LogLevel;
import com.mparticle.internal.ConfigManager;
import com.mparticle.internal.MPUtility;

public class ReferrerReceiver extends BroadcastReceiver {
    public final void onReceive(Context context, Intent intent) {
        setInstallReferrer(context, intent);
    }

    static void setInstallReferrer(Context context, Intent intent) {
        if (context == null) {
            ConfigManager.log(LogLevel.ERROR, "ReferrerReceiver Context can not be null");
        } else if (intent == null) {
            ConfigManager.log(LogLevel.ERROR, "ReferrerReceiver intent can not be null");
        } else if ("com.android.vending.INSTALL_REFERRER".equals(intent.getAction())) {
            setInstallReferrer(context, intent.getStringExtra("referrer"));
        }
    }

    static void setInstallReferrer(Context context, String referrer) {
        if (context != null) {
            context.getSharedPreferences(MParticleAnalytics.M_PARTICLE_SHARED_PREFERENCES, 0).edit().putString("mp::install_referrer", referrer).apply();
        }
    }

    public static Intent getMockInstallReferrerIntent(String referrer) {
        if (MPUtility.isEmpty(referrer)) {
            return null;
        }
        Intent intent = new Intent("com.android.vending.INSTALL_REFERRER");
        intent.putExtra("referrer", referrer);
        return intent;
    }
}
