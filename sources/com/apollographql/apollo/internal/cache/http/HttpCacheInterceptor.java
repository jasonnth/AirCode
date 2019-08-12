package com.apollographql.apollo.internal.cache.http;

import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.internal.util.ApolloLogger;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;

final class HttpCacheInterceptor implements Interceptor {
    private final HttpCache cache;
    private final ApolloLogger logger;

    HttpCacheInterceptor(HttpCache cache2, ApolloLogger logger2) {
        this.cache = (HttpCache) Utils.checkNotNull(cache2, "cache == null");
        this.logger = (ApolloLogger) Utils.checkNotNull(logger2, "logger == null");
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (Utils.shouldSkipCache(request)) {
            this.logger.mo27164d("Skip http cache for request: %s", request);
            return chain.proceed(request);
        } else if (Utils.shouldSkipNetwork(request)) {
            this.logger.mo27164d("Read http cache only for request: %s", request);
            return cacheOnlyResponse(request);
        } else if (Utils.isNetworkOnly(request)) {
            this.logger.mo27164d("Skip http cache network only request: %s", request);
            return networkOnly(request, chain);
        } else if (Utils.isNetworkFirst(request)) {
            this.logger.mo27164d("Network first for request: %s", request);
            return networkFirst(request, chain);
        } else {
            this.logger.mo27164d("Cache first for request: %s", request);
            return cacheFirst(request, chain);
        }
    }

    private Response cacheOnlyResponse(Request request) throws IOException {
        Response cacheResponse = cachedResponse(request);
        if (cacheResponse == null) {
            logCacheMiss(request);
            return Utils.unsatisfiableCacheRequest(request);
        }
        logCacheHit(request);
        return cacheResponse.newBuilder().cacheResponse(Utils.strip(cacheResponse)).build();
    }

    private Response networkOnly(Request request, Chain chain) throws IOException {
        String cacheKey = request.header("X-APOLLO-CACHE-KEY");
        Response networkResponse = Utils.withServedDateHeader(chain.proceed(request));
        if (Utils.isPrefetchResponse(request)) {
            return prefetch(networkResponse, cacheKey);
        }
        if (!networkResponse.isSuccessful()) {
            return networkResponse;
        }
        this.logger.mo27164d("Network success, skip http cache for request: %s, with cache key: %s", request, cacheKey);
        return this.cache.cacheProxy(networkResponse, cacheKey);
    }

    private Response networkFirst(Request request, Chain chain) throws IOException {
        IOException rethrowException;
        String cacheKey = request.header("X-APOLLO-CACHE-KEY");
        Response networkResponse = null;
        try {
            networkResponse = Utils.withServedDateHeader(chain.proceed(request));
            if (networkResponse.isSuccessful()) {
                this.logger.mo27164d("Network success, skip http cache for request: %s, with cache key: %s", request, cacheKey);
                return this.cache.cacheProxy(networkResponse, cacheKey);
            }
            rethrowException = null;
            Response cachedResponse = cachedResponse(request);
            if (cachedResponse == null) {
                logCacheMiss(request);
                if (rethrowException == null) {
                    return networkResponse;
                }
                throw rethrowException;
            }
            logCacheHit(request);
            return cachedResponse.newBuilder().cacheResponse(Utils.strip(cachedResponse)).networkResponse(Utils.strip(networkResponse)).request(request).build();
        } catch (IOException e) {
            rethrowException = e;
        }
    }

    private Response cacheFirst(Request request, Chain chain) throws IOException {
        Response cachedResponse = cachedResponse(request);
        if (cachedResponse != null) {
            logCacheHit(request);
            return cachedResponse.newBuilder().cacheResponse(Utils.strip(cachedResponse)).request(request).build();
        }
        logCacheMiss(request);
        String cacheKey = request.header("X-APOLLO-CACHE-KEY");
        Response networkResponse = Utils.withServedDateHeader(chain.proceed(request));
        if (Utils.isPrefetchResponse(request)) {
            return prefetch(networkResponse, cacheKey);
        }
        if (networkResponse.isSuccessful()) {
            return this.cache.cacheProxy(networkResponse, cacheKey);
        }
        return networkResponse;
    }

    /* JADX INFO: finally extract failed */
    private Response prefetch(Response networkResponse, String cacheKey) throws IOException {
        if (!networkResponse.isSuccessful()) {
            return networkResponse;
        }
        try {
            this.cache.write(networkResponse, cacheKey);
            networkResponse.close();
            Response cachedResponse = this.cache.read(cacheKey);
            if (cachedResponse != null) {
                return cachedResponse.newBuilder().networkResponse(Utils.strip(networkResponse)).build();
            }
            throw new IOException("failed to read prefetch cache response");
        } catch (Throwable th) {
            networkResponse.close();
            throw th;
        }
    }

    private Response cachedResponse(Request request) {
        Response cachedResponse = this.cache.read(request.header("X-APOLLO-CACHE-KEY"), Utils.shouldExpireAfterRead(request));
        if (cachedResponse == null) {
            return null;
        }
        if (!Utils.isStale(request, cachedResponse)) {
            return cachedResponse;
        }
        Utils.closeQuietly(cachedResponse);
        return null;
    }

    private void logCacheHit(Request request) {
        this.logger.mo27164d("Cache HIT for request: %s, with cache key: %s", request, request.header("X-APOLLO-CACHE-KEY"));
    }

    private void logCacheMiss(Request request) {
        this.logger.mo27164d("Cache MISS for request: %s, with cache key: %s", request, request.header("X-APOLLO-CACHE-KEY"));
    }
}
