package com.airbnb.android.core.models.find;

import android.os.Bundle;
import com.airbnb.android.core.models.find.PlaceFilters;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PlaceFilters$$Icepick<T extends PlaceFilters> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8477H = new Helper("com.airbnb.android.core.models.find.PlaceFilters$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.guidebookItemTypes = f8477H.getParcelableArrayList(state, "guidebookItemTypes");
            target.categories = f8477H.getParcelableArrayList(state, "categories");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8477H.putParcelableArrayList(state, "guidebookItemTypes", target.guidebookItemTypes);
        f8477H.putParcelableArrayList(state, "categories", target.categories);
    }
}
