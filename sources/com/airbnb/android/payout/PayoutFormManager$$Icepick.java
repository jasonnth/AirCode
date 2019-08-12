package com.airbnb.android.payout;

import android.os.Bundle;
import com.airbnb.android.payout.PayoutFormManager;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PayoutFormManager$$Icepick<T extends PayoutFormManager> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10658H = new Helper("com.airbnb.android.payout.PayoutFormManager$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.formInputs = f10658H.getParcelableArrayList(state, "formInputs");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10658H.putParcelableArrayList(state, "formInputs", target.formInputs);
    }
}
