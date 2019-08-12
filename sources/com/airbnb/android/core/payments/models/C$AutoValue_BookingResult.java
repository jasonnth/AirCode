package com.airbnb.android.core.payments.models;

import com.airbnb.android.core.payments.models.BookingResult.Error;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/* renamed from: com.airbnb.android.core.payments.models.$AutoValue_BookingResult reason: invalid class name */
abstract class C$AutoValue_BookingResult extends BookingResult {
    private final List<Error> errors;
    private final boolean requireZipCodeExistingCc;
    private final String reservationConfirmationCode;
    private final boolean setFirstTimeBookingCookie;
    private final boolean setHardFallback;
    private final boolean setReservationRequestedCookie;
    private final boolean settlementCurrencyChange;
    private final boolean success;

    /* renamed from: com.airbnb.android.core.payments.models.$AutoValue_BookingResult$Builder */
    static final class Builder extends com.airbnb.android.core.payments.models.BookingResult.Builder {
        private List<Error> errors;
        private Boolean requireZipCodeExistingCc;
        private String reservationConfirmationCode;
        private Boolean setFirstTimeBookingCookie;
        private Boolean setHardFallback;
        private Boolean setReservationRequestedCookie;
        private Boolean settlementCurrencyChange;
        private Boolean success;

        Builder() {
        }

        public com.airbnb.android.core.payments.models.BookingResult.Builder success(boolean success2) {
            this.success = Boolean.valueOf(success2);
            return this;
        }

        public com.airbnb.android.core.payments.models.BookingResult.Builder errors(List<Error> errors2) {
            this.errors = errors2;
            return this;
        }

        public com.airbnb.android.core.payments.models.BookingResult.Builder settlementCurrencyChange(boolean settlementCurrencyChange2) {
            this.settlementCurrencyChange = Boolean.valueOf(settlementCurrencyChange2);
            return this;
        }

        public com.airbnb.android.core.payments.models.BookingResult.Builder setHardFallback(boolean setHardFallback2) {
            this.setHardFallback = Boolean.valueOf(setHardFallback2);
            return this;
        }

        public com.airbnb.android.core.payments.models.BookingResult.Builder requireZipCodeExistingCc(boolean requireZipCodeExistingCc2) {
            this.requireZipCodeExistingCc = Boolean.valueOf(requireZipCodeExistingCc2);
            return this;
        }

        public com.airbnb.android.core.payments.models.BookingResult.Builder setFirstTimeBookingCookie(boolean setFirstTimeBookingCookie2) {
            this.setFirstTimeBookingCookie = Boolean.valueOf(setFirstTimeBookingCookie2);
            return this;
        }

        public com.airbnb.android.core.payments.models.BookingResult.Builder setReservationRequestedCookie(boolean setReservationRequestedCookie2) {
            this.setReservationRequestedCookie = Boolean.valueOf(setReservationRequestedCookie2);
            return this;
        }

        public com.airbnb.android.core.payments.models.BookingResult.Builder reservationConfirmationCode(String reservationConfirmationCode2) {
            if (reservationConfirmationCode2 == null) {
                throw new NullPointerException("Null reservationConfirmationCode");
            }
            this.reservationConfirmationCode = reservationConfirmationCode2;
            return this;
        }

