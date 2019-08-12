package com.airbnb.android.lib.paidamenities.fragments.create;

import android.os.Bundle;
import com.airbnb.android.lib.paidamenities.fragments.create.SelectListingFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SelectListingFragment$$Icepick<T extends SelectListingFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9612H = new Helper("com.airbnb.android.lib.paidamenities.fragments.create.SelectListingFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listings = f9612H.getParcelableArrayList(state, "listings");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9612H.putParcelableArrayList(state, "listings", target.listings);
    }
}
