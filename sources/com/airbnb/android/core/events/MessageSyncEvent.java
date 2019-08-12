package com.airbnb.android.core.events;

import com.airbnb.android.core.models.InboxType;
import java.util.Set;

public class MessageSyncEvent {
    public final InboxType inboxType;
    public final long unreadCount;
    private final Set<Long> updatedThreadIds;

    public MessageSyncEvent(InboxType inboxType2, long unreadCount2, Set<Long> updatedThreadIds2) {
        this.inboxType = inboxType2;
        this.unreadCount = unreadCount2;
        this.updatedThreadIds = updatedThreadIds2;
    }

    public boolean appliesToThread(long threadId) {
        return this.updatedThreadIds.contains(Long.valueOf(threadId));
    }
}
