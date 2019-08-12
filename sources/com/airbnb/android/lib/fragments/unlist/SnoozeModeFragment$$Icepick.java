package com.airbnb.android.lib.fragments.unlist;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.lib.fragments.unlist.SnoozeModeFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class SnoozeModeFragment$$Icepick<T extends SnoozeModeFragment> extends BaseSnoozeListingFragment$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9585H = new Helper("com.airbnb.android.lib.fragments.unlist.SnoozeModeFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.startDate = (AirDate) f9585H.getParcelable(state, "startDate");
            target.endDate = (AirDate) f9585H.getParcelable(state, "endDate");
            target.minDate = (AirDate) f9585H.getParcelable(state, "minDate");
            target.maxDate = (AirDate) f9585H.getParcelable(state, "maxDate");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9585H.putParcelable(state, "startDate", target.startDate);
        f9585H.putParcelable(state, "endDate", target.endDate);
        f9585H.putParcelable(state, "minDate", target.minDate);
        f9585H.putParcelable(state, "maxDate", target.maxDate);
    }
}
