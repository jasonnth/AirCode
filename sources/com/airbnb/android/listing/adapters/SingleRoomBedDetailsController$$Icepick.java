package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.listing.adapters.SingleRoomBedDetailsController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SingleRoomBedDetailsController$$Icepick<T extends SingleRoomBedDetailsController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9803H = new Helper("com.airbnb.android.listing.adapters.SingleRoomBedDetailsController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.newCounts = (HashMap) f9803H.getSerializable(state, "newCounts");
            target.showExpanded = f9803H.getBoolean(state, "showExpanded");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9803H.putSerializable(state, "newCounts", target.newCounts);
        f9803H.putBoolean(state, "showExpanded", target.showExpanded);
    }
}
