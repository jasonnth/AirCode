package com.apollographql.apollo.cache.normalized;

import com.apollographql.apollo.internal.json.ApolloJsonReader;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;
import okio.BufferedSource;
import okio.Okio;

public final class RecordFieldAdapter {
    private final JsonAdapter<Map<String, Object>> serializationAdapter;

    private static class BigDecimalAdapter extends JsonAdapter<BigDecimal> {
        private BigDecimalAdapter() {
        }

        public void toJson(JsonWriter writer, BigDecimal value) throws IOException {
            writer.value((Number) value);
        }
    }

    private static class CacheReferenceAdapter extends JsonAdapter<CacheReference> {
        private CacheReferenceAdapter() {
        }

        public void toJson(JsonWriter writer, CacheReference value) throws IOException {
            writer.value(value.serialize());
        }
    }

    private RecordFieldAdapter(Moshi moshi) {
        this.serializationAdapter = moshi.adapter(Types.newParameterizedType(Map.class, String.class, Object.class));
    }

    public static RecordFieldAdapter create(Moshi baseMoshi) {
        return new RecordFieldAdapter(baseMoshi.newBuilder().add(CacheReference.class, new CacheReferenceAdapter()).add(BigDecimal.class, new BigDecimalAdapter()).build());
    }

    public String toJson(Map<String, Object> fields) {
        return this.serializationAdapter.toJson(fields);
    }

    public Map<String, Object> from(BufferedSource bufferedFieldSource) throws IOException {
        return ApolloJsonReader.cacheJsonStreamReader(ApolloJsonReader.bufferedSourceJsonReader(bufferedFieldSource)).toMap();
    }

    public Map<String, Object> from(String jsonFieldSource) throws IOException {
        return from(Okio.buffer(Okio.source((InputStream) new ByteArrayInputStream(jsonFieldSource.getBytes()))));
    }
}
