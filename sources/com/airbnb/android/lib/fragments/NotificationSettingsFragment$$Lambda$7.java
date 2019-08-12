package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.models.NotificationPreference;
import com.google.common.base.Predicate;

final /* synthetic */ class NotificationSettingsFragment$$Lambda$7 implements Predicate {
    private static final NotificationSettingsFragment$$Lambda$7 instance = new NotificationSettingsFragment$$Lambda$7();

    private NotificationSettingsFragment$$Lambda$7() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return NotificationSettingsFragment.lambda$null$0((NotificationPreference) obj);
    }
}
