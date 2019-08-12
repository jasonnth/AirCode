package com.airbnb.android.airdate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

public class AirDateTimeZoneDeserializer extends StdScalarDeserializer<AirDateTime> {
    public AirDateTimeZoneDeserializer() {
        super(AirDateTime.class);
    }

    public AirDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        if (jp.getCurrentToken() == JsonToken.VALUE_STRING) {
            String airDateTimeString = jp.getText().trim();
            try {
                return AirDateTime.parse(airDateTimeString);
            } catch (IllegalArgumentException e) {
                throw ctxt.weirdStringException(airDateTimeString, AirDate.class, "Expected yyyy-MM-dd'T'HH:mm:ssZZ format");
            }
        } else {
            throw ctxt.wrongTokenException(jp, JsonToken.VALUE_STRING, "Expected String in yyyy-MM-dd'T'HH:mm:ssZZ format");
        }
    }

    public Object deserializeWithType(JsonParser jp, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(jp, ctxt);
    }
}
