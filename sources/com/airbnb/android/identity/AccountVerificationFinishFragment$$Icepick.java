package com.airbnb.android.identity;

import android.os.Bundle;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.models.TripRole;
import com.airbnb.android.core.models.User;
import com.airbnb.android.identity.AccountVerificationFinishFragment;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AccountVerificationFinishFragment$$Icepick<T extends AccountVerificationFinishFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9164H = new Helper("com.airbnb.android.identity.AccountVerificationFinishFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.host = (User) f9164H.getParcelable(state, TripRole.ROLE_KEY_HOST);
            target.flow = (VerificationFlow) f9164H.getSerializable(state, ManageListingAnalytics.FLOW);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9164H.putParcelable(state, TripRole.ROLE_KEY_HOST, target.host);
        f9164H.putSerializable(state, ManageListingAnalytics.FLOW, target.flow);
    }
}
