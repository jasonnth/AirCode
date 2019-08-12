package com.airbnb.android.core.data;

import com.airbnb.android.core.utils.Check;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import dagger.Lazy;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public final class JacksonConverterFactory extends ConverterFactory {
    private final Lazy<ObjectMapper> mapper;

    public static JacksonConverterFactory create(Lazy<ObjectMapper> mapper2) {
        return new JacksonConverterFactory(mapper2);
    }

    private JacksonConverterFactory(Lazy<ObjectMapper> mapper2) {
        this.mapper = (Lazy) Check.notNull(mapper2);
    }

    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new JacksonResponseBodyConverter(readerForType(type));
    }

    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new JacksonRequestBodyConverter(writerForType(type));
    }

    public Converter<?> get(Type type) {
        return new JacksonConverter(readerForType(type), writerForType(type));
    }

    private ObjectWriter writerForType(Type type) {
        ObjectMapper mapper2 = (ObjectMapper) this.mapper.get();
        return mapper2.writerFor(mapper2.getTypeFactory().constructType(type));
    }

    private ObjectReader readerForType(Type type) {
        ObjectMapper mapper2 = (ObjectMapper) this.mapper.get();
        return mapper2.readerFor(mapper2.getTypeFactory().constructType(type));
    }
}
