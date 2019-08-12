package com.airbnb.android.superhero;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class SuperHeroDataController_ObservableResubscriber extends BaseObservableResubscriber {
    public SuperHeroDataController_ObservableResubscriber(SuperHeroDataController target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.superheroActionListener, "SuperHeroDataController_superheroActionListener");
        group.resubscribeAll(target.superheroActionListener);
    }
}