        public BookingResult build() {
            String missing = "";
            if (this.success == null) {
                missing = missing + " success";
            }
            if (this.settlementCurrencyChange == null) {
                missing = missing + " settlementCurrencyChange";
            }
            if (this.setHardFallback == null) {
                missing = missing + " setHardFallback";
            }
            if (this.requireZipCodeExistingCc == null) {
                missing = missing + " requireZipCodeExistingCc";
            }
            if (this.setFirstTimeBookingCookie == null) {
                missing = missing + " setFirstTimeBookingCookie";
            }
            if (this.setReservationRequestedCookie == null) {
                missing = missing + " setReservationRequestedCookie";
            }
            if (this.reservationConfirmationCode == null) {
                missing = missing + " reservationConfirmationCode";
            }
            if (missing.isEmpty()) {
                return new AutoValue_BookingResult(this.success.booleanValue(), this.errors, this.settlementCurrencyChange.booleanValue(), this.setHardFallback.booleanValue(), this.requireZipCodeExistingCc.booleanValue(), this.setFirstTimeBookingCookie.booleanValue(), this.setReservationRequestedCookie.booleanValue(), this.reservationConfirmationCode);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_BookingResult(boolean success2, List<Error> errors2, boolean settlementCurrencyChange2, boolean setHardFallback2, boolean requireZipCodeExistingCc2, boolean setFirstTimeBookingCookie2, boolean setReservationRequestedCookie2, String reservationConfirmationCode2) {
        this.success = success2;
        this.errors = errors2;
        this.settlementCurrencyChange = settlementCurrencyChange2;
        this.setHardFallback = setHardFallback2;
        this.requireZipCodeExistingCc = requireZipCodeExistingCc2;
        this.setFirstTimeBookingCookie = setFirstTimeBookingCookie2;
        this.setReservationRequestedCookie = setReservationRequestedCookie2;
        if (reservationConfirmationCode2 == null) {
            throw new NullPointerException("Null reservationConfirmationCode");
        }
        this.reservationConfirmationCode = reservationConfirmationCode2;
    }

    @JsonProperty("success")
    public boolean success() {
        return this.success;
    }

    @JsonProperty("errors")
    public List<Error> errors() {
        return this.errors;
    }

    @JsonProperty("settlement_currency_change")
    public boolean settlementCurrencyChange() {
        return this.settlementCurrencyChange;
    }

    @JsonProperty("set_hard_fallback")
    public boolean setHardFallback() {
        return this.setHardFallback;
    }

    @JsonProperty("require_zip_code_existing_cc")
    public boolean requireZipCodeExistingCc() {
        return this.requireZipCodeExistingCc;
    }

    @JsonProperty("set_first_time_booking_cookie")
    public boolean setFirstTimeBookingCookie() {
        return this.setFirstTimeBookingCookie;
    }

    @JsonProperty("set_reservation_requested_cookie")
    public boolean setReservationRequestedCookie() {
        return this.setReservationRequestedCookie;
    }

    @JsonProperty("reservation_confirmation_code")
    public String reservationConfirmationCode() {
        return this.reservationConfirmationCode;
    }

    public String toString() {
        return "BookingResult{success=" + this.success + ", errors=" + this.errors + ", settlementCurrencyChange=" + this.settlementCurrencyChange + ", setHardFallback=" + this.setHardFallback + ", requireZipCodeExistingCc=" + this.requireZipCodeExistingCc + ", setFirstTimeBookingCookie=" + this.setFirstTimeBookingCookie + ", setReservationRequestedCookie=" + this.setReservationRequestedCookie + ", reservationConfirmationCode=" + this.reservationConfirmationCode + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BookingResult)) {
            return false;
        }
        BookingResult that = (BookingResult) o;
        if (this.success == that.success() && (this.errors != null ? this.errors.equals(that.errors()) : that.errors() == null) && this.settlementCurrencyChange == that.settlementCurrencyChange() && this.setHardFallback == that.setHardFallback() && this.requireZipCodeExistingCc == that.requireZipCodeExistingCc() && this.setFirstTimeBookingCookie == that.setFirstTimeBookingCookie() && this.setReservationRequestedCookie == that.setReservationRequestedCookie() && this.reservationConfirmationCode.equals(that.reservationConfirmationCode())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = 1231;
        int h = ((((1 * 1000003) ^ (this.success ? 1231 : 1237)) * 1000003) ^ (this.errors == null ? 0 : this.errors.hashCode())) * 1000003;
        if (this.settlementCurrencyChange) {
            i = 1231;
        } else {
            i = 1237;
        }
        int h2 = (h ^ i) * 1000003;
        if (this.setHardFallback) {
            i2 = 1231;
        } else {
            i2 = 1237;
        }
        int h3 = (h2 ^ i2) * 1000003;
        if (this.requireZipCodeExistingCc) {
            i3 = 1231;
        } else {
            i3 = 1237;
        }
        int h4 = (h3 ^ i3) * 1000003;
        if (this.setFirstTimeBookingCookie) {
            i4 = 1231;
        } else {
            i4 = 1237;
        }
        int h5 = (h4 ^ i4) * 1000003;
        if (!this.setReservationRequestedCookie) {
            i5 = 1237;
        }
        return ((h5 ^ i5) * 1000003) ^ this.reservationConfirmationCode.hashCode();
    }
}
