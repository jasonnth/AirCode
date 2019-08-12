package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import com.airbnb.android.booking.fragments.BookingSummaryFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class BookingSummaryFragment$$Icepick<T extends BookingSummaryFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7431H = new Helper("com.airbnb.android.booking.fragments.BookingSummaryFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.scrollPosition = f7431H.getInt(state, "scrollPosition");
            target.isPaymentRequestQueued = f7431H.getBoolean(state, "isPaymentRequestQueued");
            target.isInstantBookableIfGovIdProvided = f7431H.getBoolean(state, "isInstantBookableIfGovIdProvided");
            target.isGovIdSnackbarVisible = f7431H.getBoolean(state, "isGovIdSnackbarVisible");
            target.loggedDeniedGovId = f7431H.getBoolean(state, "loggedDeniedGovId");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7431H.putInt(state, "scrollPosition", target.scrollPosition);
        f7431H.putBoolean(state, "isPaymentRequestQueued", target.isPaymentRequestQueued);
        f7431H.putBoolean(state, "isInstantBookableIfGovIdProvided", target.isInstantBookableIfGovIdProvided);
        f7431H.putBoolean(state, "isGovIdSnackbarVisible", target.isGovIdSnackbarVisible);
        f7431H.putBoolean(state, "loggedDeniedGovId", target.loggedDeniedGovId);
    }
}
