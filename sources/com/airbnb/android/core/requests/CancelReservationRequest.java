package com.airbnb.android.core.requests;

import android.text.TextUtils;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.cancellation.CancellationData;
import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.airbnb.android.core.responses.CancelReservationResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;
import p032rx.Observer;
import retrofit2.Query;

public class CancelReservationRequest extends BaseRequestV2<CancelReservationResponse> {
    private final CancellationData cancellationData;
    private final String confirmationCode;
    private final boolean isHost;
    private final boolean recoverReservation;
    private final long reservationId;

    @Deprecated
    public CancelReservationRequest(long reservationId2, boolean isHost2, BaseRequestListener<CancelReservationResponse> listener) {
        withListener((Observer) listener);
        this.isHost = isHost2;
        this.reservationId = reservationId2;
        this.confirmationCode = null;
        this.cancellationData = null;
        this.recoverReservation = false;
    }

    public CancelReservationRequest(CancellationData cancellationData2) {
        this.cancellationData = cancellationData2;
        this.confirmationCode = cancellationData2.confirmationCode();
        this.isHost = cancellationData2.isHost();
        this.reservationId = 0;
        this.recoverReservation = false;
    }

    public CancelReservationRequest(String confirmationCode2) {
        this.confirmationCode = confirmationCode2;
        this.recoverReservation = true;
        this.reservationId = 0;
        this.isHost = false;
        this.cancellationData = null;
    }

    public String getPath() {
        if (this.confirmationCode != null) {
            return "reservations/" + this.confirmationCode;
        }
        return "reservations/" + this.reservationId;
    }

    public String getBody() {
        JSONObject json = new JSONObject();
        if (this.recoverReservation) {
            try {
                json.put("recover_reservation", true);
            } catch (JSONException e) {
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException(e));
            }
            return json.toString();
        } else if (this.cancellationData == null || TextUtils.isEmpty(this.cancellationData.refundAmount())) {
            return null;
        } else {
            try {
                json.put("message", this.cancellationData.message());
                json.put(CancellationAnalytics.VALUE_PAGE_REASON, this.cancellationData.cancellationReason().getReasonId() + "");
                json.put("additional_info", this.cancellationData.otherReason());
                json.put("refund_amount", this.cancellationData.refundAmount());
            } catch (JSONException e2) {
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException(e2));
            }
            return json.toString();
        }
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, this.isHost ? "host_cancellation" : "guest_cancellation").mo7895kv("_intents", "cancel_reservation");
    }

    public Strap getHeaders() {
        return Strap.make().mo11639kv(ApiRequestHeadersInterceptor.HEADER_METHOD_OVERRIDE, "DELETE");
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return CancelReservationResponse.class;
    }
}
