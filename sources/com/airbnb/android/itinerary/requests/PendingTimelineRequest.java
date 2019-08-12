package com.airbnb.android.itinerary.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.responses.TimelineResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class PendingTimelineRequest extends BaseRequestV2<TimelineResponse> {
    private final String format;
    private final boolean isPending;

    public static PendingTimelineRequest newInstance(String format2, boolean isPending2) {
        return new PendingTimelineRequest(format2, isPending2);
    }

    private PendingTimelineRequest(String format2, boolean isPending2) {
        this.format = format2;
        this.isPending = isPending2;
    }

    public Type successResponseType() {
        return TimelineResponse.class;
    }

    public String getPath() {
        return "trip_schedules";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) Strap.make().mo11639kv(TimelineRequest.ARG_FORMAT, this.format).mo11640kv("pending", this.isPending));
    }
}
