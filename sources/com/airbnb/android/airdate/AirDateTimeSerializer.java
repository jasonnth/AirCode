package com.airbnb.android.airdate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class AirDateTimeSerializer extends StdSerializer<AirDateTime> {
    public AirDateTimeSerializer() {
        super(AirDateTime.class);
    }

    public void serialize(AirDateTime airDateTime, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(airDateTime.getIsoDateStringUTC());
    }
}
