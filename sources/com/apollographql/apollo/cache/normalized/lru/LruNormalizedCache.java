package com.apollographql.apollo.cache.normalized.lru;

import com.apollographql.apollo.api.internal.Function;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.NormalizedCache;
import com.apollographql.apollo.cache.normalized.NormalizedCacheFactory;
import com.apollographql.apollo.cache.normalized.Record;
import com.apollographql.apollo.cache.normalized.RecordFieldAdapter;
import com.nytimes.android.external.cache.Cache;
import com.nytimes.android.external.cache.CacheBuilder;
import com.nytimes.android.external.cache.Weigher;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public final class LruNormalizedCache extends NormalizedCache {
    private final Cache<String, Record> lruCache;
    /* access modifiers changed from: private */
    public final Optional<NormalizedCache> secondaryCache;

    LruNormalizedCache(final RecordFieldAdapter recordFieldAdapter, EvictionPolicy evictionPolicy, Optional<NormalizedCacheFactory> secondaryNormalizedCache) {
        super(recordFieldAdapter);
        this.secondaryCache = secondaryNormalizedCache.transform(new Function<NormalizedCacheFactory, NormalizedCache>() {
            public NormalizedCache apply(NormalizedCacheFactory normalizedCacheFactory) {
                return normalizedCacheFactory.createNormalizedCache(recordFieldAdapter);
            }
        });
        CacheBuilder<Object, Object> lruCacheBuilder = CacheBuilder.newBuilder();
        if (evictionPolicy.maxSizeBytes().isPresent()) {
            lruCacheBuilder.maximumWeight(((Long) evictionPolicy.maxSizeBytes().get()).longValue()).weigher(new Weigher<String, Record>() {
                public int weigh(String key, Record value) {
                    return key.getBytes().length + value.sizeEstimateBytes();
                }
            });
        }
        if (evictionPolicy.maxEntries().isPresent()) {
            lruCacheBuilder.maximumSize(((Long) evictionPolicy.maxEntries().get()).longValue());
        }
        if (evictionPolicy.expireAfterAccess().isPresent()) {
            lruCacheBuilder.expireAfterAccess(((Long) evictionPolicy.expireAfterAccess().get()).longValue(), (TimeUnit) evictionPolicy.expireAfterAccessTimeUnit().get());
        }
        if (evictionPolicy.expireAfterWrite().isPresent()) {
            lruCacheBuilder.expireAfterWrite(((Long) evictionPolicy.expireAfterWrite().get()).longValue(), (TimeUnit) evictionPolicy.expireAfterWriteTimeUnit().get());
        }
        this.lruCache = lruCacheBuilder.build();
    }

    public Record loadRecord(final String key, final CacheHeaders cacheHeaders) {
        Record record;
        if (this.secondaryCache.isPresent()) {
            try {
                record = (Record) this.lruCache.get(key, new Callable<Record>() {
                    public Record call() throws Exception {
                        Record record = ((NormalizedCache) LruNormalizedCache.this.secondaryCache.get()).loadRecord(key, cacheHeaders);
                        if (record != null) {
                            return record;
                        }
                        throw new Exception(String.format("Record{key=%s} not present in secondary cache", new Object[]{key}));
                    }
                });
            } catch (Exception e) {
                return null;
            }
        } else {
            record = (Record) this.lruCache.getIfPresent(key);
        }
        if (record == null || !cacheHeaders.hasHeader("evict-after-read")) {
            return record;
        }
        this.lruCache.invalidate(key);
        return record;
    }

    public Set<String> merge(Record apolloRecord, CacheHeaders cacheHeaders) {
        if (cacheHeaders.hasHeader("do-not-store")) {
            return Collections.emptySet();
        }
        if (this.secondaryCache.isPresent()) {
            ((NormalizedCache) this.secondaryCache.get()).merge(apolloRecord, cacheHeaders);
        }
        Record oldRecord = (Record) this.lruCache.getIfPresent(apolloRecord.key());
        if (oldRecord == null) {
            this.lruCache.put(apolloRecord.key(), apolloRecord);
            return Collections.emptySet();
        }
        Set<String> mergeWith = oldRecord.mergeWith(apolloRecord);
        this.lruCache.put(apolloRecord.key(), oldRecord);
        return mergeWith;
    }
}
