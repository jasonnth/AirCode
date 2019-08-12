package com.airbnb.android.places.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.places.responses.MeetupActivityResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class MeetupActivityRequest extends BaseRequestV2<MeetupActivityResponse> {
    private static final String FORMAT = "activity_pdp_mobile";

    /* renamed from: id */
    private final long f11168id;

    public static MeetupActivityRequest forId(long id) {
        return new MeetupActivityRequest(id);
    }

    private MeetupActivityRequest(long id) {
        this.f11168id = id;
    }

    public Type successResponseType() {
        return MeetupActivityResponse.class;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, FORMAT);
    }

    public String getPath() {
        return "meetup_activities/" + this.f11168id;
    }
}
