package com.airbnb.android.wishlists;

import com.airbnb.android.core.BaseGraph;

public interface WishListDetailsGraph extends BaseGraph {
    void inject(BaseWishListDetailsFragment baseWishListDetailsFragment);

    void inject(WishListDetailsParentFragment wishListDetailsParentFragment);

    void inject(WishListIndexFragment wishListIndexFragment);

    void inject(WishListsFragment wishListsFragment);
}
