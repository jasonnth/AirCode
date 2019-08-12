package com.apollographql.apollo.cache.normalized;

import com.apollographql.apollo.api.C3107Operation;
import com.apollographql.apollo.api.C3107Operation.Data;
import com.apollographql.apollo.api.C3107Operation.Variables;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.internal.cache.normalized.NoOpApolloStore;
import com.apollographql.apollo.internal.cache.normalized.ResponseNormalizer;
import com.apollographql.apollo.internal.cache.normalized.Transaction;
import com.apollographql.apollo.internal.cache.normalized.WriteableStore;
import java.util.Map;
import java.util.Set;

public interface ApolloStore {
    public static final ApolloStore NO_APOLLO_STORE = new NoOpApolloStore();

    public interface RecordChangeSubscriber {
        void onCacheRecordsChanged(Set<String> set);
    }

    ResponseNormalizer<Record> cacheResponseNormalizer();

    ResponseNormalizer<Map<String, Object>> networkResponseNormalizer();

    void publish(Set<String> set);

    <D extends Data, T, V extends Variables> Response<T> read(C3107Operation<D, T, V> operation, ResponseFieldMapper<D> responseFieldMapper, ResponseNormalizer<Record> responseNormalizer, CacheHeaders cacheHeaders);

    <R> R writeTransaction(Transaction<WriteableStore, R> transaction);
}
