package com.airbnb.android.listyourspacedls.adapters;

import android.os.Bundle;
import com.airbnb.android.core.enums.PropertyType;
import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.listyourspacedls.adapters.SpaceTypeAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SpaceTypeAdapter$$Icepick<T extends SpaceTypeAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9925H = new Helper("com.airbnb.android.listyourspacedls.adapters.SpaceTypeAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.spaceType = (SpaceType) f9925H.getSerializable(state, "spaceType");
            target.propertyType = (PropertyType) f9925H.getParcelable(state, "propertyType");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9925H.putSerializable(state, "spaceType", target.spaceType);
        f9925H.putParcelable(state, "propertyType", target.propertyType);
    }
}
