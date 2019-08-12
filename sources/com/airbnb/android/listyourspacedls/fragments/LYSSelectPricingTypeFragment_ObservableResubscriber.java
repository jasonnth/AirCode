package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSSelectPricingTypeFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSSelectPricingTypeFragment_ObservableResubscriber(LYSSelectPricingTypeFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updatePricingModeListener, "LYSSelectPricingTypeFragment_updatePricingModeListener");
        group.resubscribeAll(target.updatePricingModeListener);
    }
}
