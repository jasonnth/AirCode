package com.airbnb.android.core.messaging;

import com.airbnb.airrequest.AirRequest;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.airrequest.Transformer;
import com.airbnb.airrequest.Transformer.Factory;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.requests.UpdateThreadRequest;

public class MessageStoreArchiveThreadRequest extends UpdateThreadRequest {
    private final InboxType inboxType;
    private final MessageStore messageStore;
    private final MessagingRequestFactory requestFactory;

    public static class TransformerFactory implements Factory {
        public Transformer<?> transformerFor(AirRequest request, AirRequestInitializer initializer) {
            if (request instanceof MessageStoreArchiveThreadRequest) {
                return ((MessageStoreArchiveThreadRequest) request).instrument();
            }
            return null;
        }
    }

    MessageStoreArchiveThreadRequest(MessagingRequestFactory requestFactory2, MessageStore messageStore2, InboxType inboxType2, Thread thread, boolean archive) {
        super(thread, Action.Archived, archive);
        this.requestFactory = requestFactory2;
        this.messageStore = messageStore2;
        this.inboxType = inboxType2;
    }

    /* access modifiers changed from: private */
    public void onSuccess() {
        if (this.state) {
            this.messageStore.markThreadAsArchived(this.thread.getId());
        } else {
            this.requestFactory.expireCacheForThread(this.thread.getId(), this.inboxType);
        }
    }

    /* access modifiers changed from: private */
    public Transformer<?> instrument() {
        return MessageStoreArchiveThreadRequest$$Lambda$1.lambdaFactory$(this);
    }
}
