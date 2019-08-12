package com.apollographql.apollo.interceptor;

import com.apollographql.apollo.api.C3107Operation;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.cache.normalized.Record;
import com.apollographql.apollo.exception.ApolloException;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import okhttp3.Response;

public interface ApolloInterceptor {

    public interface CallBack {
        void onFailure(ApolloException apolloException);

        void onResponse(InterceptorResponse interceptorResponse);
    }

    public static final class InterceptorResponse {
        public final Optional<Collection<Record>> cacheRecords;
        public final Optional<Response> httpResponse;
        public final Optional<com.apollographql.apollo.api.Response> parsedResponse;

        public InterceptorResponse(Response httpResponse2) {
            this(httpResponse2, null, null);
        }

        public InterceptorResponse(Response httpResponse2, com.apollographql.apollo.api.Response parsedResponse2, Collection<Record> cacheRecords2) {
            this.httpResponse = Optional.fromNullable(httpResponse2);
            this.parsedResponse = Optional.fromNullable(parsedResponse2);
            this.cacheRecords = Optional.fromNullable(cacheRecords2);
        }
    }

    void interceptAsync(C3107Operation operation, ApolloInterceptorChain apolloInterceptorChain, ExecutorService executorService, CallBack callBack);
}
