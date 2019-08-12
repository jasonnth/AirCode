package com.airbnb.android.core.requests.booking.requestbodies;

import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.models.ReservationDetails.TripType;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Locale;

public class ReservationRequestBody {
    @JsonProperty("allow_alipay_redirect")
    Boolean allowAlipayRedirect;
    @JsonProperty("business_vat_rate_applied")
    Boolean businessVatRateApplied;
    @JsonProperty("check_in")
    String checkIn;
    @JsonProperty("guest_checkin_time_from")
    String checkInHour;
    @JsonProperty("additional_checkin_details_message")
    String checkInMessage;
    @JsonProperty("check_out")
    String checkOut;
    @JsonProperty("guest_id")
    Long guestId;
    @JsonProperty("is_airbnb_credit_excluded")
    Boolean isAirbnbCreditExcluded;
    @JsonProperty("is_bringing_pets")
    Boolean isBringingPets;
    @JsonProperty("listing_id")
    Long listingId;
    @JsonProperty("number_of_adults")
    Integer numberOfAdults;
    @JsonProperty("number_of_children")
    Integer numberOfChildren;
    @JsonProperty("number_of_guests")
    Integer numberOfGuests;
    @JsonProperty("number_of_infants")
    Integer numberOfInfants;
    @JsonProperty("payment_country_code")
    String paymentCountryCode;
    @JsonProperty("reservation_id")
    Long reservationId;
    @JsonProperty("special_offer_id")
    Long specialOfferId;

    public static class Builder {
        /* access modifiers changed from: private */
        public Boolean allowAlipayRedirect;
        /* access modifiers changed from: private */
        public Boolean businessVatRateApplied;
        /* access modifiers changed from: private */
        public String checkIn;
        /* access modifiers changed from: private */
        public String checkInHour;
        /* access modifiers changed from: private */
        public String checkInMessage;
        /* access modifiers changed from: private */
        public String checkOut;
        /* access modifiers changed from: private */
        public Long guestId;
        /* access modifiers changed from: private */
        public Boolean isAirbnbCreditExcluded;
        /* access modifiers changed from: private */
        public Boolean isBringingPets;
        /* access modifiers changed from: private */
        public Long listingId;
        /* access modifiers changed from: private */
        public Integer numberOfAdults;
        /* access modifiers changed from: private */
        public Integer numberOfChildren;
        private Integer numberOfGuests;
        /* access modifiers changed from: private */
        public Integer numberOfInfants;
        /* access modifiers changed from: private */
        public Long reservationId;
        /* access modifiers changed from: private */
        public Long specialOfferId;

        public Builder listingId(Long listingId2) {
            this.listingId = listingId2;
            return this;
        }

        public Builder guestId(Long guestId2) {
            this.guestId = guestId2;
            return this;
        }

        public Builder specialOfferId(Long specialOfferId2) {
            this.specialOfferId = specialOfferId2;
            return this;
        }

        public Builder reservationId(Long reservationId2) {
            this.reservationId = reservationId2;
            return this;
        }

        public Builder checkIn(String checkIn2) {
            this.checkIn = checkIn2;
            return this;
        }

        public Builder checkOut(String checkOut2) {
            this.checkOut = checkOut2;
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

        public Builder isAirbnbCreditExcluded(Boolean isAirbnbCreditExcluded2) {
            this.isAirbnbCreditExcluded = isAirbnbCreditExcluded2;
            return this;
        }

        public Builder businessVatRateApplied(Boolean businessVatRateApplied2) {
            this.businessVatRateApplied = businessVatRateApplied2;
            return this;
        }

        public Builder allowAlipayRedirect(Boolean allowAlipayRedirect2) {
            this.allowAlipayRedirect = allowAlipayRedirect2;
            return this;
        }

        /* access modifiers changed from: private */
        public Integer getTotalGuestCount() {
            if (this.numberOfGuests != null) {
                return this.numberOfGuests;
            }
            if (this.numberOfAdults != null && this.numberOfChildren != null) {
                return Integer.valueOf(this.numberOfAdults.intValue() + this.numberOfChildren.intValue());
            }
            if (this.numberOfAdults != null) {
                return this.numberOfAdults;
            }
            return null;
        }

        public Builder reservationDetails(ReservationDetails reservationDetails) {
            boolean z = true;
            Builder isAirbnbCreditExcluded2 = listingId(reservationDetails.listingId()).guestId(reservationDetails.guestId()).specialOfferId(reservationDetails.specialOfferId()).reservationId(reservationDetails.reservationId()).checkIn(reservationDetails.checkIn().getIsoDateString()).checkOut(reservationDetails.checkOut().getIsoDateString()).numberOfGuests(Integer.valueOf(reservationDetails.numberOfGuests())).numberOfAdults(reservationDetails.numberOfAdults()).numberOfChildren(reservationDetails.numberOfChildren()).numberOfInfants(reservationDetails.numberOfInfants()).isBringingPets(reservationDetails.isBringingPets()).checkInHour(reservationDetails.checkInHour()).isAirbnbCreditExcluded(Boolean.valueOf(reservationDetails.tripType() == TripType.BusinessVerified));
            if (reservationDetails.tripType() != TripType.BusinessVerified) {
                z = false;
            }
            return isAirbnbCreditExcluded2.businessVatRateApplied(Boolean.valueOf(z));
        }

        public ReservationRequestBody build() {
            return new ReservationRequestBody(this);
        }
    }

    private ReservationRequestBody(Builder builder) {
        this.listingId = builder.listingId;
        this.guestId = builder.guestId;
        this.specialOfferId = builder.specialOfferId;
        this.reservationId = builder.reservationId;
        this.checkIn = builder.checkIn;
        this.checkOut = builder.checkOut;
        this.isBringingPets = builder.isBringingPets;
        this.checkInHour = builder.checkInHour;
        this.checkInMessage = builder.checkInMessage;
        this.isAirbnbCreditExcluded = builder.isAirbnbCreditExcluded;
        this.businessVatRateApplied = builder.businessVatRateApplied;
        this.allowAlipayRedirect = builder.allowAlipayRedirect;
        this.paymentCountryCode = Locale.getDefault().getCountry();
        if (builder.numberOfAdults != null) {
            this.numberOfAdults = builder.numberOfAdults;
            this.numberOfChildren = builder.numberOfChildren;
            this.numberOfInfants = builder.numberOfInfants;
            return;
        }
        this.numberOfGuests = builder.getTotalGuestCount();
    }
}
