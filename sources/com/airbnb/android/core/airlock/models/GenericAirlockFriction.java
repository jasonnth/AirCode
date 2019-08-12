package com.airbnb.android.core.airlock.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class GenericAirlockFriction extends BaseAirlockFriction {
    public static final GenericAirlockFriction instance = builder().version(1.0d).build();

    public static abstract class Builder {
        public abstract GenericAirlockFriction build();

        @JsonProperty("version")
        public abstract Builder version(double d);
    }

    public static Builder builder() {
        return new Builder();
    }
}
