package com.airbnb.android.core.payments.models;

import android.os.Parcelable;
import com.airbnb.android.core.models.Price;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class QuickPayArguments implements Parcelable {

    public static abstract class Builder {
        public abstract QuickPayArguments build();

        @JsonProperty("cart_item")
        public abstract Builder cartItem(CartItem cartItem);

        @JsonProperty("quickpay_client")
        public abstract Builder clientType(QuickPayClientType quickPayClientType);

        @JsonProperty("price_info")
        public abstract Builder price(Price price);
    }

    public abstract CartItem getCartItem();

    public abstract QuickPayClientType getClientType();

    public abstract Price getPrice();

    public static Builder builder() {
        return new Builder();
    }
}
