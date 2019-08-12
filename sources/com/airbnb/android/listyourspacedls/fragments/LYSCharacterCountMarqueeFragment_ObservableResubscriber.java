package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSCharacterCountMarqueeFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSCharacterCountMarqueeFragment_ObservableResubscriber(LYSCharacterCountMarqueeFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "LYSCharacterCountMarqueeFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
