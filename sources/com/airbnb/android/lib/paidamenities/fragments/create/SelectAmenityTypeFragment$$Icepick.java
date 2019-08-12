package com.airbnb.android.lib.paidamenities.fragments.create;

import android.os.Bundle;
import com.airbnb.android.lib.paidamenities.fragments.create.SelectAmenityTypeFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SelectAmenityTypeFragment$$Icepick<T extends SelectAmenityTypeFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9611H = new Helper("com.airbnb.android.lib.paidamenities.fragments.create.SelectAmenityTypeFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.paidAmenityCategories = f9611H.getParcelableArrayList(state, "paidAmenityCategories");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9611H.putParcelableArrayList(state, "paidAmenityCategories", target.paidAmenityCategories);
    }
}
