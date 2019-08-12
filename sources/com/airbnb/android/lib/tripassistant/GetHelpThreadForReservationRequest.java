package com.airbnb.android.lib.tripassistant;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class GetHelpThreadForReservationRequest extends BaseRequestV2<HelpThreadsResponse> {
    private final String confirmationCode;

    public GetHelpThreadForReservationRequest(String confirmationCode2) {
        this.confirmationCode = confirmationCode2;
    }

    public Type successResponseType() {
        return HelpThreadsResponse.class;
    }

    public String getPath() {
        return "help_threads";
    }

    public long getCacheTimeoutMs() {
        return 604800000;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_context_value_only").mo7895kv("confirmation_code", this.confirmationCode);
    }
}
