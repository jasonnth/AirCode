package com.airbnb.airrequest;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;

public class CacheControlInterceptor implements Interceptor {
    private static final String CACHE_CONTROL = "Cache-Control";

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String cacheControlHeader = "X-Response-Cache-Control";
        String requestCacheControlValue = request.header(cacheControlHeader);
        Response originalResponse = chain.proceed(request.newBuilder().headers(request.headers().newBuilder().removeAll(cacheControlHeader).build()).build());
        if (requestCacheControlValue == null) {
            return originalResponse;
        }
        return originalResponse.newBuilder().header(CACHE_CONTROL, requestCacheControlValue).build();
    }
}
