package com.airbnb.android.lib.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class NotificationSettingsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public NotificationSettingsFragment_ObservableResubscriber(NotificationSettingsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.preferencesRequestListener, "NotificationSettingsFragment_preferencesRequestListener");
        group.resubscribeAll(target.preferencesRequestListener);
        setTag((AutoTaggableObserver) target.updatePreferenceListener, "NotificationSettingsFragment_updatePreferenceListener");
        group.resubscribeAll(target.updatePreferenceListener);
    }
}
