package com.airbnb.android.identity;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AccountVerificationOfflineIdController_ObservableResubscriber extends BaseObservableResubscriber {
    public AccountVerificationOfflineIdController_ObservableResubscriber(AccountVerificationOfflineIdController target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.jumioScanReferenceUploadRequestListener, "AccountVerificationOfflineIdController_jumioScanReferenceUploadRequestListener");
        group.resubscribeAll(target.jumioScanReferenceUploadRequestListener);
        setTag((AutoTaggableObserver) target.misnapResultRequestListener, "AccountVerificationOfflineIdController_misnapResultRequestListener");
        group.resubscribeAll(target.misnapResultRequestListener);
        setTag((AutoTaggableObserver) target.jumioCredentialsResponseRequestListener, "AccountVerificationOfflineIdController_jumioCredentialsResponseRequestListener");
        group.resubscribeAll(target.jumioCredentialsResponseRequestListener);
    }
}
