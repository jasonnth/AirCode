package com.airbnb.android.core.payments.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class AddPaymentMethodArguments implements Parcelable {

    public static abstract class Builder {
        public abstract AddPaymentMethodArguments build();

        @JsonProperty("add_payment_method_client")
        public abstract Builder clientType(AddPaymentMethodClientType addPaymentMethodClientType);
    }

    public abstract AddPaymentMethodClientType getClientType();

    public static Builder builder() {
        return new Builder();
    }
}
