package com.airbnb.android.managelisting.settings.photos;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManagePhotosFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManagePhotosFragment_ObservableResubscriber(ManagePhotosFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updatePhotoOrderListener, "ManagePhotosFragment_updatePhotoOrderListener");
        group.resubscribeAll(target.updatePhotoOrderListener);
    }
}
