package com.airbnb.android.lib.payments.networking.requests.requestbodies.params;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ProductParam implements Parcelable {
    @JsonProperty("product_type")
    public abstract String productType();
}
