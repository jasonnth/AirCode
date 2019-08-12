package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.InquiryResponse;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class InquiryRequest extends BaseRequestV2<InquiryResponse> {
    protected final long threadId;

    public InquiryRequest(long threadId2) {
        this.threadId = threadId2;
    }

    public String getPath() {
        return "threads/" + this.threadId;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_mobile_host_reservation_object");
    }

    public long getCacheTimeoutMs() {
        return DateHelper.ONE_MONTH_MILLIS;
    }

    public Type successResponseType() {
        return InquiryResponse.class;
    }
}
