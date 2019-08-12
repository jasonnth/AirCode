package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.CompleteGiftCreditRedeemFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CompleteGiftCreditRedeemFragment$$Icepick<T extends CompleteGiftCreditRedeemFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9525H = new Helper("com.airbnb.android.lib.fragments.CompleteGiftCreditRedeemFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.formattedGiftCreditBalance = f9525H.getString(state, "formattedGiftCreditBalance");
            target.redeemedGiftCreditAmount = f9525H.getInt(state, "redeemedGiftCreditAmount");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9525H.putString(state, "formattedGiftCreditBalance", target.formattedGiftCreditBalance);
        f9525H.putInt(state, "redeemedGiftCreditAmount", target.redeemedGiftCreditAmount);
    }
}
