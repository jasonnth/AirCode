package com.airbnb.android.lib.paidamenities.fragments.create;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class SelectAmenityTypeFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public SelectAmenityTypeFragment_ObservableResubscriber(SelectAmenityTypeFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.paidAmenityCategoriesResponseRequestListener, "SelectAmenityTypeFragment_paidAmenityCategoriesResponseRequestListener");
        group.resubscribeAll(target.paidAmenityCategoriesResponseRequestListener);
    }
}
