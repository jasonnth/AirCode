package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSRentHistoryFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSRentHistoryFragment_ObservableResubscriber(LYSRentHistoryFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.calendarRulesListener, "LYSRentHistoryFragment_calendarRulesListener");
        group.resubscribeAll(target.calendarRulesListener);
        setTag((AutoTaggableObserver) target.personaListener, "LYSRentHistoryFragment_personaListener");
        group.resubscribeAll(target.personaListener);
    }
}
