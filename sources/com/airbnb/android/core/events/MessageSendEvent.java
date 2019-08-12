package com.airbnb.android.core.events;

import com.airbnb.android.core.models.Post;

public class MessageSendEvent {
    public final Post message;
    public final long offlinePostId;
    public final long threadId;

    public MessageSendEvent(long threadId2, long offlinePostId2, Post message2) {
        this.threadId = threadId2;
        this.offlinePostId = offlinePostId2;
        this.message = message2;
    }
}
