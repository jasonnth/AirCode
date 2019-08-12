package com.apollographql.apollo.internal.cache.normalized;

import com.apollographql.apollo.CustomTypeAdapter;
import com.apollographql.apollo.api.C3107Operation;
import com.apollographql.apollo.api.C3107Operation.Data;
import com.apollographql.apollo.api.C3107Operation.Variables;
import com.apollographql.apollo.api.Field;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ScalarType;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.ApolloStore;
import com.apollographql.apollo.cache.normalized.ApolloStore.RecordChangeSubscriber;
import com.apollographql.apollo.cache.normalized.CacheKey;
import com.apollographql.apollo.cache.normalized.CacheKeyResolver;
import com.apollographql.apollo.cache.normalized.NormalizedCache;
import com.apollographql.apollo.cache.normalized.Record;
import com.apollographql.apollo.internal.field.CacheFieldValueResolver;
import com.apollographql.apollo.internal.reader.RealResponseReader;
import com.apollographql.apollo.internal.util.ApolloLogger;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class RealApolloStore implements ApolloStore, ReadableStore, WriteableStore {
    /* access modifiers changed from: private */
    public final CacheKeyResolver cacheKeyResolver;
    /* access modifiers changed from: private */
    public final Map<ScalarType, CustomTypeAdapter> customTypeAdapters;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    /* access modifiers changed from: private */
    public final ApolloLogger logger;
    private final NormalizedCache normalizedCache;
    private final Set<RecordChangeSubscriber> subscribers = Collections.newSetFromMap(new WeakHashMap());

    public RealApolloStore(NormalizedCache normalizedCache2, CacheKeyResolver cacheKeyResolver2, Map<ScalarType, CustomTypeAdapter> customTypeAdapters2, ApolloLogger logger2) {
        this.normalizedCache = (NormalizedCache) Utils.checkNotNull(normalizedCache2, "cacheStore == null");
        this.cacheKeyResolver = (CacheKeyResolver) Utils.checkNotNull(cacheKeyResolver2, "cacheKeyResolver == null");
        this.customTypeAdapters = (Map) Utils.checkNotNull(customTypeAdapters2, "customTypeAdapters == null");
        this.logger = (ApolloLogger) Utils.checkNotNull(logger2, "logger == null");
    }

    public ResponseNormalizer<Map<String, Object>> networkResponseNormalizer() {
        return new ResponseNormalizer<Map<String, Object>>() {
            public CacheKey resolveCacheKey(Field field, Map<String, Object> record) {
                return RealApolloStore.this.cacheKeyResolver.fromFieldRecordSet(field, record);
            }
        };
    }

    public ResponseNormalizer<Record> cacheResponseNormalizer() {
        return new ResponseNormalizer<Record>() {
            public CacheKey resolveCacheKey(Field field, Record record) {
                return CacheKey.from(record.key());
            }
        };
    }

    public void publish(Set<String> changedKeys) {
        Set<RecordChangeSubscriber> iterableSubscribers;
        Utils.checkNotNull(changedKeys, "changedKeys == null");
        if (!changedKeys.isEmpty()) {
            synchronized (this) {
                iterableSubscribers = new LinkedHashSet<>(this.subscribers);
            }
            for (RecordChangeSubscriber subscriber : iterableSubscribers) {
                subscriber.onCacheRecordsChanged(changedKeys);
            }
        }
    }

    public <R> R readTransaction(Transaction<ReadableStore, R> transaction) {
        this.lock.readLock().lock();
        try {
            return transaction.execute(this);
        } finally {
            this.lock.readLock().unlock();
        }
    }

    public <R> R writeTransaction(Transaction<WriteableStore, R> transaction) {
        this.lock.writeLock().lock();
        try {
            return transaction.execute(this);
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public Record read(String key, CacheHeaders cacheHeaders) {
        return this.normalizedCache.loadRecord((String) Utils.checkNotNull(key, "key == null"), cacheHeaders);
    }

    public Set<String> merge(Collection<Record> recordSet, CacheHeaders cacheHeaders) {
        return this.normalizedCache.merge((Collection) Utils.checkNotNull(recordSet, "recordSet == null"), cacheHeaders);
    }

    public CacheKeyResolver cacheKeyResolver() {
        return this.cacheKeyResolver;
    }

    public <D extends Data, T, V extends Variables> Response<T> read(C3107Operation<D, T, V> operation, ResponseFieldMapper<D> responseFieldMapper, ResponseNormalizer<Record> responseNormalizer, CacheHeaders cacheHeaders) {
        Utils.checkNotNull(operation, "operation == null");
        Utils.checkNotNull(responseNormalizer, "responseNormalizer == null");
        Utils.checkNotNull(this.customTypeAdapters, "customTypeAdapters == null");
        final C3107Operation<D, T, V> operation2 = operation;
        final CacheHeaders cacheHeaders2 = cacheHeaders;
        final ResponseNormalizer<Record> responseNormalizer2 = responseNormalizer;
        final ResponseFieldMapper<D> responseFieldMapper2 = responseFieldMapper;
        return (Response) readTransaction(new Transaction<ReadableStore, Response<T>>() {
            public Response<T> execute(ReadableStore cache) {
                Record rootRecord = cache.read(CacheKeyResolver.rootKeyForOperation(operation2).key(), cacheHeaders2);
                if (rootRecord == null) {
                    return Response.builder(operation2).build();
                }
                RealResponseReader<Record> responseReader = new RealResponseReader<>(operation2.variables(), rootRecord, new CacheFieldValueResolver(cache, operation2.variables(), RealApolloStore.this.cacheKeyResolver(), cacheHeaders2), RealApolloStore.this.customTypeAdapters, responseNormalizer2);
                try {
                    responseNormalizer2.willResolveRootQuery(operation2);
                    return Response.builder(operation2).data(operation2.wrapData((Data) responseFieldMapper2.map(responseReader))).dependentKeys(responseNormalizer2.dependentKeys()).build();
                } catch (Exception e) {
                    RealApolloStore.this.logger.mo27167e(e, "Failed to read cached operation data. Operation: %s", operation2);
                    return Response.builder(operation2).build();
                }
            }
        });
    }
}
