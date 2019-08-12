package com.airbnb.android.lib.postbooking;

import android.os.Bundle;
import com.airbnb.android.lib.postbooking.PostBookingReferralFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PostBookingReferralFragment$$Icepick<T extends PostBookingReferralFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9654H = new Helper("com.airbnb.android.lib.postbooking.PostBookingReferralFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.referralBonusGuest = f9654H.getString(state, "referralBonusGuest");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9654H.putString(state, "referralBonusGuest", target.referralBonusGuest);
    }
}
