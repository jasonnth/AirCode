package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirRequest;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.Transformer;
import com.airbnb.airrequest.Transformer.Factory;
import com.airbnb.android.core.analytics.MessagingJitneyLogger;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.responses.ThreadResponse;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class ThreadRequest extends BaseRequestV2<ThreadResponse> {
    private static final String FORMAT = "for_messaging_sync_with_posts";
    protected final InboxType inboxType;
    protected final MessagingJitneyLogger jitneyLogger;
    protected final long threadId;

    public static class TransformerFactory implements Factory {
        public Transformer<ThreadResponse> transformerFor(AirRequest request, AirRequestInitializer initializer) {
            if (request instanceof ThreadRequest) {
                return ((ThreadRequest) request).instrument();
            }
            return null;
        }
    }

    public ThreadRequest(InboxType inboxType2, long threadId2, MessagingJitneyLogger jitneyLogger2) {
        this.inboxType = inboxType2;
        this.threadId = threadId2;
        this.jitneyLogger = jitneyLogger2;
    }

    public String getPath() {
        return "threads/" + this.threadId;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, FORMAT);
    }

    public long getCacheTimeoutMs() {
        return DateHelper.ONE_MONTH_MILLIS;
    }

    public Type successResponseType() {
        return ThreadResponse.class;
    }

    public Transformer<ThreadResponse> instrument() {
        return MessagingJitneyLogger.createInstrumentationTransformer(ThreadRequest$$Lambda$1.lambdaFactory$(this));
    }
}
