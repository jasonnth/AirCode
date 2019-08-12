package com.airbnb.android.core.requests.booking;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.notifications.NotificationPreferencesGroups;
import com.airbnb.android.core.requests.booking.requestbodies.ReservationRequestBody.Builder;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class CreateReservationRequest extends BaseRequestV2<ReservationResponse> {
    private static final String FOR_P4_CHECKOUT = "for_p4_checkout";
    private static final String FOR_P4_CHECKOUT_WITH_PAYMENT_OPTIONS = "for_p4_checkout_with_payment_options";
    private final Boolean allowAlipayRedirect;
    private final String checkInDate;
    private final String checkOutDate;
    private final Long guestId;
    private final Long listingId;
    private final Integer numberOfGuests;
    private final ReservationDetails reservationDetails;
    private final Long specialOfferId;
    private boolean useForP4Checkout;

    public static class DefaultBuilder {
        /* access modifiers changed from: private */
        public String checkIn;
        /* access modifiers changed from: private */
        public String checkOut;
        Long guestId;
        protected Long listingId;
        /* access modifiers changed from: private */
        public Integer numberOfGuests;

        public DefaultBuilder listingId(Long listingId2) {
            this.listingId = listingId2;
            return this;
        }

        public DefaultBuilder guestId(Long guestId2) {
            this.guestId = guestId2;
            return this;
        }

        public DefaultBuilder checkIn(AirDate checkIn2) {
            this.checkIn = checkIn2.getIsoDateString();
            return this;
        }

        public DefaultBuilder checkOut(AirDate checkOut2) {
            this.checkOut = checkOut2.getIsoDateString();
            return this;
        }

        public DefaultBuilder numberOfGuests(Integer numberOfGuests2) {
            this.numberOfGuests = numberOfGuests2;
            return this;
        }

        public CreateReservationRequest build() {
            validate();
            return new CreateReservationRequest(this);
        }

        /* access modifiers changed from: protected */
        public void validate() {
            Check.notNull(this.listingId, "listingId");
            Check.notNull(this.guestId, "guestId");
            Check.notNull(this.checkIn, "checkIn");
            Check.notNull(this.checkOut, "checkOut");
            Check.notNull(this.numberOfGuests, "numberOfGuests");
        }
    }

    public static class SpecialOfferBuilder extends DefaultBuilder {
        /* access modifiers changed from: private */
        public Long specialOfferId;

        public SpecialOfferBuilder listingId(Long listingId) {
            return (SpecialOfferBuilder) super.listingId(listingId);
        }

        public SpecialOfferBuilder guestId(Long guestId) {
            return (SpecialOfferBuilder) super.guestId(guestId);
        }

        public SpecialOfferBuilder specialOfferId(Long specialOfferId2) {
            this.specialOfferId = specialOfferId2;
            return this;
        }

        public CreateReservationRequest build() {
            validate();
            return new CreateReservationRequest(this);
        }

        /* access modifiers changed from: protected */
        public void validate() {
            Check.notNull(this.listingId, "listingId");
            Check.notNull(this.guestId, "guestId");
            Check.notNull(this.specialOfferId, "specialOfferId");
        }
    }

    public CreateReservationRequest(ReservationDetails reservationDetails2) {
        this.useForP4Checkout = false;
        this.reservationDetails = reservationDetails2;
        this.listingId = null;
        this.guestId = null;
        this.checkInDate = null;
        this.checkOutDate = null;
        this.numberOfGuests = null;
        this.specialOfferId = null;
        this.allowAlipayRedirect = Boolean.valueOf(false);
        this.useForP4Checkout = FeatureToggles.useForP4CheckoutFormat();
    }

    public CreateReservationRequest(ReservationDetails reservationDetails2, boolean allowAlipayRedirect2) {
        this.useForP4Checkout = false;
        this.reservationDetails = reservationDetails2;
        this.allowAlipayRedirect = Boolean.valueOf(allowAlipayRedirect2);
        this.listingId = null;
        this.guestId = null;
        this.checkInDate = null;
        this.checkOutDate = null;
        this.numberOfGuests = null;
        this.specialOfferId = null;
    }

    @Deprecated
    private CreateReservationRequest(DefaultBuilder builder) {
        this.useForP4Checkout = false;
        this.listingId = builder.listingId;
        this.guestId = builder.guestId;
        this.checkInDate = builder.checkIn;
        this.checkOutDate = builder.checkOut;
        this.numberOfGuests = builder.numberOfGuests;
        this.specialOfferId = null;
        this.reservationDetails = null;
        this.allowAlipayRedirect = Boolean.valueOf(false);
    }

    @Deprecated
    private CreateReservationRequest(SpecialOfferBuilder builder) {
        this.useForP4Checkout = false;
        this.listingId = builder.listingId;
        this.specialOfferId = builder.specialOfferId;
        this.guestId = builder.guestId;
        this.checkInDate = null;
        this.checkOutDate = null;
        this.numberOfGuests = null;
        this.reservationDetails = null;
        this.allowAlipayRedirect = Boolean.valueOf(false);
    }

    public String getPath() {
        return NotificationPreferencesGroups.RESERVATIONS;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Object getBody() {
        if (this.reservationDetails != null) {
            return new Builder().reservationDetails(this.reservationDetails).allowAlipayRedirect(this.allowAlipayRedirect).build();
        }
        return new Builder().listingId(this.listingId).guestId(this.guestId).checkIn(this.checkInDate).checkOut(this.checkOutDate).numberOfGuests(this.numberOfGuests).specialOfferId(this.specialOfferId).allowAlipayRedirect(this.allowAlipayRedirect).build();
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
