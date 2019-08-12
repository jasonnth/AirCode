package com.airbnb.android.payout.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class ExtraPayoutAttribute implements Parcelable {

    public static abstract class Builder {
        public abstract ExtraPayoutAttribute build();

        @JsonProperty("key")
        public abstract Builder key(String str);

        @JsonProperty("value")
        public abstract Builder value(String str);
    }

    @JsonProperty("key")
    public abstract String key();

    @JsonProperty("value")
    public abstract String value();

    public static Builder builder() {
        return new Builder();
    }

    public ExtraPayoutAttributeType getExtraPayoutAttributeType() {
        return ExtraPayoutAttributeType.fromServerKey(key());
    }

    public boolean getBooleanValue() {
        return value().equals("true");
    }
}
