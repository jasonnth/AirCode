package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.models.NotificationPreference;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class NotificationSettingsFragment$$Lambda$5 implements OnCheckedChangeListener {
    private final NotificationSettingsFragment arg$1;
    private final NotificationPreference arg$2;

    private NotificationSettingsFragment$$Lambda$5(NotificationSettingsFragment notificationSettingsFragment, NotificationPreference notificationPreference) {
        this.arg$1 = notificationSettingsFragment;
        this.arg$2 = notificationPreference;
    }

    public static OnCheckedChangeListener lambdaFactory$(NotificationSettingsFragment notificationSettingsFragment, NotificationPreference notificationPreference) {
        return new NotificationSettingsFragment$$Lambda$5(notificationSettingsFragment, notificationPreference);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        NotificationSettingsFragment.lambda$pushChanged$3(this.arg$1, this.arg$2, switchRowInterface, z);
    }
}
