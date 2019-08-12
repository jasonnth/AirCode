package com.airbnb.android.lib.businesstravel;

import android.os.Bundle;
import com.airbnb.android.lib.businesstravel.BusinessTravelAutoEnrollFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class BusinessTravelAutoEnrollFragment$$Icepick<T extends BusinessTravelAutoEnrollFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9494H = new Helper("com.airbnb.android.lib.businesstravel.BusinessTravelAutoEnrollFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.emailDomain = f9494H.getString(state, "emailDomain");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9494H.putString(state, "emailDomain", target.emailDomain);
    }
}
