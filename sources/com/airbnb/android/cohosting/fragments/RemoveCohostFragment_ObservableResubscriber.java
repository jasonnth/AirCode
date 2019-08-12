package com.airbnb.android.cohosting.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class RemoveCohostFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public RemoveCohostFragment_ObservableResubscriber(RemoveCohostFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.removeCohostListener, "RemoveCohostFragment_removeCohostListener");
        group.resubscribeAll(target.removeCohostListener);
    }
}
