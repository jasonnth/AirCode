package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.managelisting.settings.ManageListingSnoozeSettingAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ManageListingSnoozeSettingAdapter$$Icepick<T extends ManageListingSnoozeSettingAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10188H = new Helper("com.airbnb.android.managelisting.settings.ManageListingSnoozeSettingAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.startDate = (AirDate) f10188H.getParcelable(state, "startDate");
            target.endDate = (AirDate) f10188H.getParcelable(state, "endDate");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10188H.putParcelable(state, "startDate", target.startDate);
        f10188H.putParcelable(state, "endDate", target.endDate);
    }
}
