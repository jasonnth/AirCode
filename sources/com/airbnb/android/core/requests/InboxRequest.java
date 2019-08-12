package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirRequest;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.Transformer;
import com.airbnb.airrequest.Transformer.Factory;
import com.airbnb.android.core.analytics.MessagingJitneyLogger;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.responses.InboxResponse;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class InboxRequest extends BaseRequestV2<InboxResponse> {
    protected static final int FETCH_COUNT = 10;
    static final String FORMAT = "for_messaging_sync";
    private static final String INBOX_FILTER_KEY = "role";
    static final String INBOX_ROLE_KEY = "selected_inbox_type";
    static final String INCLUDE_HELP_THREADS = "include_help_threads";
    static final String INCLUDE_RESTAURANT_THREADS = "include_restaurant_threads";
    static final String INCLUDE_SUPPORT_MESSAGING_THREADS = "include_support_messaging_threads";
    protected final InboxType inboxType;
    private final MessagingJitneyLogger jitneyLogger;
    protected final Thread threadOffset;

    public static class TransformerFactory implements Factory {
        public Transformer<InboxResponse> transformerFor(AirRequest request, AirRequestInitializer initializer) {
            if (request instanceof InboxRequest) {
                return ((InboxRequest) request).instrument();
            }
            return null;
        }
    }

    public static InboxRequest create(InboxType inboxType2, Thread threadOffset2, MessagingJitneyLogger jitneyLogger2) {
        return new InboxRequest(inboxType2, threadOffset2, jitneyLogger2);
    }

    protected InboxRequest(InboxType inboxType2, Thread threadOffset2, MessagingJitneyLogger jitneyLogger2) {
        this.inboxType = inboxType2;
        this.threadOffset = threadOffset2;
        this.jitneyLogger = jitneyLogger2;
    }

    public String getPath() {
        return "threads";
    }

    public Collection<Query> getQueryParams() {
        QueryStrap params = QueryStrap.make().mo7893kv(TimelineRequest.ARG_LIMIT, 10).mo7895kv(TimelineRequest.ARG_FORMAT, FORMAT).mo7897kv("include_mt", shouldIncludeMagicalTripsThreads()).mo7897kv(INCLUDE_HELP_THREADS, shouldIncludeHelpThreads()).mo7897kv(INCLUDE_SUPPORT_MESSAGING_THREADS, shouldIncludeSupportMessagingThreads()).mo7897kv(INCLUDE_RESTAURANT_THREADS, shouldIncludeRestaurantThreads()).mo7895kv(INBOX_FILTER_KEY, this.inboxType.inboxFilter()).mo7895kv(INBOX_ROLE_KEY, this.inboxType.inboxRole);
        if (this.threadOffset != null) {
            params.mo7895kv("updated_at_offset", this.threadOffset.getLastMessageAt().getIsoDateStringUTC());
            params.mo7894kv("thread_id_offset", this.threadOffset.getId());
        }
        return params;
    }

    public long getCacheTimeoutMs() {
        return this.threadOffset == null ? 604800000 : 0;
    }

    public Type successResponseType() {
        return InboxResponse.class;
    }

    public Transformer<InboxResponse> instrument() {
        return MessagingJitneyLogger.createInstrumentationTransformer(InboxRequest$$Lambda$1.lambdaFactory$(this));
    }

    public static boolean shouldIncludeMagicalTripsThreads() {
        return !Trebuchet.launch(TrebuchetKeys.MT_THREAD_FORMAT_KILLSWITCH, false);
    }

    public static boolean shouldIncludeHelpThreads() {
        if (!Trebuchet.launch(TrebuchetKeys.ENABLE_TRIP_ASSISTANT, false) || !Trebuchet.launch(TrebuchetKeys.HELP_THREADS_IN_INBOX, false)) {
            return false;
        }
        return true;
    }

    public static boolean shouldIncludeSupportMessagingThreads() {
        return Trebuchet.launch(TrebuchetKeys.SUPPORT_MESSAGING_IN_INBOX, false);
    }

    public static boolean shouldIncludeRestaurantThreads() {
        return Trebuchet.launch(TrebuchetKeys.PLACE_RESY_SUPPORT_INBOX, false);
    }
}
