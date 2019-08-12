package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSBasePriceFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSBasePriceFragment_ObservableResubscriber(LYSBasePriceFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.basePriceListener, "LYSBasePriceFragment_basePriceListener");
        group.resubscribeAll(target.basePriceListener);
        setTag((AutoTaggableObserver) target.priceTipsListener, "LYSBasePriceFragment_priceTipsListener");
        group.resubscribeAll(target.priceTipsListener);
    }
}
