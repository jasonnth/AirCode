package com.airbnb.android.wishlists;

import android.os.Bundle;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.wishlists.WishListDetailsParentFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class WishListDetailsParentFragment$$Icepick<T extends WishListDetailsParentFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f2679H = new Helper("com.airbnb.android.wishlists.WishListDetailsParentFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.wishListMembers = f2679H.getParcelableArrayList(state, "wishListMembers");
            target.wishList = (WishList) f2679H.getParcelable(state, "wishList");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f2679H.putParcelableArrayList(state, "wishListMembers", target.wishListMembers);
        f2679H.putParcelable(state, "wishList", target.wishList);
    }
}
