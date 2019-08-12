package com.airbnb.android.listyourspacedls.adapters;

import android.os.Bundle;
import com.airbnb.android.core.models.ListingPersonaInput.ListingPersonaAnswer;
import com.airbnb.android.listyourspacedls.adapters.LYSHostingFrequencyAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class LYSHostingFrequencyAdapter$$Icepick<T extends LYSHostingFrequencyAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9921H = new Helper("com.airbnb.android.listyourspacedls.adapters.LYSHostingFrequencyAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.hostingFrequency = (ListingPersonaAnswer) f9921H.getParcelable(state, "hostingFrequency");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9921H.putParcelable(state, "hostingFrequency", target.hostingFrequency);
    }
}
