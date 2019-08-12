package com.apollographql.apollo.cache.http;

import java.io.IOException;

public interface HttpCacheStore {
    HttpCacheRecord cacheRecord(String str) throws IOException;

    HttpCacheRecordEditor cacheRecordEditor(String str) throws IOException;

    void remove(String str) throws IOException;
}
