package com.apollographql.apollo;

import com.apollographql.apollo.cache.normalized.CacheControl;

public interface ApolloQueryCall<T> extends ApolloCall<T> {
    ApolloQueryCall<T> cacheControl(CacheControl cacheControl);
}
