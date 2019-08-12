package com.airbnb.android.core.messaging;

import android.os.Handler;
import android.os.Looper;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.core.analytics.MessagingJitneyLogger;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.MessagingSyncs;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.requests.MessagingSyncRequest;
import com.airbnb.android.core.requests.MessagingSyncResponse;
import com.airbnb.android.core.utils.Check;
import com.google.common.collect.Maps;
import com.squareup.otto.Bus;
import java.util.List;
import java.util.Map;
import p032rx.Observable;
import p032rx.Observer;
import p032rx.subjects.ReplaySubject;

public class SyncRequestFactory {
    private final AirRequestInitializer airRequestInitializer;
    private final Bus bus;
    private final MessagingJitneyLogger jitneyLogger;
    private final MessageStore messageStore;
    private final Handler syncEventHandler;
    private final Map<InboxType, Observable<?>> syncRequests = Maps.newEnumMap(InboxType.class);

    public SyncRequestFactory(MessageStore messageStore2, AirRequestInitializer airRequestInitializer2, Bus bus2, MessagingJitneyLogger jitneyLogger2) {
        this.messageStore = messageStore2;
        this.airRequestInitializer = airRequestInitializer2;
        this.bus = bus2;
        this.jitneyLogger = jitneyLogger2;
        this.syncEventHandler = new Handler(Looper.getMainLooper());
    }

    /* access modifiers changed from: 0000 */
    public Observable<?> getSyncRequest(InboxType inboxType) {
        Observable<?> observable;
        synchronized (this.syncRequests) {
            if (!this.syncRequests.containsKey(inboxType)) {
                this.syncRequests.put(inboxType, createSyncRequest(inboxType));
            }
            observable = (Observable) this.syncRequests.get(inboxType);
        }
        return observable;
    }

    private Observable<?> createSyncRequest(InboxType inboxType) {
        MessagingSyncRequest request = MessagingSyncRequest.create(inboxType, this.messageStore.getSyncSequenceId(inboxType), this.jitneyLogger);
        Observable<MessagingSyncResponse> webRequest = this.airRequestInitializer.adapt(request).map(SyncRequestFactory$$Lambda$1.lambdaFactory$()).doOnNext(SyncRequestFactory$$Lambda$2.lambdaFactory$(this, request)).doOnError(SyncRequestFactory$$Lambda$3.lambdaFactory$(this, request));
        ReplaySubject<MessagingSyncResponse> syncSubject = ReplaySubject.create();
        webRequest.subscribe((Observer<? super T>) syncSubject);
        return syncSubject;
    }

    /* access modifiers changed from: private */
    public void handleSyncResponse(InboxType inboxType, long requestSequenceId, MessagingSyncResponse response) {
        storeResponse(inboxType, requestSequenceId, response.getSync());
        emitSyncEvent(inboxType, response.getSync());
        removeSyncRequest(inboxType);
    }

    /* access modifiers changed from: private */
    public void removeSyncRequest(InboxType inboxType) {
        synchronized (this.syncRequests) {
            this.syncRequests.remove(inboxType);
        }
    }

    private void storeResponse(InboxType inboxType, long requestSequenceId, MessagingSyncs sync) {
        if (sync.shouldReset()) {
            List<Thread> initalInbox = (List) Check.notNull(sync.getThreadsForPartialUpdate());
            this.messageStore.storeInitialSync(inboxType, sync.getCurrentSequenceId(), (long) sync.getUnreadCount(), initalInbox);
            return;
        }
        InboxType inboxType2 = inboxType;
        long j = requestSequenceId;
        this.messageStore.storeSync(inboxType2, j, sync.getCurrentSequenceId(), (long) sync.getUnreadCount(), (List) Check.notNull(sync.getThreadsForUpdate()), (List) Check.notNull(sync.getThreadsForRemoval()));
    }

    private void emitSyncEvent(InboxType inboxType, MessagingSyncs sync) {
        if (!(!sync.hasAnyThreadUpdates())) {
            this.syncEventHandler.post(SyncRequestFactory$$Lambda$4.lambdaFactory$(this, inboxType, sync));
        }
    }
}
