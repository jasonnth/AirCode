package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSSpaceTypeFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSSpaceTypeFragment_ObservableResubscriber(LYSSpaceTypeFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateSpaceTypePropertyTypeListener, "LYSSpaceTypeFragment_updateSpaceTypePropertyTypeListener");
        group.resubscribeAll(target.updateSpaceTypePropertyTypeListener);
    }
}
