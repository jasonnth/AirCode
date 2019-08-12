package com.airbnb.android.pickwishlist;

import com.airbnb.android.core.explore.ChildScope;

@ChildScope
public interface PickWishListComponent {

    public interface Builder {
        PickWishListComponent build();
    }

    void inject(CreateWishListActivity createWishListActivity);

    void inject(PickWishListFragment pickWishListFragment);
}
