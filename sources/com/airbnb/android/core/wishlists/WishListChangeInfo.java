package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.models.WishList;

public class WishListChangeInfo {
    private final boolean added;
    private final boolean doneByUser;
    private final WishListableData item;
    private final boolean newWishList;
    private final WishList wishList;

    static WishListChangeInfo forItemRemoved(WishList wishList2, WishListableData itemAdded, boolean doneByUser2) {
        return new WishListChangeInfo(wishList2, itemAdded, false, true, doneByUser2);
    }

    static WishListChangeInfo forItemAdded(WishList wishList2, WishListableData itemAdded, boolean doneByUser2) {
        return new WishListChangeInfo(wishList2, itemAdded, true, true, doneByUser2);
    }

    static WishListChangeInfo forNewWishList(WishList wishList2, WishListableData itemAdded) {
        return new WishListChangeInfo(wishList2, itemAdded, true, true, true);
    }

    private WishListChangeInfo(WishList list, WishListableData item2, boolean added2, boolean newWishList2, boolean doneByUser2) {
        this.item = item2;
        this.wishList = list;
        this.added = added2;
        this.newWishList = newWishList2;
        this.doneByUser = doneByUser2;
    }

    public boolean isAdded() {
        return this.added;
    }

    public long getId() {
        return this.item.itemId();
    }

    public WishListableType getType() {
        return this.item.type();
    }

    public WishList getWishList() {
        return this.wishList;
    }

    public WishListableData getItem() {
        return this.item;
    }

    public boolean wasDoneByUser() {
        return this.doneByUser;
    }
}
