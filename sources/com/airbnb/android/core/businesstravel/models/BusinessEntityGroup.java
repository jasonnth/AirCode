package com.airbnb.android.core.businesstravel.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class BusinessEntityGroup implements Parcelable {

    public static abstract class Builder {
        public abstract BusinessEntityGroup build();

        @JsonProperty("id")
        public abstract Builder setId(long j);

        @JsonProperty("payment_method_display_name")
        public abstract Builder setPaymentMethodDisplayName(String str);
    }

    public abstract long getId();

    public abstract String getPaymentMethodDisplayName();

    public static Builder builder() {
        return new Builder();
    }
}
