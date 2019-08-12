package com.airbnb.android.referrals;

import android.os.Bundle;
import com.airbnb.android.core.models.ReferralStatusForMobile;
import com.airbnb.android.referrals.ReferralsActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ReferralsActivity$$Icepick<T extends ReferralsActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f1557H = new Helper("com.airbnb.android.referrals.ReferralsActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.referralStatus = (ReferralStatusForMobile) f1557H.getParcelable(state, "referralStatus");
            target.grayUsers = f1557H.getParcelableArrayList(state, "grayUsers");
            target.pendingReferrees = f1557H.getParcelableArrayList(state, "pendingReferrees");
            target.earnedReferrees = f1557H.getParcelableArrayList(state, "earnedReferrees");
            target.entryPoint = f1557H.getString(state, "entryPoint");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f1557H.putParcelable(state, "referralStatus", target.referralStatus);
        f1557H.putParcelableArrayList(state, "grayUsers", target.grayUsers);
        f1557H.putParcelableArrayList(state, "pendingReferrees", target.pendingReferrees);
        f1557H.putParcelableArrayList(state, "earnedReferrees", target.earnedReferrees);
        f1557H.putString(state, "entryPoint", target.entryPoint);
    }
}
