package com.airbnb.android.core.data;

import com.airbnb.airrequest.QueryStrap;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QueryStrapDeserializer extends StdDeserializer<QueryStrap> {
    protected QueryStrapDeserializer() {
        super(QueryStrap.class);
    }

    public QueryStrap deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        return QueryStrap.make().mix((Map) ctxt.readValue(parser, ctxt.getTypeFactory().constructMapType(HashMap.class, String.class, String.class)));
    }
}
