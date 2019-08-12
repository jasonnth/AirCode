package com.airbnb.android.referrals;

import android.os.Bundle;
import com.airbnb.android.core.models.ReferralStatusForMobile;
import com.airbnb.android.referrals.ReferralsOneClickFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ReferralsOneClickFragment$$Icepick<T extends ReferralsOneClickFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f1559H = new Helper("com.airbnb.android.referrals.ReferralsOneClickFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.referralStatus = (ReferralStatusForMobile) f1559H.getParcelable(state, "referralStatus");
            target.grayUsers = f1559H.getParcelableArrayList(state, "grayUsers");
            target.pendingInvites = f1559H.getParcelableArrayList(state, "pendingInvites");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f1559H.putParcelable(state, "referralStatus", target.referralStatus);
        f1559H.putParcelableArrayList(state, "grayUsers", target.grayUsers);
        f1559H.putParcelableArrayList(state, "pendingInvites", target.pendingInvites);
    }
}