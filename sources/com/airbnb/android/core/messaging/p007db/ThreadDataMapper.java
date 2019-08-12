package com.airbnb.android.core.messaging.p007db;

import android.database.Cursor;
import com.airbnb.android.core.column_adapters.JsonColumnAdapter;
import com.airbnb.android.core.messaging.p007db.ThreadDataModel.Factory;
import com.airbnb.android.core.messaging.p007db.ThreadDataModel.Mapper;
import com.airbnb.android.core.messaging.p007db.ThreadDataModel.Marshal;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Thread;
import com.squareup.sqldelight.ColumnAdapter;
import com.squareup.sqldelight.EnumColumnAdapter;

/* renamed from: com.airbnb.android.core.messaging.db.ThreadDataMapper */
public class ThreadDataMapper {
    public static final ColumnAdapter<InboxType, String> INBOX_COLUMN_ADAPTER = EnumColumnAdapter.create(InboxType.class);
    public static final ColumnAdapter<Thread, byte[]> THREAD_COLUMN_ADAPTER = new JsonColumnAdapter(Thread.class);
    public static final Factory<ThreadData> THREAD_DATA_FACTORY = new Factory<>(ThreadDataMapper$$Lambda$1.lambdaFactory$(), INBOX_COLUMN_ADAPTER, THREAD_COLUMN_ADAPTER);
    public static final Mapper<ThreadData> THREAD_DATA_MAPPER = new Mapper<>(THREAD_DATA_FACTORY);

    /* access modifiers changed from: 0000 */
    public ThreadData map(Cursor cursor) {
        return (ThreadData) THREAD_DATA_MAPPER.map(cursor);
    }

    /* access modifiers changed from: 0000 */
    public Marshal forThreadInThreadList(InboxType inboxType, Thread thread) {
        return forThread(inboxType, thread).isInThreadlist(true).doesContainAllInfo(false);
    }

    /* access modifiers changed from: 0000 */
    public Marshal forThread(InboxType inboxType, Thread thread) {
        return forThreadData(null).threadId(thread.getId()).threadModel(thread).inboxType(inboxType).lastMessageAt(AirDateTimeConverter.convert(thread.getLastMessageAt()).longValue());
    }

    /* access modifiers changed from: 0000 */
    public Marshal forThreadData(ThreadData data) {
        return new Marshal(data, INBOX_COLUMN_ADAPTER, THREAD_COLUMN_ADAPTER);
    }
}
