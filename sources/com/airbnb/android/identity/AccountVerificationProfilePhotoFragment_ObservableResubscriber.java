package com.airbnb.android.identity;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AccountVerificationProfilePhotoFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public AccountVerificationProfilePhotoFragment_ObservableResubscriber(AccountVerificationProfilePhotoFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.uploadPhotoRequestListener, "AccountVerificationProfilePhotoFragment_uploadPhotoRequestListener");
        group.resubscribeAll(target.uploadPhotoRequestListener);
        setTag((AutoTaggableObserver) target.verificationsRequestListener, "AccountVerificationProfilePhotoFragment_verificationsRequestListener");
        group.resubscribeAll(target.verificationsRequestListener);
    }
}
