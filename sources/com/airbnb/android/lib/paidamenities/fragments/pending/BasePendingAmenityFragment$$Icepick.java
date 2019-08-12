package com.airbnb.android.lib.paidamenities.fragments.pending;

import android.os.Bundle;
import com.airbnb.android.lib.paidamenities.fragments.pending.BasePendingAmenityFragment;
import com.airbnb.android.lib.paidamenities.fragments.pending.BasePendingAmenityFragment.UserMode;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class BasePendingAmenityFragment$$Icepick<T extends BasePendingAmenityFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9615H = new Helper("com.airbnb.android.lib.paidamenities.fragments.pending.BasePendingAmenityFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.userMode = (UserMode) f9615H.getSerializable(state, "userMode");
            target.confirmationCode = f9615H.getString(state, "confirmationCode");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9615H.putSerializable(state, "userMode", target.userMode);
        f9615H.putString(state, "confirmationCode", target.confirmationCode);
    }
}
