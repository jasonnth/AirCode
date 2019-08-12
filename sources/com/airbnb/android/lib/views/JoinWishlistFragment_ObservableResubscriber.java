package com.airbnb.android.lib.views;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class JoinWishlistFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public JoinWishlistFragment_ObservableResubscriber(JoinWishlistFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listener, "JoinWishlistFragment_listener");
        group.resubscribeAll(target.listener);
    }
}
