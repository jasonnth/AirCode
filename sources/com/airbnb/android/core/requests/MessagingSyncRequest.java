package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirRequest;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.Transformer;
import com.airbnb.airrequest.Transformer.Factory;
import com.airbnb.android.core.analytics.MessagingJitneyLogger;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.responses.CreateMessageResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class MessagingSyncRequest extends BaseRequestV2<MessagingSyncResponse> {
    private static final int LIMIT = 10;
    public final InboxType inboxType;
    /* access modifiers changed from: private */
    public final MessagingJitneyLogger jitneyLogger;
    public final long requestSequenceId;

    public static class TransformerFactory implements Factory {
        public Transformer<CreateMessageResponse> transformerFor(AirRequest request, AirRequestInitializer initializer) {
            if (request instanceof MessagingSyncRequest) {
                return MessagingJitneyLogger.createInstrumentationTransformer(MessagingSyncRequest$TransformerFactory$$Lambda$1.lambdaFactory$((MessagingSyncRequest) request));
            }
            return null;
        }
    }

    public static MessagingSyncRequest create(InboxType inboxType2, long requestSequenceId2, MessagingJitneyLogger jitneyLogger2) {
        return new MessagingSyncRequest(inboxType2, requestSequenceId2, jitneyLogger2);
    }

    private MessagingSyncRequest(InboxType inboxType2, long requestSequenceId2, MessagingJitneyLogger jitneyLogger2) {
        this.inboxType = inboxType2;
        this.requestSequenceId = requestSequenceId2;
        this.jitneyLogger = jitneyLogger2;
    }

    public String getPath() {
        return "messaging_syncs";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("sequence_id", this.requestSequenceId).mo7893kv(TimelineRequest.ARG_LIMIT, 10).mo7897kv("include_mt", InboxRequest.shouldIncludeMagicalTripsThreads()).mo7897kv("include_help_threads", InboxRequest.shouldIncludeHelpThreads()).mo7897kv("include_support_messaging_threads", InboxRequest.shouldIncludeSupportMessagingThreads()).mo7897kv("include_restaurant_threads", InboxRequest.shouldIncludeRestaurantThreads()).mo7895kv("selected_inbox_type", this.inboxType.inboxRole);
    }

    public Type successResponseType() {
        return MessagingSyncResponse.class;
    }
}
