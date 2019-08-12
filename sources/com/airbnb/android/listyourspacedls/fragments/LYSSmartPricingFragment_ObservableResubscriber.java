package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSSmartPricingFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSSmartPricingFragment_ObservableResubscriber(LYSSmartPricingFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.smartPricingUpdateListener, "LYSSmartPricingFragment_smartPricingUpdateListener");
        group.resubscribeAll(target.smartPricingUpdateListener);
        setTag((AutoTaggableObserver) target.smartPricingFetchListener, "LYSSmartPricingFragment_smartPricingFetchListener");
        group.resubscribeAll(target.smartPricingFetchListener);
        setTag((AutoTaggableObserver) target.priceTipsListener, "LYSSmartPricingFragment_priceTipsListener");
        group.resubscribeAll(target.priceTipsListener);
        setTag((AutoTaggableObserver) target.basePriceListener, "LYSSmartPricingFragment_basePriceListener");
        group.resubscribeAll(target.basePriceListener);
    }
}
