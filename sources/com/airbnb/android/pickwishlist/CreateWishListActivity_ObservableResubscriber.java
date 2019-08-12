package com.airbnb.android.pickwishlist;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CreateWishListActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public CreateWishListActivity_ObservableResubscriber(CreateWishListActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.createWishListRequestListener, "CreateWishListActivity_createWishListRequestListener");
        group.resubscribeAll(target.createWishListRequestListener);
    }
}
