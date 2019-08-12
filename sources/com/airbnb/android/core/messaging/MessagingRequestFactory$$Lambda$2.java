package com.airbnb.android.core.messaging;

import com.airbnb.android.core.messaging.MessagingRequestFactory.SendSource;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Post;
import com.airbnb.android.core.models.Post.SendState;
import p032rx.functions.Action1;

final /* synthetic */ class MessagingRequestFactory$$Lambda$2 implements Action1 {
    private final MessagingRequestFactory arg$1;
    private final InboxType arg$2;
    private final long arg$3;
    private final long arg$4;
    private final Post arg$5;
    private final SendSource arg$6;

    private MessagingRequestFactory$$Lambda$2(MessagingRequestFactory messagingRequestFactory, InboxType inboxType, long j, long j2, Post post, SendSource sendSource) {
        this.arg$1 = messagingRequestFactory;
        this.arg$2 = inboxType;
        this.arg$3 = j;
        this.arg$4 = j2;
        this.arg$5 = post;
        this.arg$6 = sendSource;
    }

    public static Action1 lambdaFactory$(MessagingRequestFactory messagingRequestFactory, InboxType inboxType, long j, long j2, Post post, SendSource sendSource) {
        return new MessagingRequestFactory$$Lambda$2(messagingRequestFactory, inboxType, j, j2, post, sendSource);
    }

    public void call(Object obj) {
        this.arg$1.setOutgoingPostState(this.arg$2, this.arg$3, this.arg$4, this.arg$5, SendState.Failed, this.arg$6);
    }
}
