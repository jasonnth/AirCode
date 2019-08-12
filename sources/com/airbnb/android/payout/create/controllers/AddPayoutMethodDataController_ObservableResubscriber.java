package com.airbnb.android.payout.create.controllers;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AddPayoutMethodDataController_ObservableResubscriber extends BaseObservableResubscriber {
    public AddPayoutMethodDataController_ObservableResubscriber(AddPayoutMethodDataController target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.fetchPayoutFormListener, "AddPayoutMethodDataController_fetchPayoutFormListener");
        group.resubscribeAll(target.fetchPayoutFormListener);
        setTag((AutoTaggableObserver) target.fetchUserAddressListener, "AddPayoutMethodDataController_fetchUserAddressListener");
        group.resubscribeAll(target.fetchUserAddressListener);
        setTag((AutoTaggableObserver) target.fetchRedirectUrlListener, "AddPayoutMethodDataController_fetchRedirectUrlListener");
        group.resubscribeAll(target.fetchRedirectUrlListener);
        setTag((AutoTaggableObserver) target.createPayoutMethodListener, "AddPayoutMethodDataController_createPayoutMethodListener");
        group.resubscribeAll(target.createPayoutMethodListener);
    }
}
