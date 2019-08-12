package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSNewHostDiscountFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSNewHostDiscountFragment_ObservableResubscriber(LYSNewHostDiscountFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.promoListener, "LYSNewHostDiscountFragment_promoListener");
        group.resubscribeAll(target.promoListener);
    }
}
