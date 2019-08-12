package com.apollographql.apollo;

import com.apollographql.apollo.api.C3107Operation;
import com.apollographql.apollo.api.C3107Operation.Data;
import com.apollographql.apollo.api.C3107Operation.Variables;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.ScalarType;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.http.HttpCachePolicy;
import com.apollographql.apollo.cache.http.HttpCachePolicy.Policy;
import com.apollographql.apollo.cache.http.HttpCacheStore;
import com.apollographql.apollo.cache.normalized.ApolloStore;
import com.apollographql.apollo.cache.normalized.CacheControl;
import com.apollographql.apollo.cache.normalized.CacheKeyResolver;
import com.apollographql.apollo.cache.normalized.NormalizedCacheFactory;
import com.apollographql.apollo.cache.normalized.RecordFieldAdapter;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.internal.RealApolloCall;
import com.apollographql.apollo.internal.ResponseFieldMapperFactory;
import com.apollographql.apollo.internal.cache.http.HttpCache;
import com.apollographql.apollo.internal.cache.normalized.RealApolloStore;
import com.apollographql.apollo.internal.util.ApolloLogger;
import com.squareup.moshi.Moshi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Call.Factory;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public final class ApolloClient {
    private final ApolloStore apolloStore;
    private final List<ApolloInterceptor> applicationInterceptors;
    private final Map<ScalarType, CustomTypeAdapter> customTypeAdapters;
    private final CacheControl defaultCacheControl;
    private final CacheHeaders defaultCacheHeaders;
    private final Policy defaultHttpCachePolicy;
    private final ExecutorService dispatcher;
    private final HttpCache httpCache;
    private final Factory httpCallFactory;
    private final ApolloLogger logger;
    private final Moshi moshi;
    private final ResponseFieldMapperFactory responseFieldMapperFactory;
    private final HttpUrl serverUrl;

    public static class Builder {
        ApolloLogger apolloLogger;
        ApolloStore apolloStore;
        final List<ApolloInterceptor> applicationInterceptors;
        Optional<NormalizedCacheFactory> cacheFactory;
        Optional<CacheKeyResolver> cacheKeyResolver;
        final Map<ScalarType, CustomTypeAdapter> customTypeAdapters;
        CacheControl defaultCacheControl;
        CacheHeaders defaultCacheHeaders;
        Policy defaultHttpCachePolicy;
        ExecutorService dispatcher;
        HttpCache httpCache;
        HttpCacheStore httpCacheStore;
        Optional<Logger> logger;
        Moshi moshi;
        private final com.squareup.moshi.Moshi.Builder moshiBuilder;
        OkHttpClient okHttpClient;
        HttpUrl serverUrl;

        private Builder() {
            this.apolloStore = ApolloStore.NO_APOLLO_STORE;
            this.cacheFactory = Optional.absent();
            this.cacheKeyResolver = Optional.absent();
            this.defaultHttpCachePolicy = HttpCachePolicy.NETWORK_ONLY;
            this.defaultCacheControl = CacheControl.CACHE_FIRST;
            this.defaultCacheHeaders = CacheHeaders.NONE;
            this.customTypeAdapters = new LinkedHashMap();
            this.moshiBuilder = new com.squareup.moshi.Moshi.Builder();
            this.logger = Optional.absent();
            this.applicationInterceptors = new ArrayList();
        }

        public Builder okHttpClient(OkHttpClient okHttpClient2) {
            this.okHttpClient = (OkHttpClient) Utils.checkNotNull(okHttpClient2, "okHttpClient is null");
            return this;
        }

        public Builder serverUrl(String serverUrl2) {
            this.serverUrl = HttpUrl.parse((String) Utils.checkNotNull(serverUrl2, "serverUrl == null"));
            return this;
        }

        public Builder normalizedCache(NormalizedCacheFactory normalizedCacheFactory, CacheKeyResolver keyResolver) {
            this.cacheFactory = Optional.fromNullable(Utils.checkNotNull(normalizedCacheFactory, "normalizedCacheFactory == null"));
            this.cacheKeyResolver = Optional.fromNullable(Utils.checkNotNull(keyResolver, "cacheKeyResolver == null"));
            return this;
        }

        public ApolloClient build() {
            Utils.checkNotNull(this.okHttpClient, "okHttpClient is null");
            Utils.checkNotNull(this.serverUrl, "serverUrl is null");
            this.apolloLogger = new ApolloLogger(this.logger);
            this.moshi = this.moshiBuilder.build();
            if (this.httpCacheStore != null) {
                this.httpCache = new HttpCache(this.httpCacheStore, this.apolloLogger);
                this.okHttpClient = this.okHttpClient.newBuilder().addInterceptor(this.httpCache.interceptor()).build();
            }
            if (this.cacheFactory.isPresent() && this.cacheKeyResolver.isPresent()) {
                this.apolloStore = new RealApolloStore(((NormalizedCacheFactory) this.cacheFactory.get()).createNormalizedCache(RecordFieldAdapter.create(this.moshi)), (CacheKeyResolver) this.cacheKeyResolver.get(), this.customTypeAdapters, this.apolloLogger);
            }
            if (this.dispatcher == null) {
                this.dispatcher = defaultDispatcher();
            }
            return new ApolloClient(this);
        }

        private ExecutorService defaultDispatcher() {
            return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactory() {
                public Thread newThread(Runnable runnable) {
                    return new Thread(runnable, "Apollo Dispatcher");
                }
            });
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private ApolloClient(Builder builder) {
        this.responseFieldMapperFactory = new ResponseFieldMapperFactory();
        this.serverUrl = builder.serverUrl;
        this.httpCallFactory = builder.okHttpClient;
        this.httpCache = builder.httpCache;
        this.apolloStore = builder.apolloStore;
        this.customTypeAdapters = builder.customTypeAdapters;
        this.moshi = builder.moshi;
        this.dispatcher = builder.dispatcher;
        this.defaultHttpCachePolicy = builder.defaultHttpCachePolicy;
        this.defaultCacheHeaders = builder.defaultCacheHeaders;
        this.defaultCacheControl = builder.defaultCacheControl;
        this.logger = builder.apolloLogger;
        this.applicationInterceptors = builder.applicationInterceptors;
    }

    public <D extends Data, T, V extends Variables> ApolloQueryCall<T> query(Query<D, T, V> query) {
        return newCall(query);
    }

    private <D extends Data, T, V extends Variables> RealApolloCall<T> newCall(C3107Operation<D, T, V> operation) {
        return RealApolloCall.builder().operation(operation).serverUrl(this.serverUrl).httpCallFactory(this.httpCallFactory).httpCache(this.httpCache).httpCachePolicy(this.defaultHttpCachePolicy).moshi(this.moshi).responseFieldMapperFactory(this.responseFieldMapperFactory).customTypeAdapters(this.customTypeAdapters).apolloStore(this.apolloStore).cacheControl(this.defaultCacheControl).cacheHeaders(this.defaultCacheHeaders).dispatcher(this.dispatcher).logger(this.logger).applicationInterceptors(this.applicationInterceptors).refetchQueries(Collections.emptyList()).refetchQueryNames(Collections.emptyList()).build();
    }
}
