package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.FormUrlRequest;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.facebook.internal.AnalyticsEvents;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class UpdateReservationRequest extends FormUrlRequest<BaseResponse> {
    private String declineReason;
    private final boolean hostAgreedSouthKoreanPreapproval;
    private final long reservationId;
    private final UpdateReservationStatus status;

    private enum UpdateReservationStatus {
        Accepted("accepted"),
        Declined("declined"),
        Cancelled(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_CANCELLED),
        Retry("offer_retry");
        
        private final String mStatus;

        private UpdateReservationStatus(String status) {
            this.mStatus = status;
        }

        public String getStatus() {
            return this.mStatus;
        }
    }

    public static UpdateReservationRequest forAccept(long reservationId2) {
        return new UpdateReservationRequest(UpdateReservationStatus.Accepted, reservationId2, false);
    }

    public static UpdateReservationRequest forAccept(long reservationId2, boolean hostAgreedSouthKoreanPreapproval2) {
        return new UpdateReservationRequest(UpdateReservationStatus.Accepted, reservationId2, hostAgreedSouthKoreanPreapproval2);
    }

    public static UpdateReservationRequest forDecline(long reservationId2) {
        return new UpdateReservationRequest(UpdateReservationStatus.Declined, reservationId2);
    }

    public static UpdateReservationRequest forCancel(long reservationId2) {
        return new UpdateReservationRequest(UpdateReservationStatus.Cancelled, reservationId2);
    }

    public UpdateReservationRequest declineReason(String declineReason2) {
        this.declineReason = declineReason2;
        return this;
    }

    private UpdateReservationRequest(UpdateReservationStatus status2, long reservationId2) {
        this.status = status2;
        this.reservationId = reservationId2;
        this.hostAgreedSouthKoreanPreapproval = false;
    }

    private UpdateReservationRequest(UpdateReservationStatus status2, long reservationId2, boolean hostAgreedSouthKoreanPreapproval2) {
        this.status = status2;
        this.reservationId = reservationId2;
        this.hostAgreedSouthKoreanPreapproval = hostAgreedSouthKoreanPreapproval2;
    }

    public Collection<Query> getQueryParams() {
        QueryStrap params = QueryStrap.make();
        if (this.status == UpdateReservationStatus.Declined) {
            params.mo7895kv("_intents", "accept_decline_reservation");
        }
        return params;
    }

    public String getPath() {
        return "reservations/" + this.reservationId + "/update";
    }

    public QueryStrap getFields() {
        QueryStrap strap = QueryStrap.make().mo7895kv("status", this.status.getStatus());
        if (this.status == UpdateReservationStatus.Declined) {
            if (this.declineReason != null) {
                strap.mo7895kv("decline_reason", this.declineReason);
            }
        } else if (this.status == UpdateReservationStatus.Accepted) {
            strap.mo7895kv("verification", "true");
            if (this.hostAgreedSouthKoreanPreapproval) {
                strap.mo7897kv("host_agreed_korean_booking", this.hostAgreedSouthKoreanPreapproval);
            }
        }
        return strap;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }
}
