package com.airbnb.android.payout.manage;

import android.os.Bundle;
import com.airbnb.android.payout.manage.EditPayoutFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class EditPayoutFragment$$Icepick<T extends EditPayoutFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10772H = new Helper("com.airbnb.android.payout.manage.EditPayoutFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.payoutInstruments = f10772H.getParcelableArrayList(state, "payoutInstruments");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10772H.putParcelableArrayList(state, "payoutInstruments", target.payoutInstruments);
    }
}
