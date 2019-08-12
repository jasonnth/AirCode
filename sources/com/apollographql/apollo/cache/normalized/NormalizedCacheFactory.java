package com.apollographql.apollo.cache.normalized;

import com.apollographql.apollo.cache.normalized.NormalizedCache;

public interface NormalizedCacheFactory<T extends NormalizedCache> {
    T createNormalizedCache(RecordFieldAdapter recordFieldAdapter);
}
