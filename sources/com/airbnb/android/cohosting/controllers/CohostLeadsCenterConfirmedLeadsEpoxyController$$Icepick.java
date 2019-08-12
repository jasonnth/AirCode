package com.airbnb.android.cohosting.controllers;

import android.os.Bundle;
import com.airbnb.android.cohosting.controllers.CohostLeadsCenterConfirmedLeadsEpoxyController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostLeadsCenterConfirmedLeadsEpoxyController$$Icepick<T extends CohostLeadsCenterConfirmedLeadsEpoxyController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7793H = new Helper("com.airbnb.android.cohosting.controllers.CohostLeadsCenterConfirmedLeadsEpoxyController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.acceptedContracts = f7793H.getParcelableArrayList(state, "acceptedContracts");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7793H.putParcelableArrayList(state, "acceptedContracts", target.acceptedContracts);
    }
}
