package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.NotificationPreferencesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class NotificationSettingsFragment$$Lambda$3 implements Action1 {
    private final NotificationSettingsFragment arg$1;

    private NotificationSettingsFragment$$Lambda$3(NotificationSettingsFragment notificationSettingsFragment) {
        this.arg$1 = notificationSettingsFragment;
    }

    public static Action1 lambdaFactory$(NotificationSettingsFragment notificationSettingsFragment) {
        return new NotificationSettingsFragment$$Lambda$3(notificationSettingsFragment);
    }

    public void call(Object obj) {
        NotificationSettingsFragment.lambda$new$5(this.arg$1, (NotificationPreferencesResponse) obj);
    }
}
