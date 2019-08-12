package com.airbnb.android.lib.activities;

import android.os.Bundle;
import com.airbnb.android.core.activities.WebViewActivity$$Icepick;
import com.airbnb.android.lib.activities.PayForPendingReservationActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class PayForPendingReservationActivity$$Icepick<T extends PayForPendingReservationActivity> extends WebViewActivity$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9469H = new Helper("com.airbnb.android.lib.activities.PayForPendingReservationActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.confirmationCode = f9469H.getString(state, "confirmationCode");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9469H.putString(state, "confirmationCode", target.confirmationCode);
    }
}
