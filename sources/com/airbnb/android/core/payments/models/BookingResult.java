package com.airbnb.android.core.payments.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonDeserialize(builder = Builder.class)
public abstract class BookingResult implements Parcelable {

    public static abstract class Builder {
        public abstract BookingResult build();

        @JsonProperty("errors")
        public abstract Builder errors(List<Error> list);

        @JsonProperty("require_zip_code_existing_cc")
        public abstract Builder requireZipCodeExistingCc(boolean z);

        @JsonProperty("reservation_confirmation_code")
        public abstract Builder reservationConfirmationCode(String str);

        @JsonProperty("set_first_time_booking_cookie")
        public abstract Builder setFirstTimeBookingCookie(boolean z);

        @JsonProperty("set_hard_fallback")
        public abstract Builder setHardFallback(boolean z);

        @JsonProperty("set_reservation_requested_cookie")
        public abstract Builder setReservationRequestedCookie(boolean z);

        @JsonProperty("settlement_currency_change")
        public abstract Builder settlementCurrencyChange(boolean z);

        @JsonProperty("success")
        public abstract Builder success(boolean z);
    }

    @JsonDeserialize(builder = Builder.class)
    public static abstract class Error implements Parcelable {

        public static abstract class Builder {
            public abstract Error build();

            @JsonProperty("existing_instrument_id")
            public abstract Builder existingInstrumentId(long j);

            @JsonProperty("field")
            public abstract Builder field(String str);

            @JsonProperty("invalidate_option")
            public abstract Builder invalidateOption(long j);

            @JsonProperty("msg")
            public abstract Builder msg(String str);
        }

        @JsonProperty("existing_instrument_id")
        public abstract long existingInstrumentId();

        @JsonProperty("field")
        public abstract String field();

        @JsonProperty("invalidate_option")
        public abstract long invalidateOption();

        @JsonProperty("msg")
        public abstract String msg();

        public static Builder builder() {
            return new Builder();
        }
    }

    @JsonProperty("errors")
    public abstract List<Error> errors();

    @JsonProperty("require_zip_code_existing_cc")
    public abstract boolean requireZipCodeExistingCc();

    @JsonProperty("reservation_confirmation_code")
    public abstract String reservationConfirmationCode();

    @JsonProperty("set_first_time_booking_cookie")
    public abstract boolean setFirstTimeBookingCookie();

    @JsonProperty("set_hard_fallback")
    public abstract boolean setHardFallback();

    @JsonProperty("set_reservation_requested_cookie")
    public abstract boolean setReservationRequestedCookie();

    @JsonProperty("settlement_currency_change")
    public abstract boolean settlementCurrencyChange();

    @JsonProperty("success")
    public abstract boolean success();

    public static Builder builder() {
        return new Builder();
    }
}
