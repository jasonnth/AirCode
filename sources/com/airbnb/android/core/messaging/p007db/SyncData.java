package com.airbnb.android.core.messaging.p007db;

import android.content.ContentValues;
import com.airbnb.android.core.messaging.p007db.SyncDataModel.Factory;
import com.airbnb.android.core.messaging.p007db.SyncDataModel.Marshal;
import com.airbnb.android.core.models.InboxType;

/* renamed from: com.airbnb.android.core.messaging.db.SyncData */
abstract class SyncData implements SyncDataModel {
    public static final Factory<SyncData> FACTORY = new Factory<>(SyncData$$Lambda$1.lambdaFactory$(), ThreadDataMapper.INBOX_COLUMN_ADAPTER);

    SyncData() {
    }

    static ContentValues createContentValues(InboxType inboxType, long syncSequenceId, long unreadCount) {
        return new Marshal(null, ThreadDataMapper.INBOX_COLUMN_ADAPTER).inboxType(inboxType).unreadCount(unreadCount).syncSequenceId(syncSequenceId).asContentValues();
    }
}
