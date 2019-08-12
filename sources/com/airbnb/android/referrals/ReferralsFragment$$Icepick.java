package com.airbnb.android.referrals;

import android.os.Bundle;
import com.airbnb.android.core.models.ReferralStatusForMobile;
import com.airbnb.android.referrals.ReferralsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ReferralsFragment$$Icepick<T extends ReferralsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f1558H = new Helper("com.airbnb.android.referrals.ReferralsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.referralStatus = (ReferralStatusForMobile) f1558H.getParcelable(state, "referralStatus");
            target.entryPoint = f1558H.getString(state, "entryPoint");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f1558H.putParcelable(state, "referralStatus", target.referralStatus);
        f1558H.putString(state, "entryPoint", target.entryPoint);
    }
}
