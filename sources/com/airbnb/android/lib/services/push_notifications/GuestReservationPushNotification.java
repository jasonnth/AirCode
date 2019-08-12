package com.airbnb.android.lib.services.push_notifications;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.PushNotificationType;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.intents.GuestRecoveryActivityIntents;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.services.PushNotificationBuilder;
import com.airbnb.android.core.services.push_notifications.PushNotification;
import com.airbnb.android.lib.activities.DLSReservationObjectActivity;

public class GuestReservationPushNotification extends PushNotification {
    public GuestReservationPushNotification(Context context, Intent intent) {
        super(context, intent);
    }

    /* access modifiers changed from: protected */
    public void receivePushNotification() {
        syncGuestUpcomingTrips(true);
    }

    /* access modifiers changed from: protected */
    public void buildNotification(PushNotificationBuilder builder) {
        if ((this.typeEnum == PushNotificationType.ReservationGuestCancelled || this.typeEnum == PushNotificationType.ReservationGuestDeclined) && FeatureToggles.enableRejectionRecovery()) {
            builder.setLaunchIntentWithMain(GuestRecoveryActivityIntents.intentForReservationId(this.context, this.serverObjectId, this.typeEnum == PushNotificationType.ReservationGuestDeclined ? ReservationStatus.Denied : ReservationStatus.Cancelled));
            return;
        }
        builder.setLaunchIntentWithMain(DLSReservationObjectActivity.intentForReservationId(this.context, this.serverObjectId));
        if (this.typeEnum == PushNotificationType.ReservationGuestAccepted) {
            builder.addItineraryAction(DLSReservationObjectActivity.intentForReservationId(this.context, this.serverObjectId));
            builder.hasSeparateNotificationForWearable(true);
        }
    }
}
