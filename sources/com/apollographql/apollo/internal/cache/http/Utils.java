package com.apollographql.apollo.internal.cache.http;

import android.support.p000v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.util.Date;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpDate;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

final class Utils {
    static final ResponseBody EMPTY_RESPONSE = ResponseBody.create((MediaType) null, new byte[0]);

    static Response strip(Response response) {
        if (response == null || response.body() == null) {
            return response;
        }
        return response.newBuilder().body(null).networkResponse(null).cacheResponse(null).build();
    }

    static Response withServedDateHeader(Response response) throws IOException {
        return response.newBuilder().addHeader("X-APOLLO-SERVED-DATE", HttpDate.format(new Date())).build();
    }

    static boolean isPrefetchResponse(Request request) {
        return Boolean.TRUE.toString().equalsIgnoreCase(request.header("X-APOLLO-PREFETCH"));
    }

    static boolean shouldSkipCache(Request request) {
        String cacheKey = request.header("X-APOLLO-CACHE-KEY");
        return cacheKey == null || cacheKey.isEmpty() || fetchStrategy(request) == null;
    }

    static boolean shouldSkipNetwork(Request request) {
        String cacheKey = request.header("X-APOLLO-CACHE-KEY");
        return cacheKey != null && !cacheKey.isEmpty() && fetchStrategy(request) == HttpCacheFetchStrategy.CACHE_ONLY;
    }

    static boolean isNetworkOnly(Request request) {
        return fetchStrategy(request) == HttpCacheFetchStrategy.NETWORK_ONLY;
    }

    static boolean isNetworkFirst(Request request) {
        return fetchStrategy(request) == HttpCacheFetchStrategy.NETWORK_FIRST;
    }

    static boolean shouldExpireAfterRead(Request request) {
        return Boolean.TRUE.toString().equalsIgnoreCase(request.header("X-APOLLO-EXPIRE-AFTER-READ"));
    }

    static Response unsatisfiableCacheRequest(Request request) {
        return new Builder().request(request).protocol(Protocol.HTTP_1_1).code(504).message("Unsatisfiable Request (cache-only)").body(EMPTY_RESPONSE).sentRequestAtMillis(-1).receivedResponseAtMillis(System.currentTimeMillis()).build();
    }

    static void copyResponseBody(Response response, Sink sink) throws IOException {
        BufferedSource responseBodySource = response.body().source();
        BufferedSink cacheResponseBody = Okio.buffer(sink);
        while (responseBodySource.read(cacheResponseBody.buffer(), PlaybackStateCompat.ACTION_PLAY_FROM_URI) > 0) {
            cacheResponseBody.emit();
        }
        closeQuietly((Source) responseBodySource);
    }

    static void closeQuietly(Source source) {
        try {
            source.close();
        } catch (Exception e) {
        }
    }

    static void closeQuietly(Response response) {
        if (response != null) {
            try {
                response.close();
            } catch (Exception e) {
            }
        }
    }

    static boolean isStale(Request request, Response response) {
        String timeoutStr = request.header("X-APOLLO-EXPIRE-TIMEOUT");
        String servedDateStr = response.header("X-APOLLO-SERVED-DATE");
        if (servedDateStr == null || timeoutStr == null) {
            return true;
        }
        long timeout = Long.parseLong(timeoutStr);
        if (timeout == 0) {
            return false;
        }
        Date servedDate = HttpDate.parse(servedDateStr);
        long now = System.currentTimeMillis();
        if (servedDate == null || now - servedDate.getTime() > timeout) {
            return true;
        }
        return false;
    }

    private static HttpCacheFetchStrategy fetchStrategy(Request request) {
        HttpCacheFetchStrategy[] values;
        String fetchStrategyHeader = request.header("X-APOLLO-CACHE-FETCH-STRATEGY");
        if (fetchStrategyHeader == null || fetchStrategyHeader.isEmpty()) {
            return null;
        }
        for (HttpCacheFetchStrategy fetchStrategy : HttpCacheFetchStrategy.values()) {
            if (fetchStrategy.name().equals(fetchStrategyHeader)) {
                return fetchStrategy;
            }
        }
        return null;
    }
}
