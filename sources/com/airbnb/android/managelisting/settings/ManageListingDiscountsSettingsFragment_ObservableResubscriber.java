package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingDiscountsSettingsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingDiscountsSettingsFragment_ObservableResubscriber(ManageListingDiscountsSettingsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.getPriceSettingsListener, "ManageListingDiscountsSettingsFragment_getPriceSettingsListener");
        group.resubscribeAll(target.getPriceSettingsListener);
        setTag((AutoTaggableObserver) target.updatePriceSettingsListener, "ManageListingDiscountsSettingsFragment_updatePriceSettingsListener");
        group.resubscribeAll(target.updatePriceSettingsListener);
        setTag((AutoTaggableObserver) target.updateListingListener, "ManageListingDiscountsSettingsFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
