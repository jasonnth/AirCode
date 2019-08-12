package com.airbnb.android.lib.payments.networking.createbill.requester;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CreateBillDelegate_ObservableResubscriber extends BaseObservableResubscriber {
    public CreateBillDelegate_ObservableResubscriber(CreateBillDelegate target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.requestListener, "CreateBillDelegate_requestListener");
        group.resubscribeAll(target.requestListener);
    }
}
