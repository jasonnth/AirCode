package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.cancellation.host.HostCancellationParams;
import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Query;

public class DeleteReservationRequest extends BaseRequestV2<ReservationResponse> {
    private String additionalInfo;
    private String message;
    private final String reason;
    private long reservationId = -1;
    private String subReason;

    public DeleteReservationRequest(long reservationId2, String reason2, String message2) {
        Check.validId(reservationId2);
        this.reservationId = reservationId2;
        this.reason = reason2;
        this.message = message2;
    }

    public DeleteReservationRequest(long reservationId2, HostCancellationParams cancellationParams) {
        this.reservationId = reservationId2;
        this.reason = cancellationParams.reason();
        this.subReason = cancellationParams.subReason();
        this.message = cancellationParams.message();
        this.additionalInfo = cancellationParams.additionalInfo();
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "host_cancellation").mo7895kv("_intents", "cancel_reservation");
    }

    public String getBody() {
        try {
            return new JSONObject().put(CancellationAnalytics.VALUE_PAGE_REASON, this.reason).put("sub_reason", this.subReason).put("message", this.message).put("additional_info", this.additionalInfo).toString();
        } catch (JSONException e) {
            BugsnagWrapper.notify((Throwable) e);
            return "";
        }
    }

    public String getPath() {
        return "reservations/" + this.reservationId;
    }

    public Type successResponseType() {
        return ReservationResponse.class;
    }

    public Strap getHeaders() {
        return Strap.make().mo11639kv(ApiRequestHeadersInterceptor.HEADER_METHOD_OVERRIDE, "DELETE");
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }
}
