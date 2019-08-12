package com.airbnb.android.core.data;

import com.airbnb.android.utils.IOUtils;
import com.fasterxml.jackson.databind.ObjectReader;
import java.io.IOException;
import java.io.Reader;
import okhttp3.ResponseBody;
import retrofit2.Converter;

final class JacksonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final ObjectReader adapter;

    JacksonResponseBodyConverter(ObjectReader adapter2) {
        this.adapter = adapter2;
    }

    public T convert(ResponseBody value) throws IOException {
        Reader reader = value.charStream();
        try {
            return this.adapter.readValue(reader);
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }
}
