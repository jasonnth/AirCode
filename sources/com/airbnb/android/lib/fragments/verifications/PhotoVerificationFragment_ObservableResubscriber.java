package com.airbnb.android.lib.fragments.verifications;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PhotoVerificationFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public PhotoVerificationFragment_ObservableResubscriber(PhotoVerificationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.requestListener, "PhotoVerificationFragment_requestListener");
    }
}
