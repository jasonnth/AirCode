package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class NotificationSettingsFragment$$Lambda$4 implements Action1 {
    private final NotificationSettingsFragment arg$1;

    private NotificationSettingsFragment$$Lambda$4(NotificationSettingsFragment notificationSettingsFragment) {
        this.arg$1 = notificationSettingsFragment;
    }

    public static Action1 lambdaFactory$(NotificationSettingsFragment notificationSettingsFragment) {
        return new NotificationSettingsFragment$$Lambda$4(notificationSettingsFragment);
    }

    public void call(Object obj) {
        NotificationSettingsFragment.lambda$new$6(this.arg$1, (AirRequestNetworkException) obj);
    }
}
