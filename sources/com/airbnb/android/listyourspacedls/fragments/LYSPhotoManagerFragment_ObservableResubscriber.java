package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSPhotoManagerFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSPhotoManagerFragment_ObservableResubscriber(LYSPhotoManagerFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updatePhotoOrderListener, "LYSPhotoManagerFragment_updatePhotoOrderListener");
        group.resubscribeAll(target.updatePhotoOrderListener);
    }
}
