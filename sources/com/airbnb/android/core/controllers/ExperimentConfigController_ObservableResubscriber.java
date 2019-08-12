package com.airbnb.android.core.controllers;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ExperimentConfigController_ObservableResubscriber extends BaseObservableResubscriber {
    public ExperimentConfigController_ObservableResubscriber(ExperimentConfigController target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.trebuchetRequestListener, "ExperimentConfigController_trebuchetRequestListener");
        group.resubscribeAll(target.trebuchetRequestListener);
    }
}
