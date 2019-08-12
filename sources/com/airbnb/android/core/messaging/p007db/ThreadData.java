package com.airbnb.android.core.messaging.p007db;

import com.airbnb.android.core.models.Thread;

/* renamed from: com.airbnb.android.core.messaging.db.ThreadData */
public abstract class ThreadData implements ThreadDataModel {
    public static boolean matchesThreadIdAndTimestamp(ThreadData data, Thread thread) {
        if (data == null && thread == null) {
            return true;
        }
        if (data == null || thread == null) {
            return false;
        }
        if (data.threadId() == thread.getId() || AirDateTimeConverter.equals(thread.getLastMessageAt(), Long.valueOf(data.lastMessageAt()))) {
            return true;
        }
        return false;
    }
}
