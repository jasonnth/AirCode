package com.airbnb.android.lib.fragments.verifications;

import android.view.View;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.lib.activities.VerificationsActivity;
import com.airbnb.android.utils.Strap;

public class VerificationsFragment extends AirFragment {
    /* access modifiers changed from: protected */
    public void showOrHideSkipButton(View skipButton) {
        skipButton.setVisibility(getVerificationsActivity().isSkipEnabled() ? 0 : 8);
    }

    public VerificationsActivity getVerificationsActivity() {
        return (VerificationsActivity) getActivity();
    }

    public static void track(String page, String section, String operation) {
        AirbnbEventLogger.track("activate_account", Strap.make().mo11639kv("page", page).mo11639kv(BaseAnalytics.SECTION, section).mo11639kv(BaseAnalytics.OPERATION, operation));
    }

    public static void track(String page, String section, String operation, String target) {
        AirbnbEventLogger.track("activate_account", Strap.make().mo11639kv("page", page).mo11639kv(BaseAnalytics.SECTION, section).mo11639kv(BaseAnalytics.OPERATION, operation).mo11639kv(BaseAnalytics.TARGET, target));
    }
}
