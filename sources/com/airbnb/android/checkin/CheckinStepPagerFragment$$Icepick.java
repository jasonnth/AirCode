package com.airbnb.android.checkin;

import android.os.Bundle;
import com.airbnb.android.checkin.CheckinStepPagerFragment;
import com.airbnb.android.core.models.CheckInGuide;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CheckinStepPagerFragment$$Icepick<T extends CheckinStepPagerFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7446H = new Helper("com.airbnb.android.checkin.CheckinStepPagerFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.currPosition = f7446H.getInt(state, "currPosition");
            target.guide = (CheckInGuide) f7446H.getParcelable(state, "guide");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7446H.putInt(state, "currPosition", target.currPosition);
        f7446H.putParcelable(state, "guide", target.guide);
    }
}
