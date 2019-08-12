package com.airbnb.android.lib.fragments.inbox;

import android.content.Context;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.messaging.InboxUnreadCountManager;
import com.airbnb.android.core.messaging.InboxUnreadCountManager.InboxUnreadCountChangeListener;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.activities.HomeActivity.AccountMode;
import com.airbnb.android.lib.activities.NavigationSection;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;

public class BottomBarBadgeInboxHandler {
    /* access modifiers changed from: private */
    public AccountMode accountMode;
    private BottomBar bottomBar;
    InboxUnreadCountManager inboxUnreadCountManager;
    private final InboxUnreadCountChangeListener listener = new InboxUnreadCountChangeListener() {
        public void onHostInboxUnreadCountChange(boolean hasUnread) {
            BottomBarBadgeInboxHandler.this.updateBadgesForHostInboxChange(BottomBarBadgeInboxHandler.this.accountMode, hasUnread);
        }

        public void onGuestInboxUnreadCountChange(boolean hasUnread) {
            BottomBarBadgeInboxHandler.this.updateBadgesForGuestInboxChange(BottomBarBadgeInboxHandler.this.accountMode, hasUnread);
        }
    };

    public BottomBarBadgeInboxHandler(AccountMode accountMode2, Context context, BottomBar bottomBar2) {
        this.accountMode = accountMode2;
        this.bottomBar = bottomBar2;
        ((AirbnbGraph) AirbnbApplication.instance(context).component()).inject(this);
        this.inboxUnreadCountManager.setListener(this.listener);
    }

    public void removeInboxUnreadCountChangeListener() {
        this.inboxUnreadCountManager.removeListener();
    }

    public void updateBadgeOnAccountSwitch(AccountMode accountMode2) {
        this.accountMode = accountMode2;
        updateBadgesForHostInboxChange(accountMode2, this.inboxUnreadCountManager.hasUnread(InboxType.Host));
        updateBadgesForGuestInboxChange(accountMode2, this.inboxUnreadCountManager.hasUnread(InboxType.Guest));
    }

    /* access modifiers changed from: private */
    public void updateBadgesForHostInboxChange(AccountMode accountMode2, boolean hasUnread) {
        switch (accountMode2) {
            case GUEST:
                toggleBadgeTab(this.bottomBar.getTabWithId(NavigationSection.Account.itemId), hasUnread);
                return;
            case HOST:
                toggleBadgeTab(this.bottomBar.getTabWithId(NavigationSection.HostInbox.itemId), hasUnread);
                return;
            case TRIP_HOST:
                return;
            default:
                throw new UnhandledStateException(accountMode2);
        }
    }

    /* access modifiers changed from: private */
    public void updateBadgesForGuestInboxChange(AccountMode accountMode2, boolean hasUnread) {
        switch (accountMode2) {
            case GUEST:
                toggleBadgeTab(this.bottomBar.getTabWithId(NavigationSection.GuestInbox.itemId), hasUnread);
                return;
            case HOST:
                toggleBadgeTab(this.bottomBar.getTabWithId(NavigationSection.Account.itemId), hasUnread);
                return;
            case TRIP_HOST:
                return;
            default:
                throw new UnhandledStateException(accountMode2);
        }
    }

    private static void toggleBadgeTab(BottomBarTab tab, boolean shouldBadgeTab) {
        if (tab != null) {
            if (shouldBadgeTab) {
                tab.showBadge();
            } else {
                tab.hideBadge();
            }
        }
    }
}
