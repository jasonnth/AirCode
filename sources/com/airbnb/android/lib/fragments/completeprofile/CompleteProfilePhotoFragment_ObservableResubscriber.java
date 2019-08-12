package com.airbnb.android.lib.fragments.completeprofile;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CompleteProfilePhotoFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CompleteProfilePhotoFragment_ObservableResubscriber(CompleteProfilePhotoFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.uploadRequestListener, "CompleteProfilePhotoFragment_uploadRequestListener");
    }
}
