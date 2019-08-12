package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.managelisting.settings.ManageListingLicenseOrRegistrationNumberFragment_ObservableResubscriber */
public class C7432x3459be6 extends BaseObservableResubscriber {
    public C7432x3459be6(ManageListingLicenseOrRegistrationNumberFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingLicenseRequestListener, "ManageListingLicenseOrRegistrationNumberFragment_updateListingLicenseRequestListener");
        group.resubscribeAll(target.updateListingLicenseRequestListener);
    }
}
