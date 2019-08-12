package com.airbnb.android.cohosting.adapters;

import android.os.Bundle;
import com.airbnb.android.cohosting.adapters.CohostingShareEarningsAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostingShareEarningsAdapter$$Icepick<T extends CohostingShareEarningsAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7791H = new Helper("com.airbnb.android.cohosting.adapters.CohostingShareEarningsAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.percentage = f7791H.getInt(state, "percentage");
            target.includeCleaningFee = f7791H.getBoolean(state, "includeCleaningFee");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7791H.putInt(state, "percentage", target.percentage);
        f7791H.putBoolean(state, "includeCleaningFee", target.includeCleaningFee);
    }
}
