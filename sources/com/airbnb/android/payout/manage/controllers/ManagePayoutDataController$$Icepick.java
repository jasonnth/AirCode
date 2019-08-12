package com.airbnb.android.payout.manage.controllers;

import android.os.Bundle;
import com.airbnb.android.payout.manage.controllers.ManagePayoutDataController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ManagePayoutDataController$$Icepick<T extends ManagePayoutDataController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10774H = new Helper("com.airbnb.android.payout.manage.controllers.ManagePayoutDataController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.payoutInstruments = f10774H.getParcelableArrayList(state, "payoutInstruments");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10774H.putParcelableArrayList(state, "payoutInstruments", target.payoutInstruments);
    }
}
