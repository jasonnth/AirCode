package com.airbnb.android.listing.utils;

import android.os.Bundle;
import com.airbnb.android.core.models.CheckInTimeOption;
import com.airbnb.android.core.models.ListingCheckInTimeOptions;
import com.airbnb.android.listing.utils.CheckInOutSettingsHelper;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CheckInOutSettingsHelper$$Icepick<T extends CheckInOutSettingsHelper> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9808H = new Helper("com.airbnb.android.listing.utils.CheckInOutSettingsHelper$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.checkInTimeOptions = (ListingCheckInTimeOptions) f9808H.getParcelable(state, "checkInTimeOptions");
            target.checkInStartTime = (CheckInTimeOption) f9808H.getParcelable(state, "checkInStartTime");
            target.checkInEndTime = (CheckInTimeOption) f9808H.getParcelable(state, "checkInEndTime");
            target.checkOutTime = (CheckInTimeOption) f9808H.getParcelable(state, "checkOutTime");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9808H.putParcelable(state, "checkInTimeOptions", target.checkInTimeOptions);
        f9808H.putParcelable(state, "checkInStartTime", target.checkInStartTime);
        f9808H.putParcelable(state, "checkInEndTime", target.checkInEndTime);
        f9808H.putParcelable(state, "checkOutTime", target.checkOutTime);
    }
}
