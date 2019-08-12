package com.airbnb.android.host_referrals.activities;

import android.os.Bundle;
import com.airbnb.android.core.activities.SheetFlowActivity$$Icepick;
import com.airbnb.android.core.models.HostReferralReferrerInfo;
import com.airbnb.android.host_referrals.activities.HostReferralsActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class HostReferralsActivity$$Icepick<T extends HostReferralsActivity> extends SheetFlowActivity$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8923H = new Helper("com.airbnb.android.host_referrals.activities.HostReferralsActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.referrerInfo = (HostReferralReferrerInfo) f8923H.getParcelable(state, "referrerInfo");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8923H.putParcelable(state, "referrerInfo", target.referrerInfo);
    }
}
