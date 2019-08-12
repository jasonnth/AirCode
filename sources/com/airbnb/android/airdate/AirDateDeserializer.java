package com.airbnb.android.airdate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

public class AirDateDeserializer extends StdScalarDeserializer<AirDate> {
    public AirDateDeserializer() {
        super(AirDate.class);
    }

    public AirDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        if (jp.getCurrentToken() == JsonToken.VALUE_STRING) {
            String airDateString = jp.getText().trim();
            try {
                return new AirDate(airDateString);
            } catch (IllegalArgumentException e) {
                throw ctxt.weirdStringException(airDateString, AirDate.class, "Expected yyyy-mm-dd format");
            }
        } else {
            throw ctxt.wrongTokenException(jp, JsonToken.VALUE_STRING, "Expected String in yyyy-mm-dd format");
        }
    }

    public Object deserializeWithType(JsonParser jp, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(jp, ctxt);
    }
}
