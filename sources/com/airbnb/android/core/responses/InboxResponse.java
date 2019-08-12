package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.InboxMetadata;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.requests.AirRequestFactory.Provider;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.List;

public class InboxResponse extends BaseResponse implements Provider<Thread> {
    @JsonProperty("metadata")
    public InboxMetadata inboxMetadata;
    @JsonProperty("threads")
    public List<Thread> messageThreads;

    public InboxResponse() {
    }

    public InboxResponse(List<Thread> messageThreads2) {
        this.messageThreads = messageThreads2;
    }

    public Collection<Thread> provide() {
        return this.messageThreads;
    }
}
