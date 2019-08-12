package com.airbnb.android.lib.services.push_notifications;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.PushNotificationType;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.core.services.PushNotificationBuilder;
import com.airbnb.android.core.services.push_notifications.PushNotification;
import com.airbnb.android.lib.host.HostReservationIntentFactory;

public class HostReservationPushNotification extends PushNotification {
    public HostReservationPushNotification(Context context, Intent intent) {
        super(context, intent);
    }

    /* access modifiers changed from: protected */
    public void buildNotification(PushNotificationBuilder builder) {
        builder.setLaunchIntentWithMain(HostReservationIntentFactory.forReservationId(this.context, this.serverObjectId, ROLaunchSource.PushNotification));
        if (this.typeEnum == PushNotificationType.ReservationHostRequest) {
            builder.addAcceptAction(HostReservationIntentFactory.forReservationId(this.context, this.serverObjectId, ROLaunchSource.PushNotificationActionAccept), isInquiry());
        }
    }
}
