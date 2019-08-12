package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSHostingFrequencyFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSHostingFrequencyFragment_ObservableResubscriber(LYSHostingFrequencyFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.personaListener, "LYSHostingFrequencyFragment_personaListener");
        group.resubscribeAll(target.personaListener);
        setTag((AutoTaggableObserver) target.calendarRulesListener, "LYSHostingFrequencyFragment_calendarRulesListener");
        group.resubscribeAll(target.calendarRulesListener);
    }
}
