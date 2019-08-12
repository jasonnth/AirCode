package com.airbnb.android.core.requests.booking;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class UpdateReservationRequest extends BaseRequestV2<ReservationResponse> {
    private static final String FOR_P4_CHECKOUT = "for_p4_checkout";
    private static final String FOR_P4_CHECKOUT_WITH_PAYMENT_OPTIONS = "for_p4_checkout_with_payment_options";
    private String checkIn;
    private String checkInHour;
    private String checkInMessage;
    private String checkOut;
    private Boolean isBringingPets;
    private Integer numberOfAdults;
    private Integer numberOfChildren;
    private Integer numberOfGuests;
    private Integer numberOfInfants;
    protected Long reservationId;
    public boolean useForP4Checkout;

    public static class Builder {
        /* access modifiers changed from: private */
        public String checkIn;
        /* access modifiers changed from: private */
        public String checkInHour;
        /* access modifiers changed from: private */
        public String checkInMessage;
        /* access modifiers changed from: private */
        public String checkOut;
        /* access modifiers changed from: private */
        public Boolean isBringingPets;
        /* access modifiers changed from: private */
        public Integer numberOfAdults;
        /* access modifiers changed from: private */
        public Integer numberOfChildren;
        /* access modifiers changed from: private */
        public Integer numberOfGuests;
        /* access modifiers changed from: private */
        public Integer numberOfInfants;
        /* access modifiers changed from: private */
        public long reservationId;

        public Builder reservationId(long reservationId2) {
            this.reservationId = reservationId2;
            return this;
        }

        public Builder checkIn(AirDate checkIn2) {
            this.checkIn = checkIn2.getIsoDateString();
            return this;
        }

        public Builder checkOut(AirDate checkOut2) {
            this.checkOut = checkOut2.getIsoDateString();
            return this;
        }

        public Builder numberOfGuests(Integer numberOfGuests2) {
            this.numberOfGuests = numberOfGuests2;
            return this;
        }

        public Builder numberOfAdults(Integer numberOfAdults2) {
            this.numberOfAdults = numberOfAdults2;
            return this;
        }

        public Builder numberOfChildren(Integer numberOfChildren2) {
            this.numberOfChildren = numberOfChildren2;
            return this;
        }

        public Builder numberOfInfants(Integer numberOfInfants2) {
            this.numberOfInfants = numberOfInfants2;
            return this;
        }

        public Builder isBringingPets(Boolean isBringingPets2) {
            this.isBringingPets = isBringingPets2;
            return this;
        }

        public Builder checkInHour(String checkInHour2) {
            this.checkInHour = checkInHour2;
            return this;
        }

        public Builder checkInMessage(String checkInMessage2) {
            this.checkInMessage = checkInMessage2;
            return this;
        }

        public UpdateReservationRequest build() {
            validate();
            return new UpdateReservationRequest(this);
        }

        /* access modifiers changed from: protected */
        public void validate() {
            Check.notNull(Long.valueOf(this.reservationId), "reservationId");
        }
    }

    protected UpdateReservationRequest() {
        this.useForP4Checkout = false;
    }

    private UpdateReservationRequest(Builder builder) {
        this.useForP4Checkout = false;
        this.reservationId = Long.valueOf(builder.reservationId);
        this.checkIn = builder.checkIn;
        this.checkOut = builder.checkOut;
        this.numberOfGuests = builder.numberOfGuests;
        this.numberOfAdults = builder.numberOfAdults;
        this.numberOfChildren = builder.numberOfChildren;
        this.numberOfInfants = builder.numberOfInfants;
        this.isBringingPets = builder.isBringingPets;
        this.checkInHour = builder.checkInHour;
        this.checkInMessage = builder.checkInMessage;
    }

    public String getPath() {
        return "reservations/" + this.reservationId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Object getBody() {
        return new com.airbnb.android.core.requests.booking.requestbodies.ReservationRequestBody.Builder().reservationId(this.reservationId).checkIn(this.checkIn).checkOut(this.checkOut).numberOfGuests(this.numberOfGuests).numberOfAdults(this.numberOfAdults).numberOfChildren(this.numberOfChildren).numberOfInfants(this.numberOfInfants).isBringingPets(this.isBringingPets).checkInHour(this.checkInHour).checkInMessage(this.checkInMessage).build();
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, this.useForP4Checkout ? FOR_P4_CHECKOUT : FOR_P4_CHECKOUT_WITH_PAYMENT_OPTIONS);
    }

    public AirResponse<ReservationResponse> transformResponse(AirResponse<ReservationResponse> response) {
        if (!this.useForP4Checkout) {
            ReservationResponse reservationResponse = (ReservationResponse) response.body();
            reservationResponse.paymentInstruments = PaymentOption.toValidPaymentInstruments(reservationResponse.reservation.getPaymentOptions());
        }
        return response;
    }

    public Type successResponseType() {
        return ReservationResponse.class;
    }
}
