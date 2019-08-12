package com.airbnb.android.core.data;

import com.airbnb.airrequest.QueryStrap;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class QueryStrapModule extends SimpleModule {
    public QueryStrapModule() {
        addSerializer(QueryStrap.class, new QueryStrapSerializer());
        addDeserializer(QueryStrap.class, new QueryStrapDeserializer());
    }
}
