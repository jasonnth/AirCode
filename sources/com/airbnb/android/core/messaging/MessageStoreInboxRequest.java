package com.airbnb.android.core.messaging;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.Transformer;
import com.airbnb.android.core.analytics.MessagingJitneyLogger;
import com.airbnb.android.core.models.InboxMetadata;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.requests.InboxRequest;
import com.airbnb.android.core.responses.InboxResponse;
import java.util.List;
import p032rx.Observable;
import retrofit2.Response;

public class MessageStoreInboxRequest extends InboxRequest {
    private final MessageStore messageStore;
    private final SyncRequestFactory syncRequestFactory;

    MessageStoreInboxRequest(MessageStore messageStore2, InboxType inboxType, Thread threadOffset, SyncRequestFactory syncRequestFactory2, MessagingJitneyLogger jitneyLogger) {
        super(inboxType, threadOffset, jitneyLogger);
        this.messageStore = messageStore2;
        this.syncRequestFactory = syncRequestFactory2;
    }

    /* access modifiers changed from: private */
    public Observable<AirResponse<InboxResponse>> handleDatabaseResponse(Observable<AirResponse<InboxResponse>> webRequest, List<Thread> threads) {
        boolean mustReachServer;
        boolean isFirstPage = true;
        if (isSkipCache() || (threads.isEmpty() && !this.messageStore.containsLastThread(this.inboxType))) {
            mustReachServer = true;
        } else {
            mustReachServer = false;
        }
        if (!mustReachServer) {
            return Observable.just(inboxResponseFromLocal(this, threads));
        }
        if (this.threadOffset != null) {
            isFirstPage = false;
        }
        if (isFirstPage) {
            return this.syncRequestFactory.getSyncRequest(this.inboxType).map(MessageStoreInboxRequest$$Lambda$1.lambdaFactory$(this));
        }
        return webRequest.doOnNext(MessageStoreInboxRequest$$Lambda$2.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void storeResponse(AirResponse<InboxResponse> airResponse) {
        InboxResponse body = (InboxResponse) airResponse.body();
        if (airResponse.isSuccess() && body != null && !body.metadata.isCached()) {
            this.messageStore.storeHistoricalInboxResponse(this.inboxType, this.threadOffset, 10, ((InboxResponse) airResponse.body()).messageThreads);
        }
    }

    /* access modifiers changed from: private */
    public AirResponse<InboxResponse> inboxResponseFromLocal(InboxRequest request, List<Thread> threads) {
        long unreadCount = this.messageStore.getUnreadCount(this.inboxType);
        InboxMetadata metadata = new InboxMetadata();
        metadata.setUnreadCount(this.inboxType.isHostMode(), unreadCount);
        InboxResponse response = new InboxResponse(threads);
        response.inboxMetadata = metadata;
        return new AirResponse<>(request, Response.success(response));
    }

    public Transformer<InboxResponse> instrument() {
        return MessageStoreInboxRequest$$Lambda$3.lambdaFactory$(this);
    }
}
