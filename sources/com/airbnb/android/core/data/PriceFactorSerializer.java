package com.airbnb.android.core.data;

import com.airbnb.android.core.models.PriceFactor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class PriceFactorSerializer extends StdSerializer<PriceFactor> {
    public PriceFactorSerializer() {
        super(PriceFactor.class);
    }

    public void serialize(PriceFactor priceFactor, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeNumber(priceFactor.getFactorValue().doubleValue());
    }
}
