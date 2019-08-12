package com.airbnb.android.lib.fragments.inbox;

import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Thread;

public interface ThreadList {

    public interface Listener {
        void onSuperHeroClicked();

        void onThreadClicked(InboxType inboxType, Thread thread, Long l);

        void onThreadsLoaded(InboxType inboxType, Thread thread);

        boolean setEmptyState(InboxType inboxType, boolean z);
    }

    void setThreadListListener(Listener listener);
}
