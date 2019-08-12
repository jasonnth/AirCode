package com.airbnb.android.cohosting.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.cohosting.fragments.CohostingShareEarningsWithFeeOptionFragment_ObservableResubscriber */
public class C5688xcad858b2 extends BaseObservableResubscriber {
    public C5688xcad858b2(CohostingShareEarningsWithFeeOptionFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.setCohostingContractListener, "CohostingShareEarningsWithFeeOptionFragment_setCohostingContractListener");
        group.resubscribeAll(target.setCohostingContractListener);
    }
}
