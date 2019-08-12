package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSPhotoDetailFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSPhotoDetailFragment_ObservableResubscriber(LYSPhotoDetailFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingPhotoListener, "LYSPhotoDetailFragment_updateListingPhotoListener");
        group.resubscribeAll(target.updateListingPhotoListener);
        setTag((AutoTaggableObserver) target.deleteListingPhotoListener, "LYSPhotoDetailFragment_deleteListingPhotoListener");
        group.resubscribeAll(target.deleteListingPhotoListener);
    }
}
