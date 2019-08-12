package com.airbnb.android.referrals;

import android.os.Bundle;
import com.airbnb.android.core.models.ReferralStatusForMobile;
import com.airbnb.android.referrals.SentReferralsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SentReferralsFragment$$Icepick<T extends SentReferralsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f1560H = new Helper("com.airbnb.android.referrals.SentReferralsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.referralStatus = (ReferralStatusForMobile) f1560H.getParcelable(state, "referralStatus");
            target.pendingReferrees = f1560H.getParcelableArrayList(state, "pendingReferrees");
            target.earnedReferrees = f1560H.getParcelableArrayList(state, "earnedReferrees");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f1560H.putParcelable(state, "referralStatus", target.referralStatus);
        f1560H.putParcelableArrayList(state, "pendingReferrees", target.pendingReferrees);
        f1560H.putParcelableArrayList(state, "earnedReferrees", target.earnedReferrees);
    }
}
