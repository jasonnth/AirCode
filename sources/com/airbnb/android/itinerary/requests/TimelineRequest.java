package com.airbnb.android.itinerary.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.itinerary.responses.TimelineResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class TimelineRequest extends BaseRequestV2<TimelineResponse> {
    public static final String ARG_EXCLUDE_FREE_TIME = "exclude_free_time";
    public static final String ARG_FORMAT = "_format";
    public static final String ARG_LIMIT = "_limit";
    public static final String ARG_OFFSET = "_offset";
    public static final String ARG_PENDING = "pending";
    public static final int DEFAULT_LIMIT = 10;
    public static final int DEFAULT_OFFSET = 0;
    public static final String FORMAT_BUNDLE = "for_unbundled_itinerary";
    public static final String FORMAT_PENDING = "for_pending";
    private final String format;
    private final boolean isPending;
    private final int limit;
    private final int offset;

    public static TimelineRequest newInstance(String format2, boolean isPending2, int offset2, int limit2) {
        return new TimelineRequest(format2, isPending2, offset2, limit2);
    }

    private TimelineRequest(String format2, boolean isPending2, int offset2, int limit2) {
        this.format = format2;
        this.isPending = isPending2;
        this.offset = offset2;
        this.limit = limit2;
    }

    public Type successResponseType() {
        return TimelineResponse.class;
    }

    public String getPath() {
        return "trip_schedules";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) Strap.make().mo11639kv(ARG_FORMAT, this.format).mo11640kv("pending", this.isPending).mo11637kv(ARG_OFFSET, this.offset).mo11637kv(ARG_LIMIT, this.limit).mo11640kv(ARG_EXCLUDE_FREE_TIME, !FeatureToggles.showSuggestionsInNativeItinerary()));
    }
}
