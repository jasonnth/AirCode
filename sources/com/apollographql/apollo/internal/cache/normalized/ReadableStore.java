package com.apollographql.apollo.internal.cache.normalized;

import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.Record;

public interface ReadableStore {
    Record read(String str, CacheHeaders cacheHeaders);
}
