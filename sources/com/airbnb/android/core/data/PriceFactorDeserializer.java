package com.airbnb.android.core.data;

import com.airbnb.android.core.models.PriceFactor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

public class PriceFactorDeserializer extends StdScalarDeserializer<PriceFactor> {
    public PriceFactorDeserializer() {
        super(PriceFactor.class);
    }

    public PriceFactor deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        jp.getCurrentToken();
        if (jp.getNumberType() != null) {
            Double priceFactor = Double.valueOf(jp.getNumberValue().doubleValue());
            if (priceFactor.doubleValue() <= 1.0d) {
                return new PriceFactor(priceFactor);
            }
        }
        throw ctxt.wrongTokenException(jp, JsonToken.VALUE_NUMBER_FLOAT, "Expected number type or null");
    }

    public PriceFactor getNullValue() {
        return new PriceFactor();
    }

    public Object deserializeWithType(JsonParser jp, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(jp, ctxt);
    }
}
