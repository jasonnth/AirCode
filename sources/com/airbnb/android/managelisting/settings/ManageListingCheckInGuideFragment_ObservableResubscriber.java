package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingCheckInGuideFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingCheckInGuideFragment_ObservableResubscriber(ManageListingCheckInGuideFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.getGuideListener, "ManageListingCheckInGuideFragment_getGuideListener");
        group.resubscribeAll(target.getGuideListener);
        setTag((AutoTaggableObserver) target.createGuideListener, "ManageListingCheckInGuideFragment_createGuideListener");
        group.resubscribeAll(target.createGuideListener);
        setTag((AutoTaggableObserver) target.updateGuideListener, "ManageListingCheckInGuideFragment_updateGuideListener");
        group.resubscribeAll(target.updateGuideListener);
        setTag((AutoTaggableObserver) target.deleteStepListener, "ManageListingCheckInGuideFragment_deleteStepListener");
        group.resubscribeAll(target.deleteStepListener);
        setTag((AutoTaggableObserver) target.createStepForPhotoListener, "ManageListingCheckInGuideFragment_createStepForPhotoListener");
        group.resubscribeAll(target.createStepForPhotoListener);
    }
}
