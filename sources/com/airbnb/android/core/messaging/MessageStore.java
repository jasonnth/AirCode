package com.airbnb.android.core.messaging;

import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.messaging.p007db.MessageStoreTableOpenHelper;
import com.airbnb.android.core.messaging.p007db.ThreadData;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Post;
import com.airbnb.android.core.models.Thread;
import dagger.Lazy;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MessageStore {
    private static final String TAG = MessageStore.class.getSimpleName();
    private final Set<InboxType> hasAllThreadsForInboxType = new HashSet();
    private final Lazy<MessageStoreTableOpenHelper> tableHelper;

    public MessageStore(Lazy<MessageStoreTableOpenHelper> messageStoreTableOpenHelper) {
        this.tableHelper = messageStoreTableOpenHelper;
    }

    public void clearAll() {
        this.hasAllThreadsForInboxType.clear();
        ((MessageStoreTableOpenHelper) this.tableHelper.get()).clearAll();
    }

    public void close() {
        ((MessageStoreTableOpenHelper) this.tableHelper.get()).close();
    }

    /* access modifiers changed from: 0000 */
    public boolean containsLastThread(InboxType inboxType) {
        return this.hasAllThreadsForInboxType.contains(inboxType);
    }

    /* access modifiers changed from: 0000 */
    public void storeHistoricalInboxResponse(InboxType inboxType, Thread threadOffset, int requestCount, List<Thread> threads) {
        if (ThreadData.matchesThreadIdAndTimestamp(((MessageStoreTableOpenHelper) this.tableHelper.get()).getOldestThreadInInbox(inboxType), threadOffset)) {
            ((MessageStoreTableOpenHelper) this.tableHelper.get()).insertInboxThreads(inboxType, threads);
            if (requestCount > threads.size()) {
                this.hasAllThreadsForInboxType.add(inboxType);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public List<Thread> getInbox(InboxType inboxType, Thread threadOffset, int count) {
        return ((MessageStoreTableOpenHelper) this.tableHelper.get()).getThreadsWithOffset(inboxType, threadOffset, count);
    }

    /* access modifiers changed from: 0000 */
    public void storeThreadResponse(InboxType inboxType, Thread thread) {
        boolean dataIsNewerThanThreadList;
        boolean isInThreadlist = true;
        ThreadData existingData = ((MessageStoreTableOpenHelper) this.tableHelper.get()).getThread(thread.getId());
        if (existingData == null || existingData.isInThreadlist() != Boolean.TRUE.booleanValue() || !existingData.threadModel().getLastMessageAt().isBefore(thread.getLastMessageAt())) {
            dataIsNewerThanThreadList = false;
        } else {
            dataIsNewerThanThreadList = true;
        }
        if (dataIsNewerThanThreadList) {
            C0715L.m1198w(TAG, "Thread was fetched with newer data than we have, cannot store it");
            return;
        }
        if (existingData == null || existingData.isInThreadlist() != Boolean.TRUE.booleanValue()) {
            isInThreadlist = false;
        }
        ((MessageStoreTableOpenHelper) this.tableHelper.get()).insertThreadDetails(inboxType, thread, isInThreadlist);
    }

    /* access modifiers changed from: 0000 */
    public Thread getFullThread(long threadId) {
        ThreadData threadData = ((MessageStoreTableOpenHelper) this.tableHelper.get()).getThread(threadId);
        if (threadData == null || threadData.doesContainAllInfo() != Boolean.TRUE.booleanValue()) {
            return null;
        }
        return threadData.threadModel();
    }

    /* access modifiers changed from: 0000 */
    public void markThreadAsRead(InboxType inboxType, long threadId) {
        ((MessageStoreTableOpenHelper) this.tableHelper.get()).markThreadAsRead(inboxType, threadId);
    }

    /* access modifiers changed from: 0000 */
    public void markThreadAsArchived(long threadId) {
        ((MessageStoreTableOpenHelper) this.tableHelper.get()).markThreadAsArchived(threadId);
    }

    /* access modifiers changed from: 0000 */
    public long getSyncSequenceId(InboxType inboxType) {
        return ((MessageStoreTableOpenHelper) this.tableHelper.get()).getSyncSequenceId(inboxType);
    }

    /* access modifiers changed from: 0000 */
    public long getUnreadCount(InboxType inboxType) {
        return ((MessageStoreTableOpenHelper) this.tableHelper.get()).getUnreadCount(inboxType);
    }

    /* access modifiers changed from: 0000 */
    public void storeInitialSync(InboxType inboxType, long newSyncSequenceId, long unreadCount, List<Thread> initialThreads) {
        this.hasAllThreadsForInboxType.remove(inboxType);
        ((MessageStoreTableOpenHelper) this.tableHelper.get()).clearInbox(inboxType);
        ((MessageStoreTableOpenHelper) this.tableHelper.get()).insertInboxThreads(inboxType, initialThreads);
        ((MessageStoreTableOpenHelper) this.tableHelper.get()).setSyncDetails(inboxType, newSyncSequenceId, unreadCount);
    }

    /* access modifiers changed from: 0000 */
    public void storeSync(InboxType inboxType, long oldSyncSequenceId, long newSyncSequenceId, long unreadCount, List<? extends Thread> updates, List<Long> threadsForRemoval) {
        if (getSyncSequenceId(inboxType) != oldSyncSequenceId) {
            C0715L.m1198w(TAG, "Message update came in with an incorrect sequence ID");
        } else {
            ((MessageStoreTableOpenHelper) this.tableHelper.get()).applyThreadUpdates(inboxType, updates, newSyncSequenceId, unreadCount, threadsForRemoval);
        }
    }

    /* access modifiers changed from: 0000 */
    public void storeOutgoingMessage(long threadId, long offlineId, Post post) {
        ((MessageStoreTableOpenHelper) this.tableHelper.get()).storeOutgoingMessage(threadId, offlineId, post);
    }
}
