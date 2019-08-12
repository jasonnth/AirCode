package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.managelisting.settings.ManageListingPreBookingAddQuestionsFragment_ObservableResubscriber */
public class C7437x45d4794c extends BaseObservableResubscriber {
    public C7437x45d4794c(ManageListingPreBookingAddQuestionsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateBookingSettingsListener, "ManageListingPreBookingAddQuestionsFragment_updateBookingSettingsListener");
        group.resubscribeAll(target.updateBookingSettingsListener);
    }
}
