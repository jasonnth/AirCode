package com.apollographql.apollo.internal.cache.normalized;

import com.apollographql.apollo.api.C3107Operation;
import com.apollographql.apollo.api.C3107Operation.Data;
import com.apollographql.apollo.api.C3107Operation.Variables;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.ApolloStore;
import com.apollographql.apollo.cache.normalized.Record;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public final class NoOpApolloStore implements ApolloStore, ReadableStore, WriteableStore {
    public Set<String> merge(Collection<Record> collection, CacheHeaders cacheHeaders) {
        return Collections.emptySet();
    }

    public Record read(String key, CacheHeaders cacheHeaders) {
        return null;
    }

    public void publish(Set<String> set) {
    }

    public ResponseNormalizer<Map<String, Object>> networkResponseNormalizer() {
        return ResponseNormalizer.NO_OP_NORMALIZER;
    }

    public ResponseNormalizer<Record> cacheResponseNormalizer() {
        return ResponseNormalizer.NO_OP_NORMALIZER;
    }

    public <R> R writeTransaction(Transaction<WriteableStore, R> transaction) {
        return transaction.execute(this);
    }

    public <D extends Data, T, V extends Variables> Response<T> read(C3107Operation<D, T, V> operation, ResponseFieldMapper<D> responseFieldMapper, ResponseNormalizer<Record> responseNormalizer, CacheHeaders cacheHeaders) {
        return Response.builder(operation).build();
    }
}
