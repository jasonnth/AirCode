package com.airbnb.android.pickwishlist;

import android.os.Bundle;
import com.airbnb.android.pickwishlist.CreateWishListActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CreateWishListActivity$$Icepick<T extends CreateWishListActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10943H = new Helper("com.airbnb.android.pickwishlist.CreateWishListActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.privateWishList = f10943H.getBoolean(state, "privateWishList");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10943H.putBoolean(state, "privateWishList", target.privateWishList);
    }
}
