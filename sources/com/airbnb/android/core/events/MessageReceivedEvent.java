package com.airbnb.android.core.events;

import com.airbnb.android.core.models.Post;
import com.airbnb.android.core.models.Post.SendState;

public class MessageReceivedEvent {
    public final Post post;
    public final long threadId;

    public MessageReceivedEvent(long threadId2, long fromUserId, long postId, String message) {
        this.threadId = threadId2;
        this.post = Post.createTextPost(message, fromUserId, postId, SendState.None);
    }
}
