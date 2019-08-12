package com.airbnb.android.core.payments.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class RedirectSettings implements Parcelable {

    public static abstract class Builder {
        public abstract RedirectSettings build();

        @JsonProperty("url")
        public abstract Builder url(String str);
    }

    @JsonProperty("url")
    public abstract String url();

    public static Builder builder() {
        return new Builder();
    }
}
