package com.airbnb.android.lib.requests;

import android.text.TextUtils;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.ReservationCancellationInfoResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class ReservationCancellationInfoRequest extends BaseRequestV2<ReservationCancellationInfoResponse> {
    private final String confirmationCode;

    public ReservationCancellationInfoRequest(String confirmationCode2) {
        Check.argument(!TextUtils.isEmpty(confirmationCode2));
        this.confirmationCode = confirmationCode2;
    }

    public String getPath() {
        return "reservation_cancellation_infos/" + this.confirmationCode;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_mobile_cancellation");
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public Type successResponseType() {
        return ReservationCancellationInfoResponse.class;
    }
}
