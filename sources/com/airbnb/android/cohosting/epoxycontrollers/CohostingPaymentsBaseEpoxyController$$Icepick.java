package com.airbnb.android.cohosting.epoxycontrollers;

import android.os.Bundle;
import com.airbnb.android.cohosting.epoxycontrollers.CohostingPaymentsBaseEpoxyController;
import com.airbnb.android.cohosting.shared.CohostingPaymentSettings;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostingPaymentsBaseEpoxyController$$Icepick<T extends CohostingPaymentsBaseEpoxyController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7799H = new Helper("com.airbnb.android.cohosting.epoxycontrollers.CohostingPaymentsBaseEpoxyController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.cohostingPaymentSettings = (CohostingPaymentSettings) f7799H.getParcelable(state, "cohostingPaymentSettings");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7799H.putParcelable(state, "cohostingPaymentSettings", target.cohostingPaymentSettings);
    }
}
