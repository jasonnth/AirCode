package com.apollographql.apollo.internal;

import com.apollographql.apollo.ApolloCall.Callback;
import com.apollographql.apollo.ApolloMutationCall;
import com.apollographql.apollo.ApolloQueryCall;
import com.apollographql.apollo.CustomTypeAdapter;
import com.apollographql.apollo.api.C3107Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ScalarType;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.http.HttpCachePolicy.Policy;
import com.apollographql.apollo.cache.normalized.ApolloStore;
import com.apollographql.apollo.cache.normalized.CacheControl;
import com.apollographql.apollo.exception.ApolloCanceledException;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.exception.ApolloHttpException;
import com.apollographql.apollo.exception.ApolloNetworkException;
import com.apollographql.apollo.exception.ApolloParseException;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack;
import com.apollographql.apollo.interceptor.ApolloInterceptor.InterceptorResponse;
import com.apollographql.apollo.interceptor.ApolloInterceptorChain;
import com.apollographql.apollo.internal.cache.http.HttpCache;
import com.apollographql.apollo.internal.interceptor.ApolloCacheInterceptor;
import com.apollographql.apollo.internal.interceptor.ApolloParseInterceptor;
import com.apollographql.apollo.internal.interceptor.ApolloServerInterceptor;
import com.apollographql.apollo.internal.interceptor.RealApolloInterceptorChain;
import com.apollographql.apollo.internal.util.ApolloLogger;
import com.squareup.moshi.Moshi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.Call.Factory;
import okhttp3.HttpUrl;

public final class RealApolloCall<T> implements ApolloMutationCall<T>, ApolloQueryCall<T> {
    final ApolloStore apolloStore;
    final List<ApolloInterceptor> applicationInterceptors;
    final CacheControl cacheControl;
    final CacheHeaders cacheHeaders;
    volatile boolean canceled;
    final Map<ScalarType, CustomTypeAdapter> customTypeAdapters;
    final ExecutorService dispatcher;
    final AtomicBoolean executed;
    final HttpCache httpCache;
    final Policy httpCachePolicy;
    final Factory httpCallFactory;
    final ApolloInterceptorChain interceptorChain;
    final ApolloLogger logger;
    final Moshi moshi;
    final C3107Operation operation;
    final Optional<QueryFetcher> queryFetcher;
    final List<Query> refetchQueries;
    final List<OperationName> refetchQueryNames;
    final ResponseFieldMapperFactory responseFieldMapperFactory;
    final HttpUrl serverUrl;

    public static final class Builder<T> {
        ApolloStore apolloStore;
        List<ApolloInterceptor> applicationInterceptors;
        CacheControl cacheControl;
        CacheHeaders cacheHeaders;
        Map<ScalarType, CustomTypeAdapter> customTypeAdapters;
        ExecutorService dispatcher;
        HttpCache httpCache;
        Policy httpCachePolicy;
        Factory httpCallFactory;
        ApolloLogger logger;
        Moshi moshi;
        C3107Operation operation;
        List<Query> refetchQueries = Collections.emptyList();
        List<OperationName> refetchQueryNames;
        ResponseFieldMapperFactory responseFieldMapperFactory;
        HttpUrl serverUrl;

        public Builder<T> operation(C3107Operation operation2) {
            this.operation = operation2;
            return this;
        }

        public Builder<T> serverUrl(HttpUrl serverUrl2) {
            this.serverUrl = serverUrl2;
            return this;
        }

        public Builder<T> httpCallFactory(Factory httpCallFactory2) {
            this.httpCallFactory = httpCallFactory2;
            return this;
        }

        public Builder<T> httpCache(HttpCache httpCache2) {
            this.httpCache = httpCache2;
            return this;
        }

        public Builder<T> httpCachePolicy(Policy httpCachePolicy2) {
            this.httpCachePolicy = httpCachePolicy2;
            return this;
        }

        public Builder<T> moshi(Moshi moshi2) {
            this.moshi = moshi2;
            return this;
        }

        public Builder<T> responseFieldMapperFactory(ResponseFieldMapperFactory responseFieldMapperFactory2) {
            this.responseFieldMapperFactory = responseFieldMapperFactory2;
            return this;
        }

        public Builder<T> customTypeAdapters(Map<ScalarType, CustomTypeAdapter> customTypeAdapters2) {
            this.customTypeAdapters = customTypeAdapters2;
            return this;
        }

        public Builder<T> apolloStore(ApolloStore apolloStore2) {
            this.apolloStore = apolloStore2;
            return this;
        }

        public Builder<T> cacheControl(CacheControl cacheControl2) {
            this.cacheControl = cacheControl2;
            return this;
        }

        public Builder<T> cacheHeaders(CacheHeaders cacheHeaders2) {
            this.cacheHeaders = cacheHeaders2;
            return this;
        }

        public Builder<T> dispatcher(ExecutorService dispatcher2) {
            this.dispatcher = dispatcher2;
            return this;
        }

        public Builder<T> logger(ApolloLogger logger2) {
            this.logger = logger2;
            return this;
        }

        public Builder<T> applicationInterceptors(List<ApolloInterceptor> applicationInterceptors2) {
            this.applicationInterceptors = applicationInterceptors2;
            return this;
        }

        public Builder<T> refetchQueryNames(List<OperationName> refetchQueryNames2) {
            this.refetchQueryNames = refetchQueryNames2;
            return this;
        }

