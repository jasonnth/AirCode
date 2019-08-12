package com.airbnb.android.core.analytics;

import android.content.Context;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.adtracking.GoogleAdvertisingIdProvider;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.utils.Strap;
import com.mparticle.MPEvent.Builder;
import com.mparticle.MParticle;

public class MParticleAnalytics {
    public static final String LOGOUT = "logout";
    public static final String LOG_IN = "log_in";
    public static final String M_PARTICLE_SHARED_PREFERENCES = "mParticlePrefs";
    public static final String P2_IMPRESSION_WITH_DATES = "p2_impression_with_dates";
    public static final String P3_IMPRESSION = "p3_impression";
    public static final String P4_IMPRESSION = "p4_impression";
    public static final String REQUEST_BOOKING = "request_booking";
    public static final String SIGN_UP = "sign_up";
    public static final String TRAVEL_COUPON = "travel_coupon";
    private static boolean isEnabled;

    private MParticleAnalytics() {
    }

    public static void start(Context context) {
        boolean z = true;
        isEnabled = !isKillSwitched();
        if (isEnabled) {
            if (BuildHelper.isChina()) {
                z = false;
            }
            MParticle.setAndroidIdDisabled(z);
            if (BuildHelper.isDebugFeaturesEnabled()) {
                MParticle.start(context, context.getString(C0716R.string.mp_key_debug), context.getString(C0716R.string.mp_secret_debug));
            } else {
                MParticle.start(context);
            }
        }
    }

    public static void logEvent(String eventName, Strap info) {
        if (isEnabled) {
            info.mo11639kv(GoogleAdvertisingIdProvider.GOOGLE_ADVERTISING_ID_TAG, GoogleAdvertisingIdProvider.getGoogleAdvertisingId());
            MParticle.getInstance().logEvent(new Builder(eventName).info(info).build());
        }
    }

    private static boolean isKillSwitched() {
        return Trebuchet.launch(TrebuchetKeys.DISABLE_MPARTICLE_TREBUCHET, false);
    }
}
