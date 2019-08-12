package com.airbnb.android.lib.tripassistant.amenities;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class HTPhotoPickerActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public HTPhotoPickerActivity_ObservableResubscriber(HTPhotoPickerActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.uploadPhotoListener, "HTPhotoPickerActivity_uploadPhotoListener");
        group.resubscribeAll(target.uploadPhotoListener);
        setTag((AutoTaggableObserver) target.deletePhotoListener, "HTPhotoPickerActivity_deletePhotoListener");
        group.resubscribeAll(target.deletePhotoListener);
    }
}
