package com.airbnb.android.core.messaging;

import android.os.Handler;
import android.os.Looper;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.events.MessageSyncEvent;
import com.airbnb.android.core.models.InboxType;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class InboxUnreadCountManager {
    private long guestInboxUnreadCount;
    private long hostInboxUnreadCount;
    private InboxUnreadCountChangeListener listener;
    private final Handler uiHandler = new Handler(Looper.getMainLooper());

    public interface InboxUnreadCountChangeListener {
        void onGuestInboxUnreadCountChange(boolean z);

        void onHostInboxUnreadCountChange(boolean z);
    }

    public InboxUnreadCountManager(Bus bus) {
        new Handler(Looper.getMainLooper()).post(InboxUnreadCountManager$$Lambda$1.lambdaFactory$(this, bus));
    }

    public boolean hasUnread(InboxType inboxType) {
        switch (inboxType) {
            case Host:
                if (this.hostInboxUnreadCount != 0) {
                    return true;
                }
                return false;
            case Guest:
                if (this.guestInboxUnreadCount == 0) {
                    return false;
                }
                return true;
            default:
                throw new UnhandledStateException(inboxType);
        }
    }

    public void setUnreadCount(InboxType inboxType, long unreadCount) {
        switch (inboxType) {
            case Host:
                this.hostInboxUnreadCount = unreadCount;
                this.uiHandler.post(InboxUnreadCountManager$$Lambda$2.lambdaFactory$(this));
                return;
            case Guest:
                this.guestInboxUnreadCount = unreadCount;
                this.uiHandler.post(InboxUnreadCountManager$$Lambda$3.lambdaFactory$(this));
                return;
            default:
                throw new UnhandledStateException(inboxType);
        }
    }

    static /* synthetic */ void lambda$setUnreadCount$1(InboxUnreadCountManager inboxUnreadCountManager) {
        if (inboxUnreadCountManager.listener != null) {
            inboxUnreadCountManager.listener.onHostInboxUnreadCountChange(inboxUnreadCountManager.hasUnread(InboxType.Host));
        }
    }

    static /* synthetic */ void lambda$setUnreadCount$2(InboxUnreadCountManager inboxUnreadCountManager) {
        if (inboxUnreadCountManager.listener != null) {
            inboxUnreadCountManager.listener.onGuestInboxUnreadCountChange(inboxUnreadCountManager.hasUnread(InboxType.Guest));
        }
    }

    public void setListener(InboxUnreadCountChangeListener listener2) {
        this.listener = listener2;
        listener2.onGuestInboxUnreadCountChange(hasUnread(InboxType.Guest));
        listener2.onHostInboxUnreadCountChange(hasUnread(InboxType.Host));
    }

    public void removeListener() {
        this.listener = null;
    }

    public void decreaseUnreadCount(InboxType inboxType) {
        switch (inboxType) {
            case Host:
                setUnreadCount(inboxType, this.hostInboxUnreadCount - 1);
                return;
            case Guest:
                setUnreadCount(inboxType, this.guestInboxUnreadCount - 1);
                return;
            default:
                throw new UnhandledStateException(inboxType);
        }
    }

    @Subscribe
    public void onMessageSync(MessageSyncEvent event) {
        setUnreadCount(event.inboxType, event.unreadCount);
    }
}
