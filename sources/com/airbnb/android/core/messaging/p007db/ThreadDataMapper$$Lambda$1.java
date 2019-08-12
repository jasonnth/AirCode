package com.airbnb.android.core.messaging.p007db;

import com.airbnb.android.core.messaging.p007db.ThreadDataModel.Creator;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Thread;

/* renamed from: com.airbnb.android.core.messaging.db.ThreadDataMapper$$Lambda$1 */
final /* synthetic */ class ThreadDataMapper$$Lambda$1 implements Creator {
    private static final ThreadDataMapper$$Lambda$1 instance = new ThreadDataMapper$$Lambda$1();

    private ThreadDataMapper$$Lambda$1() {
    }

    public static Creator lambdaFactory$() {
        return instance;
    }

    public ThreadDataModel create(long j, InboxType inboxType, Thread thread, long j2, boolean z, boolean z2) {
        return new AutoValue_ThreadData(j, inboxType, thread, j2, z, z2);
    }
}
