package com.apollographql.apollo.cache.normalized.lru;

import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.cache.normalized.NormalizedCacheFactory;
import com.apollographql.apollo.cache.normalized.RecordFieldAdapter;

public final class LruNormalizedCacheFactory implements NormalizedCacheFactory<LruNormalizedCache> {
    private final EvictionPolicy evictionPolicy;
    private final Optional<NormalizedCacheFactory> optionalSecondaryCache;

    public LruNormalizedCacheFactory(EvictionPolicy evictionPolicy2, NormalizedCacheFactory secondaryCacheFactory) {
        this.evictionPolicy = (EvictionPolicy) Utils.checkNotNull(evictionPolicy2, "evictionPolicy == null");
        this.optionalSecondaryCache = Optional.fromNullable(secondaryCacheFactory);
    }

    public LruNormalizedCache createNormalizedCache(RecordFieldAdapter fieldAdapter) {
        return new LruNormalizedCache(fieldAdapter, this.evictionPolicy, this.optionalSecondaryCache);
    }
}
