package com.airbnb.android.core.messaging;

import com.airbnb.android.core.messaging.MessagingRequestFactory.SendSource;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Post.SendState;
import com.airbnb.android.core.responses.CreateMessageResponse;
import p032rx.functions.Action1;

final /* synthetic */ class MessagingRequestFactory$$Lambda$1 implements Action1 {
    private final MessagingRequestFactory arg$1;
    private final InboxType arg$2;
    private final long arg$3;
    private final long arg$4;
    private final SendSource arg$5;

    private MessagingRequestFactory$$Lambda$1(MessagingRequestFactory messagingRequestFactory, InboxType inboxType, long j, long j2, SendSource sendSource) {
        this.arg$1 = messagingRequestFactory;
        this.arg$2 = inboxType;
        this.arg$3 = j;
        this.arg$4 = j2;
        this.arg$5 = sendSource;
    }

    public static Action1 lambdaFactory$(MessagingRequestFactory messagingRequestFactory, InboxType inboxType, long j, long j2, SendSource sendSource) {
        return new MessagingRequestFactory$$Lambda$1(messagingRequestFactory, inboxType, j, j2, sendSource);
    }

    public void call(Object obj) {
        this.arg$1.setOutgoingPostState(this.arg$2, this.arg$3, this.arg$4, ((CreateMessageResponse) obj).post, SendState.None, this.arg$5);
    }
}
