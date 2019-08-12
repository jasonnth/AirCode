package com.apollographql.apollo.internal.interceptor;

import com.apollographql.apollo.CustomTypeAdapter;
import com.apollographql.apollo.api.C3107Operation;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ScalarType;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.exception.ApolloHttpException;
import com.apollographql.apollo.exception.ApolloParseException;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack;
import com.apollographql.apollo.interceptor.ApolloInterceptor.InterceptorResponse;
import com.apollographql.apollo.interceptor.ApolloInterceptorChain;
import com.apollographql.apollo.internal.cache.http.HttpCache;
import com.apollographql.apollo.internal.cache.normalized.ResponseNormalizer;
import com.apollographql.apollo.internal.util.ApolloLogger;
import java.io.Closeable;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import okhttp3.Response;

public final class ApolloParseInterceptor implements ApolloInterceptor {
    private final Map<ScalarType, CustomTypeAdapter> customTypeAdapters;
    private final HttpCache httpCache;
    private final ApolloLogger logger;
    private final ResponseNormalizer<Map<String, Object>> normalizer;
    private final ResponseFieldMapper responseFieldMapper;

    public ApolloParseInterceptor(HttpCache httpCache2, ResponseNormalizer<Map<String, Object>> normalizer2, ResponseFieldMapper responseFieldMapper2, Map<ScalarType, CustomTypeAdapter> customTypeAdapters2, ApolloLogger logger2) {
        this.httpCache = httpCache2;
        this.normalizer = normalizer2;
        this.responseFieldMapper = responseFieldMapper2;
        this.customTypeAdapters = customTypeAdapters2;
        this.logger = logger2;
    }

    public void interceptAsync(final C3107Operation operation, ApolloInterceptorChain chain, ExecutorService dispatcher, final CallBack callBack) {
        chain.proceedAsync(dispatcher, new CallBack() {
            public void onResponse(InterceptorResponse response) {
                try {
                    callBack.onResponse(ApolloParseInterceptor.this.parse(operation, (Response) response.httpResponse.get()));
                } catch (ApolloException e) {
                    onFailure(e);
                }
            }

            public void onFailure(ApolloException e) {
                callBack.onFailure(e);
            }
        });
    }

    /* access modifiers changed from: private */
    public InterceptorResponse parse(C3107Operation operation, Response httpResponse) throws ApolloHttpException, ApolloParseException {
        String cacheKey = httpResponse.request().header("X-APOLLO-CACHE-KEY");
        if (httpResponse.isSuccessful()) {
            try {
                com.apollographql.apollo.api.Response parsedResponse = new HttpResponseBodyParser(operation, this.responseFieldMapper, this.customTypeAdapters).parse(httpResponse.body(), this.normalizer);
                if (parsedResponse.hasErrors() && this.httpCache != null) {
                    this.httpCache.removeQuietly(cacheKey);
                }
                return new InterceptorResponse(httpResponse, parsedResponse, this.normalizer.records());
            } catch (Exception rethrown) {
                this.logger.mo27167e(rethrown, "Failed to parse network response for operation: %s", operation);
                closeQuietly(httpResponse);
                if (this.httpCache != null) {
                    this.httpCache.removeQuietly(cacheKey);
                }
                throw new ApolloParseException("Failed to parse http response", rethrown);
            }
        } else {
            this.logger.mo27166e("Failed to parse network response: %s", httpResponse);
            throw new ApolloHttpException(httpResponse);
        }
    }

    private static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }
}
