package com.airbnb.android.checkin;

import android.os.Bundle;
import com.airbnb.android.checkin.ViewCheckinActivity;
import com.airbnb.android.core.models.CheckInGuide;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ViewCheckinActivity$$Icepick<T extends ViewCheckinActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7557H = new Helper("com.airbnb.android.checkin.ViewCheckinActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.checkinGuide = (CheckInGuide) f7557H.getParcelable(state, "checkinGuide");
            target.showLocalizedGuide = f7557H.getBoolean(state, "showLocalizedGuide");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7557H.putParcelable(state, "checkinGuide", target.checkinGuide);
        f7557H.putBoolean(state, "showLocalizedGuide", target.showLocalizedGuide);
    }
}
