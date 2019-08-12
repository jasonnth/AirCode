package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.core.enums.ReservationCancellationReason;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.android.lib.fragments.ReasonPickerFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ReasonPickerFragment$$Icepick<T extends ReasonPickerFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9546H = new Helper("com.airbnb.android.lib.fragments.ReasonPickerFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.isLoading = f9546H.getBoolean(state, "isLoading");
            target.hideCancellationFee = f9546H.getBoolean(state, "hideCancellationFee");
            target.isModal = f9546H.getBoolean(state, "isModal");
            target.reason = (ReservationCancellationReason) f9546H.getSerializable(state, CancellationAnalytics.VALUE_PAGE_REASON);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9546H.putBoolean(state, "isLoading", target.isLoading);
        f9546H.putBoolean(state, "hideCancellationFee", target.hideCancellationFee);
        f9546H.putBoolean(state, "isModal", target.isModal);
        f9546H.putSerializable(state, CancellationAnalytics.VALUE_PAGE_REASON, target.reason);
    }
}
