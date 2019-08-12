package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.listing.adapters.LongTermDiscountsAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class LongTermDiscountsAdapter$$Icepick<T extends LongTermDiscountsAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9796H = new Helper("com.airbnb.android.listing.adapters.LongTermDiscountsAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.weeklyDiscountValue = f9796H.getBoxedInt(state, "weeklyDiscountValue");
            target.monthlyDiscountValue = f9796H.getBoxedInt(state, "monthlyDiscountValue");
            target.weeklyDiscountShowError = f9796H.getBoolean(state, "weeklyDiscountShowError");
            target.monthlyDiscountShowError = f9796H.getBoolean(state, "monthlyDiscountShowError");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9796H.putBoxedInt(state, "weeklyDiscountValue", target.weeklyDiscountValue);
        f9796H.putBoxedInt(state, "monthlyDiscountValue", target.monthlyDiscountValue);
        f9796H.putBoolean(state, "weeklyDiscountShowError", target.weeklyDiscountShowError);
        f9796H.putBoolean(state, "monthlyDiscountShowError", target.monthlyDiscountShowError);
    }
}
