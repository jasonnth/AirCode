package com.airbnb.android.wishlists;

import android.os.Bundle;
import com.airbnb.android.core.models.User;
import com.airbnb.android.wishlists.WishListMembersFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class WishListMembersFragment$$Icepick<T extends WishListMembersFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f2680H = new Helper("com.airbnb.android.wishlists.WishListMembersFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.userToRemove = (User) f2680H.getParcelable(state, "userToRemove");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f2680H.putParcelable(state, "userToRemove", target.userToRemove);
    }
}
