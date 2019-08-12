package com.airbnb.android.host_referrals.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.HostReferralReferrerInfo;
import com.airbnb.android.host_referrals.fragments.SentHostReferralsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SentHostReferralsFragment$$Icepick<T extends SentHostReferralsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8925H = new Helper("com.airbnb.android.host_referrals.fragments.SentHostReferralsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.referrees = f8925H.getParcelableArrayList(state, "referrees");
            target.referrerInfo = (HostReferralReferrerInfo) f8925H.getParcelable(state, "referrerInfo");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8925H.putParcelableArrayList(state, "referrees", target.referrees);
        f8925H.putParcelable(state, "referrerInfo", target.referrerInfo);
    }
}
