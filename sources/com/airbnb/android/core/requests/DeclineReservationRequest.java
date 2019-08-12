package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.enums.DeclineReason;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class DeclineReservationRequest extends BaseRequestV2<ReservationResponse> {
    private static final int DECLINE_RESERVATION_STATUS_CODE = 2;
    private final RequestBody requestBody;
    private final long reservationId;

    private static class RequestBody {
        @JsonProperty("decline_block_dates")
        boolean declineBlockDates;
        @JsonProperty("decline_message_to_airbnb")
        String declineMessageToAirbnb;
        @JsonProperty("decline_message_to_guest")
        String declineMessageToGuest;
        @JsonProperty("decline_reason")
        String declineReason;
        @JsonProperty("status_code")
        int statusCode;

        public RequestBody(int statusCode2, String declineReason2) {
            this.statusCode = statusCode2;
            this.declineReason = declineReason2;
        }

        public void messageToGuest(String declineMessageToGuest2) {
            this.declineMessageToGuest = declineMessageToGuest2;
        }

        public void messageToAirnb(String declineMessageToAirbnb2) {
            this.declineMessageToAirbnb = declineMessageToAirbnb2;
        }

        public void blockDates(boolean declineBlockDates2) {
            this.declineBlockDates = declineBlockDates2;
        }
    }

    public DeclineReservationRequest(long id, DeclineReason reason) {
        this.reservationId = id;
        this.requestBody = new RequestBody(2, reason.serverKey);
    }

    public DeclineReservationRequest airbnbMessage(String message) {
        this.requestBody.messageToAirnb(message);
        return this;
    }

    public DeclineReservationRequest guestMessage(String message) {
        this.requestBody.messageToGuest(message);
        return this;
    }

    public DeclineReservationRequest blockDates(boolean blockDates) {
        this.requestBody.blockDates(blockDates);
        return this;
    }

    public String getPath() {
        return "reservations/" + this.reservationId;
    }

    public RequestBody getBody() {
        return this.requestBody;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_mobile_host_rejection").mo7895kv("_intents", "accept_decline_booking");
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Type successResponseType() {
        return ReservationResponse.class;
    }
}
