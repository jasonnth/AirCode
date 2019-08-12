package com.airbnb.android.identity;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AccountVerificationSelfieConfirmFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public AccountVerificationSelfieConfirmFragment_ObservableResubscriber(AccountVerificationSelfieConfirmFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.selfieGetResponseListener, "AccountVerificationSelfieConfirmFragment_selfieGetResponseListener");
        group.resubscribeAll(target.selfieGetResponseListener);
        setTag((AutoTaggableObserver) target.misnapSelfiePostResponseListener, "AccountVerificationSelfieConfirmFragment_misnapSelfiePostResponseListener");
        group.resubscribeAll(target.misnapSelfiePostResponseListener);
        setTag((AutoTaggableObserver) target.airbnbSelfiePostResponseListener, "AccountVerificationSelfieConfirmFragment_airbnbSelfiePostResponseListener");
        group.resubscribeAll(target.airbnbSelfiePostResponseListener);
    }
}
