package com.airbnb.android.core.models;

import android.util.Log;
import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.android.core.notifications.PushNotificationConstants;
import com.airbnb.android.utils.Strap;

public enum InboxType {
    Guest(TripRole.ROLE_KEY_GUEST, false),
    GuestArchived(TripRole.ROLE_KEY_GUEST, true),
    Host(TripRole.ROLE_KEY_HOST, false),
    HostArchived(TripRole.ROLE_KEY_HOST, true),
    ExperienceHost("experience_host", false);
    
    private static final String TAG = null;
    public final boolean archived;
    public final String inboxRole;

    static {
        TAG = InboxType.class.getName();
    }

    public static InboxType inboxFromIsHost(boolean isHost) {
        return isHost ? Host : Guest;
    }

    public static InboxType inboxFromKey(String key) {
        InboxType[] values;
        for (InboxType inboxType : values()) {
            if (!inboxType.archived && inboxType.inboxRole.equals(key)) {
                return inboxType;
            }
        }
        Log.w(TAG, "Unable to decode inbox type: " + key);
        return null;
    }

    private InboxType(String inboxRole2, boolean archived2) {
        this.inboxRole = inboxRole2;
        this.archived = archived2;
    }

    public boolean isHostMode() {
        return this == Host || this == HostArchived;
    }

    public boolean isGuestMode() {
        return this == Guest || this == GuestArchived;
    }

    public String inboxFilter() {
        return this.archived ? PushNotificationConstants.EXTRA_IS_HIDDEN : "all";
    }

    public String inboxTypeLoggingString() {
        return this.archived ? "archive" : "inbox";
    }

    public boolean useMessagingSync() {
        return this == Guest || this == Host;
    }

    public InboxType getNonArchivedInboxType() {
        switch (this) {
            case Guest:
            case GuestArchived:
                return Guest;
            case Host:
            case HostArchived:
                return Host;
            case ExperienceHost:
                return ExperienceHost;
            default:
                throw new IllegalStateException("Unsupported inbox type: " + name());
        }
    }

    public InboxType getArchivedInboxType() {
        switch (this) {
            case Guest:
            case GuestArchived:
                return GuestArchived;
            case Host:
            case HostArchived:
                return HostArchived;
            default:
                throw new IllegalStateException("Unsupported inbox type: " + name());
        }
    }

    public Strap addLoggingParams(Strap strap) {
        return strap.mo11639kv("role", this.inboxRole).mo11639kv(ThreadFragmentIntents.ARG_INBOX_TYPE, inboxTypeLoggingString());
    }
}
