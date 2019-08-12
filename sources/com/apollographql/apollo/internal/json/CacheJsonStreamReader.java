package com.apollographql.apollo.internal.json;

import com.apollographql.apollo.cache.normalized.CacheReference;
import java.io.IOException;

public final class CacheJsonStreamReader extends ResponseJsonStreamReader {
    public CacheJsonStreamReader(JsonReader jsonReader) {
        super(jsonReader);
    }

    public Object nextScalar(boolean optional) throws IOException {
        Object scalar = super.nextScalar(optional);
        if (!(scalar instanceof String)) {
            return scalar;
        }
        String scalarString = (String) scalar;
        if (CacheReference.canDeserialize(scalarString)) {
            return CacheReference.deserialize(scalarString);
        }
        return scalar;
    }
}
