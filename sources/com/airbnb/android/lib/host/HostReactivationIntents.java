package com.airbnb.android.lib.host;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.host.HostReactivationFragment;
import com.airbnb.android.lib.fragments.LegacyHostReactivationFragment;

public final class HostReactivationIntents {
    private HostReactivationIntents() {
    }

    public static Intent intentForHostReactivation(Context context) {
        if (FeatureToggles.showHostSuspensionDlsBanner()) {
            return HostReactivationFragment.createIntent(context);
        }
        return LegacyHostReactivationFragment.createIntent(context);
    }
}
