package com.airbnb.android.explore.fragments;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.explore.fragments.MTDatesFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class MTDatesFragment$$Icepick<T extends MTDatesFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8581H = new Helper("com.airbnb.android.explore.fragments.MTDatesFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.startDate = (AirDate) f8581H.getParcelable(state, "startDate");
            target.endDate = (AirDate) f8581H.getParcelable(state, "endDate");
            target.isChinaStyle = f8581H.getBoolean(state, "isChinaStyle");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8581H.putParcelable(state, "startDate", target.startDate);
        f8581H.putParcelable(state, "endDate", target.endDate);
        f8581H.putBoolean(state, "isChinaStyle", target.isChinaStyle);
    }
}
