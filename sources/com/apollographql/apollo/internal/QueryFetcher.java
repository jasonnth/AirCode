package com.apollographql.apollo.internal;

import com.apollographql.apollo.ApolloCall.Callback;
import com.apollographql.apollo.CustomTypeAdapter;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ScalarType;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.http.HttpCachePolicy;
import com.apollographql.apollo.cache.normalized.ApolloStore;
import com.apollographql.apollo.cache.normalized.CacheControl;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.internal.util.ApolloLogger;
import com.squareup.moshi.Moshi;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.Call.Factory;
import okhttp3.HttpUrl;

final class QueryFetcher {
    private final List<RealApolloCall> calls;
    private volatile boolean canceled;
    private final AtomicBoolean executed = new AtomicBoolean();
    /* access modifiers changed from: private */
    public final ApolloLogger logger;

    static final class Builder {
        ApolloStore apolloStore;
        List<ApolloInterceptor> applicationInterceptors;
        Map<ScalarType, CustomTypeAdapter> customTypeAdapters;
        ExecutorService dispatcher;
        Factory httpCallFactory;
        ApolloLogger logger;
        Moshi moshi;
        List<Query> queries;
        ResponseFieldMapperFactory responseFieldMapperFactory;
        HttpUrl serverUrl;

        /* access modifiers changed from: 0000 */
        public Builder queries(List<Query> queries2) {
            this.queries = queries2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder serverUrl(HttpUrl serverUrl2) {
            this.serverUrl = serverUrl2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder httpCallFactory(Factory httpCallFactory2) {
            this.httpCallFactory = httpCallFactory2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder moshi(Moshi moshi2) {
            this.moshi = moshi2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder responseFieldMapperFactory(ResponseFieldMapperFactory responseFieldMapperFactory2) {
            this.responseFieldMapperFactory = responseFieldMapperFactory2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder customTypeAdapters(Map<ScalarType, CustomTypeAdapter> customTypeAdapters2) {
            this.customTypeAdapters = customTypeAdapters2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder apolloStore(ApolloStore apolloStore2) {
            this.apolloStore = apolloStore2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder dispatcher(ExecutorService dispatcher2) {
            this.dispatcher = dispatcher2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder logger(ApolloLogger logger2) {
            this.logger = logger2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder applicationInterceptors(List<ApolloInterceptor> applicationInterceptors2) {
            this.applicationInterceptors = applicationInterceptors2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public QueryFetcher build() {
            return new QueryFetcher(this);
        }

        private Builder() {
        }
    }

    interface OnFetchCompleteCallback {
        void onFetchComplete();
    }

    static Builder builder() {
        return new Builder();
    }

    QueryFetcher(Builder builder) {
        this.logger = builder.logger;
        this.calls = new ArrayList(builder.queries.size());
        for (Query query : builder.queries) {
            this.calls.add(RealApolloCall.builder().operation(query).serverUrl(builder.serverUrl).httpCallFactory(builder.httpCallFactory).moshi(builder.moshi).responseFieldMapperFactory(builder.responseFieldMapperFactory).customTypeAdapters(builder.customTypeAdapters).apolloStore(builder.apolloStore).httpCachePolicy(HttpCachePolicy.NETWORK_ONLY).cacheControl(CacheControl.NETWORK_ONLY).cacheHeaders(CacheHeaders.NONE).logger(builder.logger).applicationInterceptors(builder.applicationInterceptors).dispatcher(builder.dispatcher).build());
        }
    }

    /* access modifiers changed from: 0000 */
    public void refetchAsync(OnFetchCompleteCallback callback) {
        if (!this.executed.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed");
        }
        refetchAsync(0, (OnFetchCompleteCallback) Utils.checkNotNull(callback, "callback == null"));
    }

    /* access modifiers changed from: private */
    public void refetchAsync(final int nextCallIndex, final OnFetchCompleteCallback callback) {
        final RealApolloCall call = nextCallIndex < this.calls.size() ? (RealApolloCall) this.calls.get(nextCallIndex) : null;
        if (call == null) {
            callback.onFetchComplete();
        } else if (!this.canceled) {
            call.enqueue(new Callback() {
                public void onResponse(Response response) {
                    QueryFetcher.this.refetchAsync(nextCallIndex + 1, callback);
                }

                public void onFailure(ApolloException e) {
                    if (QueryFetcher.this.logger != null) {
                        QueryFetcher.this.logger.mo27167e(e, "Failed to fetch query: %s", call.operation);
                    }
                    QueryFetcher.this.refetchAsync(nextCallIndex + 1, callback);
                }
            });
        }
    }
}
