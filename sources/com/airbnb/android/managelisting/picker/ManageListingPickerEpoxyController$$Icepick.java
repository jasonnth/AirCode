package com.airbnb.android.managelisting.picker;

import android.os.Bundle;
import com.airbnb.android.managelisting.picker.ManageListingPickerEpoxyController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ManageListingPickerEpoxyController$$Icepick<T extends ManageListingPickerEpoxyController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10170H = new Helper("com.airbnb.android.managelisting.picker.ManageListingPickerEpoxyController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.sortedListings = f10170H.getParcelableArrayList(state, "sortedListings");
            target.finishedLoading = f10170H.getBoolean(state, "finishedLoading");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10170H.putParcelableArrayList(state, "sortedListings", target.sortedListings);
        f10170H.putBoolean(state, "finishedLoading", target.finishedLoading);
    }
}
