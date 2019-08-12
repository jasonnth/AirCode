package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class DiscountsExampleFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public DiscountsExampleFragment_ObservableResubscriber(DiscountsExampleFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.exampleRequestListener, "DiscountsExampleFragment_exampleRequestListener");
        group.resubscribeAll(target.exampleRequestListener);
    }
}
