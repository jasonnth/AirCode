package com.apollographql.apollo.internal.interceptor;

import com.apollographql.apollo.CustomTypeAdapter;
import com.apollographql.apollo.api.C3107Operation;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ScalarType;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.ApolloStore;
import com.apollographql.apollo.cache.normalized.CacheControl;
import com.apollographql.apollo.cache.normalized.Record;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack;
import com.apollographql.apollo.interceptor.ApolloInterceptor.InterceptorResponse;
import com.apollographql.apollo.interceptor.ApolloInterceptorChain;
import com.apollographql.apollo.internal.cache.normalized.ResponseNormalizer;
import com.apollographql.apollo.internal.cache.normalized.Transaction;
import com.apollographql.apollo.internal.cache.normalized.WriteableStore;
import com.apollographql.apollo.internal.util.ApolloLogger;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public final class ApolloCacheInterceptor implements ApolloInterceptor {
    /* access modifiers changed from: private */
    public final ApolloStore apolloStore;
    private final CacheControl cacheControl;
    /* access modifiers changed from: private */
    public final CacheHeaders cacheHeaders;
    private final Map<ScalarType, CustomTypeAdapter> customTypeAdapters;
    private final ExecutorService dispatcher;
    /* access modifiers changed from: private */
    public final ApolloLogger logger;
    private final ResponseFieldMapper responseFieldMapper;

    public ApolloCacheInterceptor(ApolloStore apolloStore2, CacheControl cacheControl2, CacheHeaders cacheHeaders2, ResponseFieldMapper responseFieldMapper2, Map<ScalarType, CustomTypeAdapter> customTypeAdapters2, ExecutorService dispatcher2, ApolloLogger logger2) {
        this.apolloStore = (ApolloStore) Utils.checkNotNull(apolloStore2, "cache == null");
        this.cacheControl = (CacheControl) Utils.checkNotNull(cacheControl2, "cacheControl == null");
        this.cacheHeaders = (CacheHeaders) Utils.checkNotNull(cacheHeaders2, "cacheHeaders == null");
        this.responseFieldMapper = (ResponseFieldMapper) Utils.checkNotNull(responseFieldMapper2, "responseFieldMapper == null");
        this.customTypeAdapters = (Map) Utils.checkNotNull(customTypeAdapters2, "customTypeAdapters == null");
        this.dispatcher = (ExecutorService) Utils.checkNotNull(dispatcher2, "dispatcher == null");
        this.logger = (ApolloLogger) Utils.checkNotNull(logger2, "logger == null");
    }

    public void interceptAsync(C3107Operation operation, ApolloInterceptorChain chain, ExecutorService dispatcher2, CallBack callBack) {
        final C3107Operation operation2 = operation;
        final CallBack callBack2 = callBack;
        final ApolloInterceptorChain apolloInterceptorChain = chain;
        final ExecutorService executorService = dispatcher2;
        dispatcher2.execute(new Runnable() {
            public void run() {
                InterceptorResponse cachedResponse = ApolloCacheInterceptor.this.resolveCacheFirstResponse(operation2);
                if (cachedResponse != null) {
                    callBack2.onResponse(cachedResponse);
                } else {
                    apolloInterceptorChain.proceedAsync(executorService, new CallBack() {
                        public void onResponse(InterceptorResponse response) {
                            callBack2.onResponse(ApolloCacheInterceptor.this.handleNetworkResponse(operation2, response));
                        }

                        public void onFailure(ApolloException e) {
                            InterceptorResponse response = ApolloCacheInterceptor.this.resolveNetworkFirstCacheResponse(operation2);
                            if (response != null) {
                                ApolloCacheInterceptor.this.logger.mo27165d(e, "Failed to fetch network response for operation %s, return cached one", operation2);
                                callBack2.onResponse(response);
                                return;
                            }
                            callBack2.onFailure(e);
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public InterceptorResponse resolveCacheFirstResponse(C3107Operation operation) {
        if (this.cacheControl == CacheControl.CACHE_ONLY || this.cacheControl == CacheControl.CACHE_FIRST) {
            ResponseNormalizer<Record> responseNormalizer = this.apolloStore.cacheResponseNormalizer();
            Response cachedResponse = this.apolloStore.read(operation, this.responseFieldMapper, responseNormalizer, this.cacheHeaders);
            if (this.cacheControl == CacheControl.CACHE_ONLY || cachedResponse.data() != null) {
                this.logger.mo27164d("Cache HIT for operation %s", operation);
                return new InterceptorResponse(null, cachedResponse, responseNormalizer.records());
            }
        }
        this.logger.mo27164d("Cache MISS for operation %s", operation);
        return null;
    }

    /* access modifiers changed from: private */
    public InterceptorResponse handleNetworkResponse(C3107Operation operation, InterceptorResponse networkResponse) {
        if ((!networkResponse.httpResponse.isPresent() || !((okhttp3.Response) networkResponse.httpResponse.get()).isSuccessful()) && this.cacheControl != CacheControl.NETWORK_ONLY) {
            ResponseNormalizer<Record> responseNormalizer = this.apolloStore.cacheResponseNormalizer();
            Response cachedResponse = this.apolloStore.read(operation, this.responseFieldMapper, responseNormalizer, this.cacheHeaders);
            if (cachedResponse.data() != null) {
                return new InterceptorResponse((okhttp3.Response) networkResponse.httpResponse.get(), cachedResponse, responseNormalizer.records());
            }
        }
        cacheResponse(networkResponse);
        return networkResponse;
    }

    private void cacheResponse(InterceptorResponse networkResponse) {
        final Optional<Collection<Record>> records = networkResponse.cacheRecords;
        if (records.isPresent()) {
            try {
                final Set<String> changedKeys = (Set) this.apolloStore.writeTransaction(new Transaction<WriteableStore, Set<String>>() {
                    public Set<String> execute(WriteableStore cache) {
                        return cache.merge((Collection) records.get(), ApolloCacheInterceptor.this.cacheHeaders);
                    }
                });
                this.dispatcher.execute(new Runnable() {
                    public void run() {
                        try {
                            ApolloCacheInterceptor.this.apolloStore.publish(changedKeys);
                        } catch (Exception e) {
                            ApolloCacheInterceptor.this.logger.mo27166e("Failed to publish cache changes", e);
                        }
                    }
                });
            } catch (Exception e) {
                this.logger.mo27166e("Failed to cache operation response", e);
            }
        }
    }

    /* access modifiers changed from: private */
    public InterceptorResponse resolveNetworkFirstCacheResponse(C3107Operation operation) {
        if (this.cacheControl == CacheControl.NETWORK_FIRST) {
            ResponseNormalizer<Record> responseNormalizer = this.apolloStore.cacheResponseNormalizer();
            Response cachedResponse = this.apolloStore.read(operation, this.responseFieldMapper, responseNormalizer, this.cacheHeaders);
            if (cachedResponse.data() != null) {
                this.logger.mo27164d("Cache HIT for operation %s", operation);
                return new InterceptorResponse(null, cachedResponse, responseNormalizer.records());
            }
        }
        this.logger.mo27164d("Cache MISS for operation %s", operation);
        return null;
    }
}
