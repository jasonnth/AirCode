package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class ReservationRequest extends BaseRequestV2<ReservationResponse> {
    private String confirmationCode;
    private final String format;
    private long reservationId = -1;

    public enum Format {
        HostRejection("for_mobile_host_rejection"),
        Guest("for_mobile_guest"),
        Host("for_mobile_host"),
        ReviewRatings("for_mobile_host_with_review_ratings");
        
        public final String serverKey;

        private Format(String serverKey2) {
            this.serverKey = serverKey2;
        }
    }

    public static ReservationRequest forConfirmationCode(String confirmationCode2, Format format2) {
        return new ReservationRequest(confirmationCode2, format2.serverKey);
    }

    public static ReservationRequest forReservationId(long reservationId2, Format format2) {
        return new ReservationRequest(reservationId2, format2.serverKey);
    }

    public static ReservationRequest forChargeAttempted(long reservationId2) {
        return new ReservationRequest(reservationId2, "charge_attempted");
    }

    private ReservationRequest(long reservationId2, String format2) {
        this.reservationId = reservationId2;
        this.format = format2;
    }

    private ReservationRequest(String confirmationCode2, String format2) {
        this.confirmationCode = confirmationCode2;
        this.format = format2;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, this.format);
    }

    public String getPath() {
        if (this.confirmationCode != null) {
            return "reservations/" + this.confirmationCode;
        }
        if (this.reservationId != -1) {
            return "reservations/" + this.reservationId;
        }
        return "reservations/";
    }

    public long getCacheTimeoutMs() {
        return DateHelper.ONE_MONTH_MILLIS;
    }

    public Type successResponseType() {
        return ReservationResponse.class;
    }
}
