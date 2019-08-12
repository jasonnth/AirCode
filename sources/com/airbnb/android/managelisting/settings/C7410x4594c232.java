package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.managelisting.settings.ManageListingCheckinTypeTextSettingFragment_ObservableResubscriber */
public class C7410x4594c232 extends BaseObservableResubscriber {
    public C7410x4594c232(ManageListingCheckinTypeTextSettingFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateAmenityListener, "ManageListingCheckinTypeTextSettingFragment_updateAmenityListener");
        group.resubscribeAll(target.updateAmenityListener);
        setTag((AutoTaggableObserver) target.refetchInformationListener, "ManageListingCheckinTypeTextSettingFragment_refetchInformationListener");
        group.resubscribeAll(target.refetchInformationListener);
    }
}
