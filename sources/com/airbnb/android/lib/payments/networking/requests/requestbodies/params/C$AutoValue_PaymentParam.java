package com.airbnb.android.lib.payments.networking.requests.requestbodies.params;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.$AutoValue_PaymentParam reason: invalid class name */
abstract class C$AutoValue_PaymentParam extends PaymentParam {
    private final AirbnbCredit airbnbCredit;
    private final C0895BusinessTravel businessTravel;
    private final String country;
    private final String displayCurrency;
    private final ExistingGibraltarInstrument existingGibraltarInstrument;
    private final InstrumentParams instrumentParams;
    private final String method;
    private final Boolean userAgreedToCurrencyMismatch;
    private final String zipRetry;

    /* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.$AutoValue_PaymentParam$Builder */
    static final class Builder extends com.airbnb.android.lib.payments.networking.requests.requestbodies.params.PaymentParam.Builder {
        private AirbnbCredit airbnbCredit;
        private C0895BusinessTravel businessTravel;
        private String country;
        private String displayCurrency;
        private ExistingGibraltarInstrument existingGibraltarInstrument;
        private InstrumentParams instrumentParams;
        private String method;
        private Boolean userAgreedToCurrencyMismatch;
        private String zipRetry;

        Builder() {
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.PaymentParam.Builder method(String method2) {
            this.method = method2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.PaymentParam.Builder existingGibraltarInstrument(ExistingGibraltarInstrument existingGibraltarInstrument2) {
            this.existingGibraltarInstrument = existingGibraltarInstrument2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.PaymentParam.Builder userAgreedToCurrencyMismatch(Boolean userAgreedToCurrencyMismatch2) {
            this.userAgreedToCurrencyMismatch = userAgreedToCurrencyMismatch2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.PaymentParam.Builder airbnbCredit(AirbnbCredit airbnbCredit2) {
            this.airbnbCredit = airbnbCredit2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.PaymentParam.Builder country(String country2) {
            this.country = country2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.PaymentParam.Builder zipRetry(String zipRetry2) {
            this.zipRetry = zipRetry2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.PaymentParam.Builder instrumentParams(InstrumentParams instrumentParams2) {
            this.instrumentParams = instrumentParams2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.PaymentParam.Builder businessTravel(C0895BusinessTravel businessTravel2) {
            this.businessTravel = businessTravel2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.PaymentParam.Builder displayCurrency(String displayCurrency2) {
            if (displayCurrency2 == null) {
                throw new NullPointerException("Null displayCurrency");
            }
            this.displayCurrency = displayCurrency2;
            return this;
        }

        public PaymentParam build() {
            String missing = "";
            if (this.displayCurrency == null) {
                missing = missing + " displayCurrency";
            }
            if (missing.isEmpty()) {
                return new AutoValue_PaymentParam(this.method, this.existingGibraltarInstrument, this.userAgreedToCurrencyMismatch, this.airbnbCredit, this.country, this.zipRetry, this.instrumentParams, this.businessTravel, this.displayCurrency);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_PaymentParam(String method2, ExistingGibraltarInstrument existingGibraltarInstrument2, Boolean userAgreedToCurrencyMismatch2, AirbnbCredit airbnbCredit2, String country2, String zipRetry2, InstrumentParams instrumentParams2, C0895BusinessTravel businessTravel2, String displayCurrency2) {
        this.method = method2;
        this.existingGibraltarInstrument = existingGibraltarInstrument2;
        this.userAgreedToCurrencyMismatch = userAgreedToCurrencyMismatch2;
        this.airbnbCredit = airbnbCredit2;
        this.country = country2;
        this.zipRetry = zipRetry2;
        this.instrumentParams = instrumentParams2;
        this.businessTravel = businessTravel2;
        if (displayCurrency2 == null) {
            throw new NullPointerException("Null displayCurrency");
        }
        this.displayCurrency = displayCurrency2;
    }

    @JsonProperty("method")
    public String method() {
        return this.method;
    }

    @JsonProperty("existing_gibraltar_instrument")
    public ExistingGibraltarInstrument existingGibraltarInstrument() {
        return this.existingGibraltarInstrument;
    }

    @JsonProperty("user_agreed_to_currency_mismatch")
    public Boolean userAgreedToCurrencyMismatch() {
        return this.userAgreedToCurrencyMismatch;
    }

    @JsonProperty("airbnb_credit")
    public AirbnbCredit airbnbCredit() {
        return this.airbnbCredit;
    }

    @JsonProperty("country")
    public String country() {
        return this.country;
    }

    @JsonProperty("zip_retry")
    public String zipRetry() {
        return this.zipRetry;
    }

    @JsonProperty("instrument_params")
    public InstrumentParams instrumentParams() {
        return this.instrumentParams;
    }

    @JsonProperty("business_travel")
    public C0895BusinessTravel businessTravel() {
        return this.businessTravel;
    }

    @JsonProperty("display_currency")
    public String displayCurrency() {
        return this.displayCurrency;
    }

    public String toString() {
        return "PaymentParam{method=" + this.method + ", existingGibraltarInstrument=" + this.existingGibraltarInstrument + ", userAgreedToCurrencyMismatch=" + this.userAgreedToCurrencyMismatch + ", airbnbCredit=" + this.airbnbCredit + ", country=" + this.country + ", zipRetry=" + this.zipRetry + ", instrumentParams=" + this.instrumentParams + ", businessTravel=" + this.businessTravel + ", displayCurrency=" + this.displayCurrency + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PaymentParam)) {
            return false;
        }
        PaymentParam that = (PaymentParam) o;
        if (this.method != null ? this.method.equals(that.method()) : that.method() == null) {
            if (this.existingGibraltarInstrument != null ? this.existingGibraltarInstrument.equals(that.existingGibraltarInstrument()) : that.existingGibraltarInstrument() == null) {
                if (this.userAgreedToCurrencyMismatch != null ? this.userAgreedToCurrencyMismatch.equals(that.userAgreedToCurrencyMismatch()) : that.userAgreedToCurrencyMismatch() == null) {
                    if (this.airbnbCredit != null ? this.airbnbCredit.equals(that.airbnbCredit()) : that.airbnbCredit() == null) {
                        if (this.country != null ? this.country.equals(that.country()) : that.country() == null) {
                            if (this.zipRetry != null ? this.zipRetry.equals(that.zipRetry()) : that.zipRetry() == null) {
                                if (this.instrumentParams != null ? this.instrumentParams.equals(that.instrumentParams()) : that.instrumentParams() == null) {
                                    if (this.businessTravel != null ? this.businessTravel.equals(that.businessTravel()) : that.businessTravel() == null) {
                                        if (this.displayCurrency.equals(that.displayCurrency())) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((((1 * 1000003) ^ (this.method == null ? 0 : this.method.hashCode())) * 1000003) ^ (this.existingGibraltarInstrument == null ? 0 : this.existingGibraltarInstrument.hashCode())) * 1000003) ^ (this.userAgreedToCurrencyMismatch == null ? 0 : this.userAgreedToCurrencyMismatch.hashCode())) * 1000003) ^ (this.airbnbCredit == null ? 0 : this.airbnbCredit.hashCode())) * 1000003) ^ (this.country == null ? 0 : this.country.hashCode())) * 1000003) ^ (this.zipRetry == null ? 0 : this.zipRetry.hashCode())) * 1000003) ^ (this.instrumentParams == null ? 0 : this.instrumentParams.hashCode())) * 1000003;
        if (this.businessTravel != null) {
            i = this.businessTravel.hashCode();
        }
        return ((h ^ i) * 1000003) ^ this.displayCurrency.hashCode();
    }
}
