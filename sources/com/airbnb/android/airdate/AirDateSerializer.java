package com.airbnb.android.airdate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class AirDateSerializer extends StdSerializer<AirDate> {
    public AirDateSerializer() {
        super(AirDate.class);
    }

    public void serialize(AirDate airDate, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(airDate.getIsoDateString());
    }
}
