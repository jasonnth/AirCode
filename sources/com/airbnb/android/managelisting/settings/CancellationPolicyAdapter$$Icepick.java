package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.managelisting.settings.CancellationPolicyAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CancellationPolicyAdapter$$Icepick<T extends CancellationPolicyAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10171H = new Helper("com.airbnb.android.managelisting.settings.CancellationPolicyAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.selectedCancellationPolicyName = f10171H.getString(state, "selectedCancellationPolicyName");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10171H.putString(state, "selectedCancellationPolicyName", target.selectedCancellationPolicyName);
    }
}
