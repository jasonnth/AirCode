package com.airbnb.android.core.data;

import android.support.p000v4.util.ArrayMap;
import com.airbnb.airrequest.QueryStrap;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import retrofit2.Query;

public class QueryStrapSerializer extends JsonSerializer<QueryStrap> {
    protected QueryStrapSerializer() {
    }

    public void serialize(QueryStrap value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        Map<String, String> queryMap = new ArrayMap<>(value.size());
        Iterator it = value.iterator();
        while (it.hasNext()) {
            Query query = (Query) it.next();
            queryMap.put(query.name(), query.value());
        }
        gen.writeObject(queryMap);
    }
}
