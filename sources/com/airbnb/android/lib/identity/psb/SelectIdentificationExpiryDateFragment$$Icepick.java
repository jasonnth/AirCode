package com.airbnb.android.lib.identity.psb;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.lib.identity.psb.SelectIdentificationExpiryDateFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SelectIdentificationExpiryDateFragment$$Icepick<T extends SelectIdentificationExpiryDateFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9602H = new Helper("com.airbnb.android.lib.identity.psb.SelectIdentificationExpiryDateFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.expiryDate = (AirDate) f9602H.getParcelable(state, "expiryDate");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9602H.putParcelable(state, "expiryDate", target.expiryDate);
    }
}
