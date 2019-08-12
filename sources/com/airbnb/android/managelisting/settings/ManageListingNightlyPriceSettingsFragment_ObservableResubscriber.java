package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingNightlyPriceSettingsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingNightlyPriceSettingsFragment_ObservableResubscriber(ManageListingNightlyPriceSettingsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.smartPricingListener, "ManageListingNightlyPriceSettingsFragment_smartPricingListener");
        group.resubscribeAll(target.smartPricingListener);
        setTag((AutoTaggableObserver) target.basePriceListener, "ManageListingNightlyPriceSettingsFragment_basePriceListener");
        group.resubscribeAll(target.basePriceListener);
    }
}
