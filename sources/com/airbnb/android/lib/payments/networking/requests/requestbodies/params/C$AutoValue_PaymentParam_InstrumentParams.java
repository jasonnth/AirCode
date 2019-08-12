package com.airbnb.android.lib.payments.networking.requests.requestbodies.params;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.$AutoValue_PaymentParam_InstrumentParams reason: invalid class name */
abstract class C$AutoValue_PaymentParam_InstrumentParams extends InstrumentParams {
    private final String paymentMethodCseCvvPayload;

    C$AutoValue_PaymentParam_InstrumentParams(String paymentMethodCseCvvPayload2) {
        this.paymentMethodCseCvvPayload = paymentMethodCseCvvPayload2;
    }

    @JsonProperty("payment_method_cse_cvv_payload")
    public String paymentMethodCseCvvPayload() {
        return this.paymentMethodCseCvvPayload;
    }

    public String toString() {
        return "InstrumentParams{paymentMethodCseCvvPayload=" + this.paymentMethodCseCvvPayload + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof InstrumentParams)) {
            return false;
        }
        InstrumentParams that = (InstrumentParams) o;
        if (this.paymentMethodCseCvvPayload != null) {
            return this.paymentMethodCseCvvPayload.equals(that.paymentMethodCseCvvPayload());
        }
        if (that.paymentMethodCseCvvPayload() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.paymentMethodCseCvvPayload == null ? 0 : this.paymentMethodCseCvvPayload.hashCode());
    }
}
