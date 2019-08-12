package com.airbnb.android.lib.payments.quickpay.fragments;

import android.os.Bundle;
import com.airbnb.android.lib.payments.quickpay.fragments.GiftCardQuickPayFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class GiftCardQuickPayFragment$$Icepick<T extends GiftCardQuickPayFragment> extends QuickPayFragment$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9646H = new Helper("com.airbnb.android.lib.payments.quickpay.fragments.GiftCardQuickPayFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.cvvNonce = f9646H.getString(state, "cvvNonce");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9646H.putString(state, "cvvNonce", target.cvvNonce);
    }
}
