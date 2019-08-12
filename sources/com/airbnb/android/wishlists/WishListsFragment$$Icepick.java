package com.airbnb.android.wishlists;

import android.os.Bundle;
import com.airbnb.android.wishlists.WishListsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class WishListsFragment$$Icepick<T extends WishListsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f2681H = new Helper("com.airbnb.android.wishlists.WishListsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.lastSessionUuid = f2681H.getInt(state, "lastSessionUuid");
            target.selectedWishListId = f2681H.getLong(state, "selectedWishListId");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f2681H.putInt(state, "lastSessionUuid", target.lastSessionUuid);
        f2681H.putLong(state, "selectedWishListId", target.selectedWishListId);
    }
}
