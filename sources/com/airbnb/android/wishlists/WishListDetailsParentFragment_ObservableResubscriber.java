package com.airbnb.android.wishlists;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class WishListDetailsParentFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public WishListDetailsParentFragment_ObservableResubscriber(WishListDetailsParentFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.wishListRequestListener, "WishListDetailsParentFragment_wishListRequestListener");
        group.resubscribeAll(target.wishListRequestListener);
        setTag((AutoTaggableObserver) target.membersRequestListener, "WishListDetailsParentFragment_membersRequestListener");
        group.resubscribeAll(target.membersRequestListener);
        setTag((AutoTaggableObserver) target.removeMemberRequestListener, "WishListDetailsParentFragment_removeMemberRequestListener");
        group.resubscribeAll(target.removeMemberRequestListener);
    }
}
