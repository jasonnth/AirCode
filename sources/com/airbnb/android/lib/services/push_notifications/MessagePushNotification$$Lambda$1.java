package com.airbnb.android.lib.services.push_notifications;

import com.airbnb.android.core.events.MessageReceivedEvent;

final /* synthetic */ class MessagePushNotification$$Lambda$1 implements Runnable {
    private final MessagePushNotification arg$1;
    private final long arg$2;
    private final long arg$3;

    private MessagePushNotification$$Lambda$1(MessagePushNotification messagePushNotification, long j, long j2) {
        this.arg$1 = messagePushNotification;
        this.arg$2 = j;
        this.arg$3 = j2;
    }

    public static Runnable lambdaFactory$(MessagePushNotification messagePushNotification, long j, long j2) {
        return new MessagePushNotification$$Lambda$1(messagePushNotification, j, j2);
    }

    public void run() {
        this.arg$1.bus.post(new MessageReceivedEvent(this.arg$1.serverObjectId, this.arg$2, this.arg$3, this.arg$1.content.toString()));
    }
}
