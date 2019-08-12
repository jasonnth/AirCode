package com.apollographql.apollo.cache.http;

import okio.Source;

public interface HttpCacheRecord {
    Source bodySource();

    void close();

    Source headerSource();
}
