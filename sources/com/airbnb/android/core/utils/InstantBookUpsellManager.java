package com.airbnb.android.core.utils;

import android.content.Context;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.erf.FeatureToggles;

public class InstantBookUpsellManager {
    public static final int ACCEPTANCE_RATE_WINDOW = 3;
    private static final String PREFS_HOST_ACCEPTANCE_COUNT = "prefs_host_acceptance_count_";
    private static final String PREFS_HOST_ACCEPTANCE_IB_UPSELL = "prefs_host_acceptance_ib_upsell_";
    SharedPrefsHelper sharedPrefsHelper;

    public InstantBookUpsellManager(Context context) {
        ((CoreGraph) CoreApplication.instance(context).component()).inject(this);
    }

    public boolean shouldShowUpsell(long listingId) {
        if (!this.sharedPrefsHelper.hasInstantBookUpsellFlag(getUpsellFlagPrefKey(listingId)) && this.sharedPrefsHelper.getAcceptedRequestCount(getAcceptanceCounterPrefKey(listingId)) == 3 && FeatureToggles.isUpsellIBAfterAcceptanceEnabled()) {
            return true;
        }
        return false;
    }

    public void onRequestAccepted(long listingId) {
        int acceptedRequestCount = this.sharedPrefsHelper.getAcceptedRequestCount(getAcceptanceCounterPrefKey(listingId)) + 1;
        if (acceptedRequestCount > 3) {
            acceptedRequestCount %= 3;
        }
        this.sharedPrefsHelper.setAcceptedRequestCount(getAcceptanceCounterPrefKey(listingId), acceptedRequestCount);
    }

    public void onRequestDecliend(long listingId) {
        resetConsecutiveAcceptanceCounter(listingId);
    }

    public void onUpsellAfterAcceptance(long listingId) {
        resetConsecutiveAcceptanceCounter(listingId);
    }

    public void onTappedOnUpsell(long listingId) {
        this.sharedPrefsHelper.setInstantBookUpsellFlag(getUpsellFlagPrefKey(listingId));
    }

    private void resetConsecutiveAcceptanceCounter(long listingId) {
        this.sharedPrefsHelper.setAcceptedRequestCount(getAcceptanceCounterPrefKey(listingId), 0);
    }

    private String getAcceptanceCounterPrefKey(long id) {
        return PREFS_HOST_ACCEPTANCE_COUNT + id;
    }

    private String getUpsellFlagPrefKey(long id) {
        return PREFS_HOST_ACCEPTANCE_IB_UPSELL + id;
    }
}
