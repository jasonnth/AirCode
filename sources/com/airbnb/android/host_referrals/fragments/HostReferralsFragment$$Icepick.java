package com.airbnb.android.host_referrals.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.HostReferralReferrerInfo;
import com.airbnb.android.host_referrals.fragments.HostReferralsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class HostReferralsFragment$$Icepick<T extends HostReferralsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8924H = new Helper("com.airbnb.android.host_referrals.fragments.HostReferralsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.referrerInfo = (HostReferralReferrerInfo) f8924H.getParcelable(state, "referrerInfo");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8924H.putParcelable(state, "referrerInfo", target.referrerInfo);
    }
}
