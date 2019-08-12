package com.airbnb.android.cohosting.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CohostingContractFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CohostingContractFragment_ObservableResubscriber(CohostingContractFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.cohostingContractListener, "CohostingContractFragment_cohostingContractListener");
        group.resubscribeAll(target.cohostingContractListener);
    }
}