        public Builder<T> refetchQueries(List<Query> refetchQueries2) {
            this.refetchQueries = refetchQueries2 != null ? new ArrayList<>(refetchQueries2) : Collections.emptyList();
            return this;
        }

        Builder() {
        }

        public RealApolloCall<T> build() {
            return new RealApolloCall<>(this);
        }
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    private RealApolloCall(Builder<T> builder) {
        this.executed = new AtomicBoolean();
        this.operation = builder.operation;
        this.serverUrl = builder.serverUrl;
        this.httpCallFactory = builder.httpCallFactory;
        this.httpCache = builder.httpCache;
        this.httpCachePolicy = builder.httpCachePolicy;
        this.moshi = builder.moshi;
        this.responseFieldMapperFactory = builder.responseFieldMapperFactory;
        this.customTypeAdapters = builder.customTypeAdapters;
        this.apolloStore = builder.apolloStore;
        this.cacheControl = builder.cacheControl;
        this.cacheHeaders = builder.cacheHeaders;
        this.dispatcher = builder.dispatcher;
        this.logger = builder.logger;
        this.applicationInterceptors = builder.applicationInterceptors;
        this.refetchQueryNames = builder.refetchQueryNames;
        this.refetchQueries = builder.refetchQueries;
        if (this.refetchQueries.isEmpty() || builder.apolloStore == null) {
            this.queryFetcher = Optional.absent();
        } else {
            this.queryFetcher = Optional.m1700of(QueryFetcher.builder().queries(builder.refetchQueries).serverUrl(builder.serverUrl).httpCallFactory(builder.httpCallFactory).moshi(builder.moshi).responseFieldMapperFactory(builder.responseFieldMapperFactory).customTypeAdapters(builder.customTypeAdapters).apolloStore(builder.apolloStore).dispatcher(builder.dispatcher).logger(builder.logger).applicationInterceptors(builder.applicationInterceptors).build());
        }
        this.interceptorChain = prepareInterceptorChain(this.operation);
    }

    public void enqueue(final Callback<T> callback) {
        if (!this.executed.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed");
        }
        this.interceptorChain.proceedAsync(this.dispatcher, new CallBack() {
            public void onResponse(final InterceptorResponse response) {
                if (callback != null) {
                    if (RealApolloCall.this.canceled) {
                        callback.onCanceledError(new ApolloCanceledException("Canceled"));
                    } else if (RealApolloCall.this.queryFetcher.isPresent()) {
                        ((QueryFetcher) RealApolloCall.this.queryFetcher.get()).refetchAsync(new OnFetchCompleteCallback() {
                            public void onFetchComplete() {
                                callback.onResponse((Response) response.parsedResponse.get());
                            }
                        });
                    } else {
                        callback.onResponse((Response) response.parsedResponse.get());
                    }
                }
            }

            public void onFailure(ApolloException e) {
                if (callback != null) {
                    if (RealApolloCall.this.canceled) {
                        callback.onCanceledError(new ApolloCanceledException("Canceled", e));
                    } else if (e instanceof ApolloHttpException) {
                        callback.onHttpError((ApolloHttpException) e);
                    } else if (e instanceof ApolloParseException) {
                        callback.onParseError((ApolloParseException) e);
                    } else if (e instanceof ApolloNetworkException) {
                        callback.onNetworkError((ApolloNetworkException) e);
                    } else {
                        callback.onFailure(e);
                    }
                }
            }
        });
    }

    public RealApolloCall<T> cacheControl(CacheControl cacheControl2) {
        if (!this.executed.get()) {
            return toBuilder().cacheControl((CacheControl) Utils.checkNotNull(cacheControl2, "cacheControl == null")).build();
        }
        throw new IllegalStateException("Already Executed");
    }

    public RealApolloCall<T> clone() {
        return toBuilder().build();
    }

    public Builder<T> toBuilder() {
        return builder().operation(this.operation).serverUrl(this.serverUrl).httpCallFactory(this.httpCallFactory).httpCache(this.httpCache).httpCachePolicy(this.httpCachePolicy).moshi(this.moshi).responseFieldMapperFactory(this.responseFieldMapperFactory).customTypeAdapters(this.customTypeAdapters).apolloStore(this.apolloStore).cacheControl(this.cacheControl).cacheHeaders(this.cacheHeaders).dispatcher(this.dispatcher).logger(this.logger).applicationInterceptors(this.applicationInterceptors).refetchQueryNames(this.refetchQueryNames).refetchQueries(this.refetchQueries);
    }

    private ApolloInterceptorChain prepareInterceptorChain(C3107Operation operation2) {
        List<ApolloInterceptor> interceptors = new ArrayList<>();
        Policy httpCachePolicy2 = operation2 instanceof Query ? this.httpCachePolicy : null;
        ResponseFieldMapper responseFieldMapper = this.responseFieldMapperFactory.create(operation2);
        interceptors.addAll(this.applicationInterceptors);
        interceptors.add(new ApolloCacheInterceptor(this.apolloStore, this.cacheControl, this.cacheHeaders, responseFieldMapper, this.customTypeAdapters, this.dispatcher, this.logger));
        interceptors.add(new ApolloParseInterceptor(this.httpCache, this.apolloStore.networkResponseNormalizer(), responseFieldMapper, this.customTypeAdapters, this.logger));
        interceptors.add(new ApolloServerInterceptor(this.serverUrl, this.httpCallFactory, httpCachePolicy2, false, this.moshi, this.logger));
        return new RealApolloInterceptorChain(operation2, interceptors);
    }
}
