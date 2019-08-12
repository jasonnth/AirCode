package com.airbnb.android.core.messaging.p007db;

import com.airbnb.android.core.messaging.p007db.SyncDataModel.Creator;
import com.airbnb.android.core.models.InboxType;

/* renamed from: com.airbnb.android.core.messaging.db.SyncData$$Lambda$1 */
final /* synthetic */ class SyncData$$Lambda$1 implements Creator {
    private static final SyncData$$Lambda$1 instance = new SyncData$$Lambda$1();

    private SyncData$$Lambda$1() {
    }

    public static Creator lambdaFactory$() {
        return instance;
    }

    public SyncDataModel create(InboxType inboxType, long j, long j2) {
        return new AutoValue_SyncData(inboxType, j, j2);
    }
}
