package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.models.WishList;
import java.util.List;

public interface WishListsChangedListener {
    void onWishListsChanged(List<WishList> list, WishListChangeInfo wishListChangeInfo);
}
