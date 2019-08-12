package com.airbnb.android.managelisting.settings.photos;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PhotoFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public PhotoFragment_ObservableResubscriber(PhotoFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingPhotoListener, "PhotoFragment_updateListingPhotoListener");
        group.resubscribeAll(target.updateListingPhotoListener);
        setTag((AutoTaggableObserver) target.deleteListingPhotoListener, "PhotoFragment_deleteListingPhotoListener");
        group.resubscribeAll(target.deleteListingPhotoListener);
    }
}
