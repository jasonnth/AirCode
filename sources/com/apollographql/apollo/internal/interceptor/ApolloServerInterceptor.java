package com.apollographql.apollo.internal.interceptor;

import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.apollographql.apollo.api.C3107Operation;
import com.apollographql.apollo.api.C3107Operation.Variables;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.cache.http.HttpCachePolicy.Policy;
import com.apollographql.apollo.exception.ApolloNetworkException;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack;
import com.apollographql.apollo.interceptor.ApolloInterceptor.InterceptorResponse;
import com.apollographql.apollo.interceptor.ApolloInterceptorChain;
import com.apollographql.apollo.internal.util.ApolloLogger;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import okhttp3.Call;
import okhttp3.Call.Factory;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;

public final class ApolloServerInterceptor implements ApolloInterceptor {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    final Optional<Policy> cachePolicy;
    volatile Call httpCall;
    final Factory httpCallFactory;
    final ApolloLogger logger;
    final Moshi moshi;
    final boolean prefetch;
    final HttpUrl serverUrl;

    static final class OperationJsonAdapter extends JsonAdapter<C3107Operation> {
        private final Moshi moshi;

        OperationJsonAdapter(Moshi moshi2) {
            this.moshi = moshi2;
        }

        public void toJson(JsonWriter writer, C3107Operation value) throws IOException {
            writer.beginObject();
            writer.name("query").value(value.queryDocument().replaceAll("\\n", ""));
            Variables variables = value.variables();
            if (variables != null) {
                JsonAdapter<Variables> adapter = this.moshi.adapter(variables.getClass());
                writer.name("variables");
                adapter.toJson(writer, variables);
            }
            writer.endObject();
        }
    }

    public ApolloServerInterceptor(HttpUrl serverUrl2, Factory httpCallFactory2, Policy cachePolicy2, boolean prefetch2, Moshi moshi2, ApolloLogger logger2) {
        this.serverUrl = (HttpUrl) Utils.checkNotNull(serverUrl2, "serverUrl == null");
        this.httpCallFactory = (Factory) Utils.checkNotNull(httpCallFactory2, "httpCallFactory == null");
        this.cachePolicy = Optional.fromNullable(cachePolicy2);
        this.prefetch = prefetch2;
        this.moshi = (Moshi) Utils.checkNotNull(moshi2, "moshi == null");
        this.logger = (ApolloLogger) Utils.checkNotNull(logger2, "logger == null");
    }

    public void interceptAsync(final C3107Operation operation, ApolloInterceptorChain chain, ExecutorService dispatcher, final CallBack callBack) {
        dispatcher.execute(new Runnable() {
            public void run() {
                try {
                    ApolloServerInterceptor.this.httpCall = ApolloServerInterceptor.this.httpCall(operation);
                    ApolloServerInterceptor.this.httpCall.enqueue(new Callback() {
                        public void onFailure(Call call, IOException e) {
                            ApolloServerInterceptor.this.logger.mo27167e(e, "Failed to execute http call", new Object[0]);
                            callBack.onFailure(new ApolloNetworkException("Failed to execute http call", e));
                        }

                        public void onResponse(Call call, Response response) throws IOException {
                            callBack.onResponse(new InterceptorResponse(response));
                        }
                    });
                } catch (IOException e) {
                    ApolloServerInterceptor.this.logger.mo27167e(e, "Failed to prepare http call", new Object[0]);
                    callBack.onFailure(new ApolloNetworkException("Failed to prepare http call", e));
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public Call httpCall(C3107Operation operation) throws IOException {
        RequestBody requestBody = httpRequestBody(operation);
        Builder requestBuilder = new Builder().url(this.serverUrl).post(requestBody).header("Accept", "application/json").header(ApiRequestHeadersInterceptor.HEADER_CONTENT_TYPE, "application/json");
        if (this.cachePolicy.isPresent()) {
            Policy cachePolicy2 = (Policy) this.cachePolicy.get();
            requestBuilder = requestBuilder.header("X-APOLLO-CACHE-KEY", cacheKey(requestBody)).header("X-APOLLO-CACHE-FETCH-STRATEGY", cachePolicy2.fetchStrategy.name()).header("X-APOLLO-EXPIRE-TIMEOUT", String.valueOf(cachePolicy2.expireTimeoutMs())).header("X-APOLLO-EXPIRE-AFTER-READ", Boolean.toString(cachePolicy2.expireAfterRead)).header("X-APOLLO-PREFETCH", Boolean.toString(this.prefetch));
        }
        return this.httpCallFactory.newCall(requestBuilder.build());
    }

    private RequestBody httpRequestBody(C3107Operation operation) throws IOException {
        JsonAdapter<C3107Operation> adapter = new OperationJsonAdapter<>(this.moshi);
        Buffer buffer = new Buffer();
        adapter.toJson((BufferedSink) buffer, operation);
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
    }

    public static String cacheKey(RequestBody requestBody) throws IOException {
        Buffer hashBuffer = new Buffer();
        requestBody.writeTo(hashBuffer);
        return hashBuffer.readByteString().md5().hex();
    }
}
