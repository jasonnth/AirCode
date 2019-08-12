package com.airbnb.android.booking.fragments.alipay;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AlipayVerificationFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public AlipayVerificationFragment_ObservableResubscriber(AlipayVerificationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.resendCodeRequestListener, "AlipayVerificationFragment_resendCodeRequestListener");
        group.resubscribeAll(target.resendCodeRequestListener);
        setTag((AutoTaggableObserver) target.verificationRequestListener, "AlipayVerificationFragment_verificationRequestListener");
        group.resubscribeAll(target.verificationRequestListener);
        setTag((AutoTaggableObserver) target.paymentOptionResponseRequestListener, "AlipayVerificationFragment_paymentOptionResponseRequestListener");
        group.resubscribeAll(target.paymentOptionResponseRequestListener);
    }
}
