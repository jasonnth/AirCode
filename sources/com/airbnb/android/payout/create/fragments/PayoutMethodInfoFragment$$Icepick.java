package com.airbnb.android.payout.create.fragments;

import android.os.Bundle;
import com.airbnb.android.payout.create.fragments.PayoutMethodInfoFragment;
import com.airbnb.android.payout.models.PayoutInfoForm;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PayoutMethodInfoFragment$$Icepick<T extends PayoutMethodInfoFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10771H = new Helper("com.airbnb.android.payout.create.fragments.PayoutMethodInfoFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.selectedPayoutInfoForm = (PayoutInfoForm) f10771H.getParcelable(state, "selectedPayoutInfoForm");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10771H.putParcelable(state, "selectedPayoutInfoForm", target.selectedPayoutInfoForm);
    }
}
