package com.airbnb.android.core.adapters;

import android.os.Bundle;
import com.airbnb.android.core.adapters.HouseRulesEpoxyController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class HouseRulesEpoxyController$$Icepick<T extends HouseRulesEpoxyController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8423H = new Helper("com.airbnb.android.core.adapters.HouseRulesEpoxyController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listingExpectations = f8423H.getParcelableArrayList(state, "listingExpectations");
            target.translatedListingExpectations = f8423H.getParcelableArrayList(state, "translatedListingExpectations");
            target.translatedHouseRules = f8423H.getString(state, "translatedHouseRules");
            target.showingTranslation = f8423H.getBoolean(state, "showingTranslation");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8423H.putParcelableArrayList(state, "listingExpectations", target.listingExpectations);
        f8423H.putParcelableArrayList(state, "translatedListingExpectations", target.translatedListingExpectations);
        f8423H.putString(state, "translatedHouseRules", target.translatedHouseRules);
        f8423H.putBoolean(state, "showingTranslation", target.showingTranslation);
    }
}
