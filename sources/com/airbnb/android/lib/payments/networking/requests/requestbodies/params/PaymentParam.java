package com.airbnb.android.lib.payments.networking.requests.requestbodies.params;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class PaymentParam implements Parcelable {

    static abstract class AirbnbCredit implements Parcelable {
        @JsonProperty("include_airbnb_credit")
        public abstract boolean includeAirbnbCredit();

        AirbnbCredit() {
        }
    }

    /* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.PaymentParam$BusinessTravel */
    static abstract class C0895BusinessTravel implements Parcelable {
        @JsonProperty("business_entity_group_id")
        public abstract Long businessEntityGroupId();

        C0895BusinessTravel() {
        }
    }

    static abstract class ExistingGibraltarInstrument implements Parcelable {
        @JsonProperty("id")
        /* renamed from: id */
        public abstract Long mo10855id();

        ExistingGibraltarInstrument() {
        }
    }

    static abstract class InstrumentParams implements Parcelable {
        @JsonProperty("payment_method_cse_cvv_payload")
        public abstract String paymentMethodCseCvvPayload();

        InstrumentParams() {
        }
    }

    public static abstract class Builder {
        /* access modifiers changed from: 0000 */
        public abstract Builder airbnbCredit(AirbnbCredit airbnbCredit);

        public abstract PaymentParam build();

        /* access modifiers changed from: 0000 */
        public abstract Builder businessTravel(C0895BusinessTravel businessTravel);

        public abstract Builder country(String str);

        public abstract Builder displayCurrency(String str);

        /* access modifiers changed from: 0000 */
        public abstract Builder existingGibraltarInstrument(ExistingGibraltarInstrument existingGibraltarInstrument);

        /* access modifiers changed from: 0000 */
        public abstract Builder instrumentParams(InstrumentParams instrumentParams);

        public abstract Builder method(String str);

        public abstract Builder userAgreedToCurrencyMismatch(Boolean bool);

        public abstract Builder zipRetry(String str);

        public Builder gibraltarInstrumentId(Long gibraltarInstrumentId) {
            existingGibraltarInstrument(new AutoValue_PaymentParam_ExistingGibraltarInstrument(gibraltarInstrumentId));
            return this;
        }

        public Builder airbnbCredit(boolean includeAirbnbCredit) {
            airbnbCredit((AirbnbCredit) new AutoValue_PaymentParam_AirbnbCredit(includeAirbnbCredit));
            return this;
        }

        public Builder instrumentParams(String paymentMethodCseCvvPayload) {
            instrumentParams((InstrumentParams) new AutoValue_PaymentParam_InstrumentParams(paymentMethodCseCvvPayload));
            return this;
        }

        public Builder businessTravel(Long businessEntityGroupId) {
            businessTravel((C0895BusinessTravel) new AutoValue_PaymentParam_BusinessTravel(businessEntityGroupId));
            return this;
        }
    }

    @JsonProperty("airbnb_credit")
    public abstract AirbnbCredit airbnbCredit();

    @JsonProperty("business_travel")
    public abstract C0895BusinessTravel businessTravel();

    @JsonProperty("country")
    public abstract String country();

    @JsonProperty("display_currency")
    public abstract String displayCurrency();

    @JsonProperty("existing_gibraltar_instrument")
    public abstract ExistingGibraltarInstrument existingGibraltarInstrument();

    @JsonProperty("instrument_params")
    public abstract InstrumentParams instrumentParams();

    @JsonProperty("method")
    public abstract String method();

    @JsonProperty("user_agreed_to_currency_mismatch")
    public abstract Boolean userAgreedToCurrencyMismatch();

    @JsonProperty("zip_retry")
    public abstract String zipRetry();

    public static Builder builder() {
        return new Builder();
    }
}
