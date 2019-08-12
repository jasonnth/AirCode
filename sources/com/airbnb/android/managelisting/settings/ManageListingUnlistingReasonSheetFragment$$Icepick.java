package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.core.models.SupportPhoneNumber;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.android.managelisting.settings.ManageListingUnlistingReasonSheetFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ManageListingUnlistingReasonSheetFragment$$Icepick<T extends ManageListingUnlistingReasonSheetFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10191H = new Helper("com.airbnb.android.managelisting.settings.ManageListingUnlistingReasonSheetFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.cxNumber = (SupportPhoneNumber) f10191H.getParcelable(state, "cxNumber");
            target.reason = f10191H.getInt(state, CancellationAnalytics.VALUE_PAGE_REASON);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10191H.putParcelable(state, "cxNumber", target.cxNumber);
        f10191H.putInt(state, CancellationAnalytics.VALUE_PAGE_REASON, target.reason);
    }
}
