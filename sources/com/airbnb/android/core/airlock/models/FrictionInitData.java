package com.airbnb.android.core.airlock.models;

import android.os.Parcelable;
import com.airbnb.android.core.airlock.deserializer.FrictionInitDataDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Map;

@JsonDeserialize(builder = Builder.class)
public abstract class FrictionInitData implements Parcelable {

    public static abstract class Builder {
        public abstract FrictionInitData build();

        @JsonProperty("friction_init_data")
        @JsonDeserialize(using = FrictionInitDataDeserializer.class)
        public abstract Builder frictionMap(Map<String, BaseAirlockFriction> map);
    }

    public abstract Map<String, BaseAirlockFriction> frictionMap();

    public static Builder builder() {
        return new Builder();
    }

    public boolean hasChargebackAppeal() {
        return frictionMap().containsKey(AirlockFrictionType.ChargebackAppeal.getServerKey());
    }

    public BaseAirlockFriction get(AirlockFrictionType frictionType) {
        String key = frictionType.getServerKey();
        if (frictionMap().containsKey(key)) {
            return (BaseAirlockFriction) frictionMap().get(key);
        }
        return GenericAirlockFriction.instance;
    }
}
