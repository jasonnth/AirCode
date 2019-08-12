package com.airbnb.android.wishlists;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class WishListDetailsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public WishListDetailsFragment_ObservableResubscriber(WishListDetailsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.privacyRequestListener, "WishListDetailsFragment_privacyRequestListener");
        group.resubscribeAll(target.privacyRequestListener);
    }
}
