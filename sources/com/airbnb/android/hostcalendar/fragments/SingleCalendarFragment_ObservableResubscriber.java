package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class SingleCalendarFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public SingleCalendarFragment_ObservableResubscriber(SingleCalendarFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.calendarRulesListener, "SingleCalendarFragment_calendarRulesListener");
        group.resubscribeAll(target.calendarRulesListener);
    }
}
