package com.airbnb.android.lib.postbooking;

import android.os.Bundle;
import com.airbnb.android.lib.postbooking.PostBookingBusinessTravelPromoFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PostBookingBusinessTravelPromoFragment$$Icepick<T extends PostBookingBusinessTravelPromoFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9652H = new Helper("com.airbnb.android.lib.postbooking.PostBookingBusinessTravelPromoFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.confirmationCode = f9652H.getString(state, "confirmationCode");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9652H.putString(state, "confirmationCode", target.confirmationCode);
    }
}
