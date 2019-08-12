package com.airbnb.android.aireventlogger;

import com.airbnb.android.aireventlogger.Converter.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Type;

public class JacksonConverterFactory implements Factory {
    private final ObjectMapper mapper;

    public static JacksonConverterFactory create() {
        return create(new ObjectMapper());
    }

    public static JacksonConverterFactory create(ObjectMapper mapper2) {
        return new JacksonConverterFactory(mapper2);
    }

    private JacksonConverterFactory(ObjectMapper mapper2) {
        this.mapper = (ObjectMapper) Utils.throwIfNull(mapper2, "mapper == null");
    }

    public Converter<?> get(Type type) {
        return new JacksonConverter(this.mapper.writerFor(this.mapper.getTypeFactory().constructType(type)));
    }
}
