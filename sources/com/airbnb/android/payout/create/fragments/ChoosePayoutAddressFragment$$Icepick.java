package com.airbnb.android.payout.create.fragments;

import android.os.Bundle;
import com.airbnb.android.payout.create.fragments.ChoosePayoutAddressFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ChoosePayoutAddressFragment$$Icepick<T extends ChoosePayoutAddressFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10770H = new Helper("com.airbnb.android.payout.create.fragments.ChoosePayoutAddressFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.addressSelectionMap = (HashMap) f10770H.getSerializable(state, "addressSelectionMap");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10770H.putSerializable(state, "addressSelectionMap", target.addressSelectionMap);
    }
}
