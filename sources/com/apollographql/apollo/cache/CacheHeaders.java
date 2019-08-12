package com.apollographql.apollo.cache;

import java.util.Collections;
import java.util.Map;

public final class CacheHeaders {
    public static final CacheHeaders NONE = new CacheHeaders(Collections.emptyMap());
    private final Map<String, String> headerMap;

    private CacheHeaders(Map<String, String> headerMap2) {
        this.headerMap = headerMap2;
    }

    public boolean hasHeader(String headerName) {
        return this.headerMap.containsKey(headerName);
    }
}
