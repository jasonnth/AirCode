package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.ReservationAlertResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import p032rx.Observer;
import retrofit2.Query;

public class GetReservationAlertsRequest extends BaseRequestV2<ReservationAlertResponse> {
    private final Strap params;

    public static GetReservationAlertsRequest forConfirmationCode(String code, BaseRequestListener<ReservationAlertResponse> listener) {
        return new GetReservationAlertsRequest(new Strap().mo11639kv("confirmation_code", code), listener);
    }

    public static GetReservationAlertsRequest forThreadId(long threadId, BaseRequestListener<ReservationAlertResponse> listener) {
        return new GetReservationAlertsRequest(new Strap().mo11638kv("thread_id", threadId), listener);
    }

    private GetReservationAlertsRequest(Strap params2, BaseRequestListener<ReservationAlertResponse> listener) {
        withListener((Observer) listener);
        this.params = params2;
    }

    public String getPath() {
        return "reservation_alerts";
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.params);
    }

    public Type successResponseType() {
        return ReservationAlertResponse.class;
    }
}
