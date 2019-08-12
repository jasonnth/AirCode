package com.airbnb.android.core.messaging.p007db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.messaging.InboxUnreadCountManager;
import com.airbnb.android.core.messaging.p007db.ThreadDataModel.Marshal;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Post;
import com.airbnb.android.core.models.Thread;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import com.squareup.sqldelight.SqlDelightStatement;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import p032rx.schedulers.Schedulers;

/* renamed from: com.airbnb.android.core.messaging.db.MessageStoreTableOpenHelper */
public class MessageStoreTableOpenHelper extends SQLiteOpenHelper {
    private static final String FILE_NAME = "message_store.db";
    private static final String TAG = MessageStoreTableOpenHelper.class.getSimpleName();
    private static final int VERSION = 15;
    private final BriteDatabase database = SqlBrite.create().wrapDatabaseHelper(this, Schedulers.m4048io());
    private final InboxUnreadCountManager inboxUnreadCountManager;
    private final Lazy<ThreadDataMapper> threadDataMapper;

    public MessageStoreTableOpenHelper(Context context, Lazy<ThreadDataMapper> threadDataMapper2, InboxUnreadCountManager inboxUnreadCountManager2) {
        super(context, FILE_NAME, null, 15, new LoggingDatabaseErrorHandler(context));
        this.threadDataMapper = threadDataMapper2;
        this.inboxUnreadCountManager = inboxUnreadCountManager2;
    }

