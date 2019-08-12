package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.listing.adapters.LengthOfStayDiscountsEpoxyController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class LengthOfStayDiscountsEpoxyController$$Icepick<T extends LengthOfStayDiscountsEpoxyController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9790H = new Helper("com.airbnb.android.listing.adapters.LengthOfStayDiscountsEpoxyController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.modelStates = (HashMap) f9790H.getSerializable(state, "modelStates");
            target.inputEnabled = f9790H.getBoolean(state, "inputEnabled");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9790H.putSerializable(state, "modelStates", target.modelStates);
        f9790H.putBoolean(state, "inputEnabled", target.inputEnabled);
    }
}
