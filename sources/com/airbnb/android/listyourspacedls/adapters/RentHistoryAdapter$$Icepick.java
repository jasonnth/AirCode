package com.airbnb.android.listyourspacedls.adapters;

import android.os.Bundle;
import com.airbnb.android.core.models.ListingPersonaInput.ListingPersonaAnswer;
import com.airbnb.android.listyourspacedls.adapters.RentHistoryAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class RentHistoryAdapter$$Icepick<T extends RentHistoryAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9924H = new Helper("com.airbnb.android.listyourspacedls.adapters.RentHistoryAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.rentHistoryAnswer = (ListingPersonaAnswer) f9924H.getParcelable(state, "rentHistoryAnswer");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9924H.putParcelable(state, "rentHistoryAnswer", target.rentHistoryAnswer);
    }
}