    public void onCreate(SQLiteDatabase db) {
        synchronized (this.database) {
            db.execSQL(ThreadDataModel.DROP_TABLE);
            db.execSQL(SyncDataModel.DROP_TABLE);
            db.execSQL(ThreadDataModel.CREATE_TABLE);
            db.execSQL(SyncDataModel.CREATE_TABLE);
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        C0715L.m1189d(TAG, "Upgrading database");
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        C0715L.m1191e(TAG, "Downgrading database");
        onCreate(db);
    }

    public void clearAll() {
        synchronized (this.database) {
            this.database.delete(ThreadDataModel.TABLE_NAME, null, new String[0]);
            this.database.delete(SyncDataModel.TABLE_NAME, null, new String[0]);
        }
    }

    public void clearInbox(InboxType inboxType) {
        synchronized (this.database) {
            SqlDelightStatement deleteForInbox = ThreadDataMapper.THREAD_DATA_FACTORY.delete_for_inbox(inboxType);
            this.database.execute(deleteForInbox.statement, (Object[]) deleteForInbox.args);
            this.database.execute(SyncData.FACTORY.delete_for_inbox(inboxType).statement, (Object[]) deleteForInbox.args);
        }
    }

    public long getSyncSequenceId(InboxType inboxType) {
        long j;
        synchronized (this.database) {
            Cursor cursor = this.database.query(SyncDataModel.SELECT_SYNC_BY_INBOX, inboxType.name());
            try {
                j = cursor.moveToFirst() ? cursor.getLong(0) : 0;
                cursor.close();
            } catch (Throwable th) {
                cursor.close();
                throw th;
            }
        }
        return j;
    }

    public void setSyncDetails(InboxType inboxType, long syncSequenceId, long unreadCount) {
        synchronized (this.database) {
            this.database.insert(SyncDataModel.TABLE_NAME, SyncData.createContentValues(inboxType, syncSequenceId, unreadCount), 5);
        }
    }

    public List<Thread> getThreadsWithOffset(InboxType inboxType, Thread threadOffset, int count) {
        List<Thread> items;
        synchronized (this.database) {
            Cursor cursor = createCursorForThreadsWithOffset(inboxType, threadOffset, count);
            try {
                items = new ArrayList<>(cursor.getCount());
                while (cursor.moveToNext()) {
                    items.add(((ThreadDataMapper) this.threadDataMapper.get()).map(cursor).threadModel());
                }
                cursor.close();
            } catch (Throwable th) {
                cursor.close();
                throw th;
            }
        }
        return items;
    }

    public long getUnreadCount(InboxType inboxType) {
        long j;
        synchronized (this.database) {
            Cursor cursor = this.database.query(SyncDataModel.SELECT_UNREAD_BY_INBOX, inboxType.name());
            try {
                j = cursor.moveToFirst() ? cursor.getLong(0) : -1;
                cursor.close();
            } catch (Throwable th) {
                cursor.close();
                throw th;
            }
        }
        return j;
    }

    private Cursor createCursorForThreadsWithOffset(InboxType inboxType, Thread threadOffset, int count) {
        Cursor query;
        synchronized (this.database) {
            if (threadOffset == null) {
                query = this.database.query(ThreadDataModel.INBOX, inboxType.name(), Integer.toString(count));
            } else {
                String offsetMillis = AirDateTimeConverter.convertToString(threadOffset.getLastMessageAt());
                String offsetId = Long.toString(threadOffset.getId());
                query = this.database.query(ThreadDataModel.INBOX_WITH_OFFSET, inboxType.name(), offsetMillis, offsetMillis, offsetId, Integer.toString(count));
            }
        }
        return query;
    }

    public ThreadData getOldestThreadInInbox(InboxType inboxType) {
        ThreadData threadData;
        synchronized (this.database) {
            Cursor cursor = this.database.query(ThreadDataModel.GET_OLDEST_THREAD, inboxType.name());
            try {
                threadData = cursor.moveToFirst() ? ((ThreadDataMapper) this.threadDataMapper.get()).map(cursor) : null;
                cursor.close();
            } catch (Throwable th) {
                cursor.close();
                throw th;
            }
        }
        return threadData;
    }

    public void insertInboxThreads(InboxType inboxType, List<? extends Thread> threads) {
        synchronized (this.database) {
            for (Thread thread : threads) {
                if (!thread.isArchived()) {
                    insertThread(((ThreadDataMapper) this.threadDataMapper.get()).forThreadInThreadList(inboxType, thread));
                }
            }
        }
    }

    public void insertThreadDetails(InboxType inboxType, Thread thread, boolean isInThreadlist) {
        synchronized (this.database) {
            insertThread(((ThreadDataMapper) this.threadDataMapper.get()).forThread(inboxType, thread).doesContainAllInfo(true).isInThreadlist(isInThreadlist));
        }
    }

    public ThreadData getThread(long threadId) {
        ThreadData threadData;
        synchronized (this.database) {
            Cursor cursor = this.database.query(ThreadDataModel.SELECT_THREAD_BY_ID, Long.toString(threadId));
            threadData = cursor.moveToFirst() ? ((ThreadDataMapper) this.threadDataMapper.get()).map(cursor) : null;
        }
        return threadData;
    }

    public void markThreadAsRead(InboxType inboxType, long threadId) {
        synchronized (this.database) {
            ThreadData threadData = getThread(threadId);
            if (threadData != null && threadData.threadModel().isUnread()) {
                threadData.threadModel().setUnread(false);
                insertThread(((ThreadDataMapper) this.threadDataMapper.get()).forThreadData(threadData));
                decrementInboxCount(threadData.inboxType());
                this.inboxUnreadCountManager.decreaseUnreadCount(inboxType);
            }
        }
    }

    public void markThreadAsArchived(long threadId) {
        synchronized (this.database) {
            ThreadData threadData = getThread(threadId);
            if (threadData != null) {
                deleteThread(threadId);
                if (threadData.threadModel().isUnread()) {
                    decrementInboxCount(threadData.inboxType());
                }
            }
        }
    }

    public void applyThreadUpdates(InboxType inboxType, List<? extends Thread> updates, long syncSequenceId, long unreadCount, List<Long> threadsForRemoval) {
        synchronized (this.database) {
            ThreadData oldestThreadDataInInbox = getOldestThreadInInbox(inboxType);
            Thread oldestThreadInInbox = oldestThreadDataInInbox != null ? oldestThreadDataInInbox.threadModel() : null;
            for (Thread update : updates) {
                applyThreadUpdate(inboxType, update, oldestThreadInInbox);
            }
            for (Long threadId : threadsForRemoval) {
                deleteThread(threadId.longValue());
            }
            setSyncDetails(inboxType, syncSequenceId, unreadCount);
        }
    }

    public void storeOutgoingMessage(long threadId, long offlineId, Post post) {
        synchronized (this.database) {
            ThreadData threadData = getThread(threadId);
            if (threadData != null) {
                threadData.threadModel().addPost(post, true, Long.valueOf(offlineId));
                insertThread(((ThreadDataMapper) this.threadDataMapper.get()).forThreadData(threadData));
            }
        }
    }

    private void decrementInboxCount(InboxType inboxType) {
        SqlDelightStatement decrementUnread = SyncData.FACTORY.decrement_unread_by_inbox(inboxType);
        this.database.execute(decrementUnread.statement, (Object[]) decrementUnread.args);
    }

    private void applyThreadUpdate(InboxType inboxType, Thread update, Thread oldestThreadInInbox) {
        boolean doesContainAllInfo;
        boolean wasPreviouslyInThreadlist;
        boolean isNewerThanOldestThreadInThreadlist;
        boolean isInThreadlist = false;
        synchronized (this.database) {
            ThreadData existingThreadData = getThread(update.getId());
            if (!(existingThreadData == null || existingThreadData.threadModel().getPosts() == null)) {
                update.mergeOlderPosts(existingThreadData.threadModel().getPosts());
            }
            if (existingThreadData == null || existingThreadData.doesContainAllInfo() != Boolean.TRUE.booleanValue()) {
                doesContainAllInfo = false;
            } else {
                doesContainAllInfo = true;
            }
            if (existingThreadData == null || existingThreadData.isInThreadlist() != Boolean.TRUE.booleanValue()) {
                wasPreviouslyInThreadlist = false;
            } else {
                wasPreviouslyInThreadlist = true;
            }
            if (oldestThreadInInbox == null || update.getLastMessageAt().isAfter(oldestThreadInInbox.getLastMessageAt()) || (update.getLastMessageAt().equals(oldestThreadInInbox.getLastMessageAt()) && update.getId() > oldestThreadInInbox.getId())) {
                isNewerThanOldestThreadInThreadlist = true;
            } else {
                isNewerThanOldestThreadInThreadlist = false;
            }
            if (wasPreviouslyInThreadlist || isNewerThanOldestThreadInThreadlist) {
                isInThreadlist = true;
            }
            insertThread(((ThreadDataMapper) this.threadDataMapper.get()).forThread(inboxType, update).doesContainAllInfo(doesContainAllInfo).isInThreadlist(isInThreadlist));
            if (update.isArchived()) {
                markThreadAsArchived(update.getId());
            }
        }
    }

    private void insertThread(Marshal marshal) {
        synchronized (this.database) {
            this.database.insert(ThreadDataModel.TABLE_NAME, marshal.asContentValues(), 5);
        }
    }

    private void deleteThread(long threadId) {
        synchronized (this.database) {
            this.database.delete(ThreadDataModel.TABLE_NAME, "threadId = ?", Long.toString(threadId));
        }
    }
}
