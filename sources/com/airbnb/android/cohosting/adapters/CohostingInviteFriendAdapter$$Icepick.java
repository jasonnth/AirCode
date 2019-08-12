package com.airbnb.android.cohosting.adapters;

import android.os.Bundle;
import com.airbnb.android.cohosting.adapters.CohostingInviteFriendAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostingInviteFriendAdapter$$Icepick<T extends CohostingInviteFriendAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7790H = new Helper("com.airbnb.android.cohosting.adapters.CohostingInviteFriendAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.email = f7790H.getString(state, "email");
            target.percentage = f7790H.getInt(state, "percentage");
            target.includeCleaningFee = f7790H.getBoolean(state, "includeCleaningFee");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7790H.putString(state, "email", target.email);
        f7790H.putInt(state, "percentage", target.percentage);
        f7790H.putBoolean(state, "includeCleaningFee", target.includeCleaningFee);
    }
}
