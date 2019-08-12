package com.apollographql.apollo.internal.cache.http;

public enum HttpCacheFetchStrategy {
    CACHE_ONLY,
    NETWORK_ONLY,
    CACHE_FIRST,
    NETWORK_FIRST
}
