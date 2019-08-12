package com.airbnb.android.core.messaging;

import android.util.Log;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.Transformer;
import com.airbnb.android.core.analytics.MessagingJitneyLogger;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.requests.ThreadRequest;
import com.airbnb.android.core.responses.ThreadResponse;
import p032rx.Observable;
import retrofit2.Response;

class MessageStoreThreadRequest extends ThreadRequest {
    private static final String TAG = MessageStoreThreadRequest.class.getSimpleName();
    private final MessageStore messageStore;
    private final SyncRequestFactory syncRequestFactory;

    MessageStoreThreadRequest(MessageStore messageStore2, InboxType inboxType, long threadId, SyncRequestFactory syncRequestFactory2, MessagingJitneyLogger jitneyLogger) {
        super(inboxType, threadId, jitneyLogger);
        this.messageStore = messageStore2;
        this.syncRequestFactory = syncRequestFactory2;
    }

    /* access modifiers changed from: private */
    public Observable<AirResponse<ThreadResponse>> handleDatabaseResponse(Observable<AirResponse<ThreadResponse>> webRequest, Thread thread) {
        if (thread == null) {
            return webRequest.map(MessageStoreThreadRequest$$Lambda$1.lambdaFactory$(this));
        }
        if (isSkipCache()) {
            return this.syncRequestFactory.getSyncRequest(this.inboxType).map(MessageStoreThreadRequest$$Lambda$2.lambdaFactory$(this));
        }
        return Observable.just(inboxResponseFromLocal(this, thread));
    }

    /* access modifiers changed from: private */
    public Thread getStoredThread() {
        return this.messageStore.getFullThread(this.threadId);
    }

    public Transformer<ThreadResponse> instrument() {
        return MessageStoreThreadRequest$$Lambda$3.lambdaFactory$(this);
    }

    /* access modifiers changed from: private */
    public AirResponse<ThreadResponse> storeResponse(AirResponse<ThreadResponse> airResponse) {
        ThreadResponse body = (ThreadResponse) airResponse.body();
        if (!(airResponse.isSuccess() && body != null && !body.metadata.isCached())) {
            return airResponse;
        }
        this.messageStore.storeThreadResponse(this.inboxType, body.thread);
        Thread thread = getStoredThread();
        if (thread == null) {
            Log.w(TAG, "Sync failed to store the returned value, returning data from the thread fetch instead");
            thread = body.thread;
        }
        return inboxResponseFromLocal(this, thread);
    }

    /* access modifiers changed from: private */
    public static AirResponse<ThreadResponse> inboxResponseFromLocal(ThreadRequest request, Thread thread) {
        return new AirResponse<>(request, Response.success(new ThreadResponse(thread)));
    }
}
