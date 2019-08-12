package com.airbnb.android.core.messaging.p007db;

import com.airbnb.android.core.models.InboxType;

/* renamed from: com.airbnb.android.core.messaging.db.AutoValue_SyncData */
final class AutoValue_SyncData extends SyncData {
    private final InboxType inboxType;
    private final long syncSequenceId;
    private final long unreadCount;

    AutoValue_SyncData(InboxType inboxType2, long syncSequenceId2, long unreadCount2) {
        if (inboxType2 == null) {
            throw new NullPointerException("Null inboxType");
        }
        this.inboxType = inboxType2;
        this.syncSequenceId = syncSequenceId2;
        this.unreadCount = unreadCount2;
    }

    public InboxType inboxType() {
        return this.inboxType;
    }

    public long syncSequenceId() {
        return this.syncSequenceId;
    }

    public long unreadCount() {
        return this.unreadCount;
    }

    public String toString() {
        return "SyncData{inboxType=" + this.inboxType + ", syncSequenceId=" + this.syncSequenceId + ", unreadCount=" + this.unreadCount + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SyncData)) {
            return false;
        }
        SyncData that = (SyncData) o;
        if (this.inboxType.equals(that.inboxType()) && this.syncSequenceId == that.syncSequenceId() && this.unreadCount == that.unreadCount()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) (((long) (((int) (((long) (((1 * 1000003) ^ this.inboxType.hashCode()) * 1000003)) ^ ((this.syncSequenceId >>> 32) ^ this.syncSequenceId))) * 1000003)) ^ ((this.unreadCount >>> 32) ^ this.unreadCount));
    }
}
