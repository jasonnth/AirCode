package com.airbnb.android.payout.manage.controllers;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManagePayoutDataController_ObservableResubscriber extends BaseObservableResubscriber {
    public ManagePayoutDataController_ObservableResubscriber(ManagePayoutDataController target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.payoutInstrumentsListener, "ManagePayoutDataController_payoutInstrumentsListener");
        group.resubscribeAll(target.payoutInstrumentsListener);
    }
}
