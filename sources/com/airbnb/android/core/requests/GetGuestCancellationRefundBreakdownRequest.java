package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class GetGuestCancellationRefundBreakdownRequest extends BaseRequestV2<ReservationResponse> {
    private final String confirmationCode;

    public GetGuestCancellationRefundBreakdownRequest(String confirmationCode2) {
        this.confirmationCode = confirmationCode2;
    }

    public String getPath() {
        return "reservations/" + this.confirmationCode;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_guest_cancellation_refund_breakdown");
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public Type successResponseType() {
        return ReservationResponse.class;
    }
}
