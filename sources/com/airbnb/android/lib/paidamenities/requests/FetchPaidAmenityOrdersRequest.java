package com.airbnb.android.lib.paidamenities.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.lib.paidamenities.responses.PaidAmenityOrdersResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class FetchPaidAmenityOrdersRequest extends BaseRequestV2<PaidAmenityOrdersResponse> {
    private final String confirmationCode;

    public static FetchPaidAmenityOrdersRequest forConfirmationCode(String confirmationCode2) {
        return new FetchPaidAmenityOrdersRequest(confirmationCode2);
    }

    private FetchPaidAmenityOrdersRequest(String confirmationCode2) {
        this.confirmationCode = confirmationCode2;
    }

    public Type successResponseType() {
        return PaidAmenityOrdersResponse.class;
    }

    public String getPath() {
        return "paid_amenity_orders";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv("confirmation_code", this.confirmationCode).mo7895kv(TimelineRequest.ARG_FORMAT, "for_mobile_detail_view");
    }
}
