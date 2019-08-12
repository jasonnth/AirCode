package com.airbnb.android.lib.services.push_notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.services.PushNotificationBuilder;
import com.airbnb.android.core.services.push_notifications.PushNotification;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.AutoAirModalLargeActivity;
import com.airbnb.android.lib.fragments.LegacyHostReactivationFragment;

public class HostSuspendedPushNotification extends PushNotification {
    public HostSuspendedPushNotification(Context context, Intent intent) {
        super(context, intent);
    }

    /* access modifiers changed from: protected */
    public void buildNotification(PushNotificationBuilder builder) {
        builder.setLaunchIntentWithMain(AutoAirModalLargeActivity.intentForFragment(this.context, LegacyHostReactivationFragment.class, (Bundle) null, C0880R.string.host_reactivation_reactivate_title));
    }
}
