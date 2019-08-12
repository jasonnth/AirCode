package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter.Factory;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class MapJsonAdapter<K, V> extends JsonAdapter<Map<K, V>> {
    public static final Factory FACTORY = new Factory() {
        public JsonAdapter<?> create(Type type, Set<? extends Annotation> annotations, Moshi moshi) {
            if (!annotations.isEmpty()) {
                return null;
            }
            Class<?> rawType = Types.getRawType(type);
            if (rawType != Map.class) {
                return null;
            }
            Type[] keyAndValue = Types.mapKeyAndValueTypes(type, rawType);
            return new MapJsonAdapter(moshi, keyAndValue[0], keyAndValue[1]).nullSafe();
        }
    };
    private final JsonAdapter<K> keyAdapter;
    private final JsonAdapter<V> valueAdapter;

    public MapJsonAdapter(Moshi moshi, Type keyType, Type valueType) {
        this.keyAdapter = moshi.adapter(keyType);
        this.valueAdapter = moshi.adapter(valueType);
    }

    public void toJson(JsonWriter writer, Map<K, V> map) throws IOException {
        writer.beginObject();
        for (Entry<K, V> entry : map.entrySet()) {
            if (entry.getKey() == null) {
                throw new JsonDataException("Map key is null at " + writer.getPath());
            }
            writer.promoteValueToName();
            this.keyAdapter.toJson(writer, entry.getKey());
            this.valueAdapter.toJson(writer, entry.getValue());
        }
        writer.endObject();
    }

    public String toString() {
        return "JsonAdapter(" + this.keyAdapter + "=" + this.valueAdapter + ")";
    }
}
