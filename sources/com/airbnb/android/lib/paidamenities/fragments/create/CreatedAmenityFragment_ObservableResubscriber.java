package com.airbnb.android.lib.paidamenities.fragments.create;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CreatedAmenityFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CreatedAmenityFragment_ObservableResubscriber(CreatedAmenityFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.createPaidAmenityRequestListener, "CreatedAmenityFragment_createPaidAmenityRequestListener");
        group.resubscribeAll(target.createPaidAmenityRequestListener);
        setTag((AutoTaggableObserver) target.updatePaidAmenityRequestListener, "CreatedAmenityFragment_updatePaidAmenityRequestListener");
        group.resubscribeAll(target.updatePaidAmenityRequestListener);
    }
}
