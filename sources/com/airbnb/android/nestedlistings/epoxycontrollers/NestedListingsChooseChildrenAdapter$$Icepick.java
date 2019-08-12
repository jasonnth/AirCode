package com.airbnb.android.nestedlistings.epoxycontrollers;

import android.os.Bundle;
import com.airbnb.android.nestedlistings.epoxycontrollers.NestedListingsChooseChildrenAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class NestedListingsChooseChildrenAdapter$$Icepick<T extends NestedListingsChooseChildrenAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10527H = new Helper("com.airbnb.android.nestedlistings.epoxycontrollers.NestedListingsChooseChildrenAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.selectedListingIds = (HashSet) f10527H.getSerializable(state, "selectedListingIds");
            target.initialSelectedListingIds = (HashSet) f10527H.getSerializable(state, "initialSelectedListingIds");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10527H.putSerializable(state, "selectedListingIds", target.selectedListingIds);
        f10527H.putSerializable(state, "initialSelectedListingIds", target.initialSelectedListingIds);
    }
}
