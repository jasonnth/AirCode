package com.airbnb.android.lib.activities;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class MessageThreadWebLinkActivity$$Lambda$2 implements Action1 {
    private final MessageThreadWebLinkActivity arg$1;

    private MessageThreadWebLinkActivity$$Lambda$2(MessageThreadWebLinkActivity messageThreadWebLinkActivity) {
        this.arg$1 = messageThreadWebLinkActivity;
    }

    public static Action1 lambdaFactory$(MessageThreadWebLinkActivity messageThreadWebLinkActivity) {
        return new MessageThreadWebLinkActivity$$Lambda$2(messageThreadWebLinkActivity);
    }

    public void call(Object obj) {
        this.arg$1.handleFetchError((AirRequestNetworkException) obj);
    }
}
