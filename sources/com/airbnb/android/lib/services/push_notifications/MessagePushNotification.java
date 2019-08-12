package com.airbnb.android.lib.services.push_notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.PushNotificationType;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.services.PushNotificationBuilder;
import com.airbnb.android.core.services.push_notifications.PushNotification;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.activities.DLSReservationObjectActivity;
import com.airbnb.android.lib.activities.HostReservationObjectActivity;
import com.squareup.otto.Bus;

public class MessagePushNotification extends PushNotification {
    private static final String POST_ID_KEY = "post_id";
    private static final String SENDER_ID_KEY = "sender_id";
    private final Bus bus;
    public final InboxType inboxType;
    private final PushNotificationType type;

    public MessagePushNotification(Context context, Intent intent, Bus bus2, PushNotificationType type2) {
        super(context, intent);
        this.bus = bus2;
        this.type = type2;
        this.inboxType = getInboxType(intent.getExtras());
        ((AirbnbGraph) AirbnbApplication.instance(context).component()).inject(this);
    }

    /* access modifiers changed from: protected */
    public void receivePushNotification() {
        if (!this.type.matchesAny(PushNotificationType.MessageWithImageAttachment, PushNotificationType.MessageNonBooking)) {
            new Handler(Looper.getMainLooper()).post(MessagePushNotification$$Lambda$1.lambdaFactory$(this, getSenderId(this.intent.getExtras()), getPostId(this.intent.getExtras())));
            syncGuestUpcomingTrips(this.inboxType.isGuestMode());
        }
    }

    /* access modifiers changed from: protected */
    public void buildNotification(PushNotificationBuilder builder) {
        Intent threadIntent = ThreadFragmentIntents.newIntent(this.context, this.serverObjectId, this.inboxType);
        builder.setLaunchIntentWithMain(threadIntent);
        builder.addMessageAction(false, threadIntent);
        if (isAccepted() && this.inboxType.isGuestMode()) {
            builder.addItineraryAction(DLSReservationObjectActivity.intentForThread(this.context, this.serverObjectId));
        }
        if (isAccepted() && this.inboxType.isHostMode()) {
            builder.addItineraryAction(HostReservationObjectActivity.intentForThread(this.context, this.serverObjectId, ROLaunchSource.PushNotification));
        }
    }

    private static long getSenderId(Bundle args) {
        long j = -1;
        if (!args.containsKey(SENDER_ID_KEY)) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Message push notification is missing sender ID"));
            return j;
        }
        try {
            return Long.parseLong(args.getString(SENDER_ID_KEY));
        } catch (NumberFormatException e) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Mesasge push notification has malformed sender ID"));
            return j;
        }
    }

    private static long getPostId(Bundle args) {
        if (!args.containsKey(SENDER_ID_KEY)) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Message push notification is missing post ID"));
        }
        return args.getLong("post_id", -1);
    }

    private static InboxType getInboxType(Bundle args) {
        String role = args.getString("role");
        InboxType inboxType2 = InboxType.inboxFromKey(role);
        if (inboxType2 != null) {
            return inboxType2;
        }
        BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Messaging push notification unable to decode inbox type: " + role));
        return InboxType.Guest;
    }
}
