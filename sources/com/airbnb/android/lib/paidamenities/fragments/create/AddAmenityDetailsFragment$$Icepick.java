package com.airbnb.android.lib.paidamenities.fragments.create;

import android.os.Bundle;
import com.airbnb.android.core.models.PaidAmenityCategory;
import com.airbnb.android.lib.paidamenities.fragments.create.AddAmenityDetailsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AddAmenityDetailsFragment$$Icepick<T extends AddAmenityDetailsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9608H = new Helper("com.airbnb.android.lib.paidamenities.fragments.create.AddAmenityDetailsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.paidAmenityCategory = (PaidAmenityCategory) f9608H.getParcelable(state, "paidAmenityCategory");
            target.amenityDescription = f9608H.getString(state, "amenityDescription");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9608H.putParcelable(state, "paidAmenityCategory", target.paidAmenityCategory);
        f9608H.putString(state, "amenityDescription", target.amenityDescription);
    }
}
