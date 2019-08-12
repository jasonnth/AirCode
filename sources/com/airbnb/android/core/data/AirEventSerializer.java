package com.airbnb.android.core.data;

import com.airbnb.android.aireventlogger.AirEvent;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class AirEventSerializer extends StdSerializer<AirEvent> {
    protected AirEventSerializer() {
        super(AirEvent.class);
    }

    public void serialize(AirEvent value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeObject(value.data());
    }
}
