package com.airbnb.android.cohosting.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CohostingStopShareEarningsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CohostingStopShareEarningsFragment_ObservableResubscriber(CohostingStopShareEarningsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.setCohostingContractListener, "CohostingStopShareEarningsFragment_setCohostingContractListener");
        group.resubscribeAll(target.setCohostingContractListener);
    }
}
