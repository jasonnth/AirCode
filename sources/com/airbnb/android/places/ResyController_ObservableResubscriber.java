package com.airbnb.android.places;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ResyController_ObservableResubscriber extends BaseObservableResubscriber {
    public ResyController_ObservableResubscriber(ResyController target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.restaurantAvailabilityRequestListener, "ResyController_restaurantAvailabilityRequestListener");
        group.resubscribeAll(target.restaurantAvailabilityRequestListener);
    }
}
