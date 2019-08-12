package com.airbnb.android.lib.fragments.inbox;

import com.airbnb.android.core.messaging.InboxUnreadCountManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BottomBarBadgeInboxHandler_MembersInjector implements MembersInjector<BottomBarBadgeInboxHandler> {
    static final /* synthetic */ boolean $assertionsDisabled = (!BottomBarBadgeInboxHandler_MembersInjector.class.desiredAssertionStatus());
    private final Provider<InboxUnreadCountManager> inboxUnreadCountManagerProvider;

    public BottomBarBadgeInboxHandler_MembersInjector(Provider<InboxUnreadCountManager> inboxUnreadCountManagerProvider2) {
        if ($assertionsDisabled || inboxUnreadCountManagerProvider2 != null) {
            this.inboxUnreadCountManagerProvider = inboxUnreadCountManagerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<BottomBarBadgeInboxHandler> create(Provider<InboxUnreadCountManager> inboxUnreadCountManagerProvider2) {
        return new BottomBarBadgeInboxHandler_MembersInjector(inboxUnreadCountManagerProvider2);
    }

    public void injectMembers(BottomBarBadgeInboxHandler instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.inboxUnreadCountManager = (InboxUnreadCountManager) this.inboxUnreadCountManagerProvider.get();
    }

    public static void injectInboxUnreadCountManager(BottomBarBadgeInboxHandler instance, Provider<InboxUnreadCountManager> inboxUnreadCountManagerProvider2) {
        instance.inboxUnreadCountManager = (InboxUnreadCountManager) inboxUnreadCountManagerProvider2.get();
    }
}
