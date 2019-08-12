package com.airbnb.android.core.messaging.p007db;

import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Thread;

/* renamed from: com.airbnb.android.core.messaging.db.AutoValue_ThreadData */
final class AutoValue_ThreadData extends ThreadData {
    private final boolean doesContainAllInfo;
    private final InboxType inboxType;
    private final boolean isInThreadlist;
    private final long lastMessageAt;
    private final long threadId;
    private final Thread threadModel;

    AutoValue_ThreadData(long threadId2, InboxType inboxType2, Thread threadModel2, long lastMessageAt2, boolean isInThreadlist2, boolean doesContainAllInfo2) {
        this.threadId = threadId2;
        if (inboxType2 == null) {
            throw new NullPointerException("Null inboxType");
        }
        this.inboxType = inboxType2;
        if (threadModel2 == null) {
            throw new NullPointerException("Null threadModel");
        }
        this.threadModel = threadModel2;
        this.lastMessageAt = lastMessageAt2;
        this.isInThreadlist = isInThreadlist2;
        this.doesContainAllInfo = doesContainAllInfo2;
    }

    public long threadId() {
        return this.threadId;
    }

    public InboxType inboxType() {
        return this.inboxType;
    }

    public Thread threadModel() {
        return this.threadModel;
    }

    public long lastMessageAt() {
        return this.lastMessageAt;
    }

    public boolean isInThreadlist() {
        return this.isInThreadlist;
    }

    public boolean doesContainAllInfo() {
        return this.doesContainAllInfo;
    }

    public String toString() {
        return "ThreadData{threadId=" + this.threadId + ", inboxType=" + this.inboxType + ", threadModel=" + this.threadModel + ", lastMessageAt=" + this.lastMessageAt + ", isInThreadlist=" + this.isInThreadlist + ", doesContainAllInfo=" + this.doesContainAllInfo + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ThreadData)) {
            return false;
        }
        ThreadData that = (ThreadData) o;
        if (this.threadId == that.threadId() && this.inboxType.equals(that.inboxType()) && this.threadModel.equals(that.threadModel()) && this.lastMessageAt == that.lastMessageAt() && this.isInThreadlist == that.isInThreadlist() && this.doesContainAllInfo == that.doesContainAllInfo()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2 = 1231;
        int h = ((int) (((long) (((((((int) (((long) (1 * 1000003)) ^ ((this.threadId >>> 32) ^ this.threadId))) * 1000003) ^ this.inboxType.hashCode()) * 1000003) ^ this.threadModel.hashCode()) * 1000003)) ^ ((this.lastMessageAt >>> 32) ^ this.lastMessageAt))) * 1000003;
        if (this.isInThreadlist) {
            i = 1231;
        } else {
            i = 1237;
        }
        int h2 = (h ^ i) * 1000003;
        if (!this.doesContainAllInfo) {
            i2 = 1237;
        }
        return h2 ^ i2;
    }
}
