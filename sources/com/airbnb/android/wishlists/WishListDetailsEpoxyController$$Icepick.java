package com.airbnb.android.wishlists;

import android.os.Bundle;
import com.airbnb.android.wishlists.WishListDetailsEpoxyController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class WishListDetailsEpoxyController$$Icepick<T extends WishListDetailsEpoxyController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f2677H = new Helper("com.airbnb.android.wishlists.WishListDetailsEpoxyController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.lastSelectedTabPosition = f2677H.getInt(state, "lastSelectedTabPosition");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f2677H.putInt(state, "lastSelectedTabPosition", target.lastSelectedTabPosition);
    }
}
