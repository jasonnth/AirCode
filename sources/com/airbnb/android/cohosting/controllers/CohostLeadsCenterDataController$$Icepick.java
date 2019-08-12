package com.airbnb.android.cohosting.controllers;

import android.os.Bundle;
import com.airbnb.android.cohosting.controllers.CohostLeadsCenterDataController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostLeadsCenterDataController$$Icepick<T extends CohostLeadsCenterDataController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7794H = new Helper("com.airbnb.android.cohosting.controllers.CohostLeadsCenterDataController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.loading = f7794H.getBoolean(state, "loading");
            target.contracts = f7794H.getParcelableArrayList(state, "contracts");
            target.inquiries = f7794H.getParcelableArrayList(state, "inquiries");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7794H.putBoolean(state, "loading", target.loading);
        f7794H.putParcelableArrayList(state, "contracts", target.contracts);
        f7794H.putParcelableArrayList(state, "inquiries", target.inquiries);
    }
}
