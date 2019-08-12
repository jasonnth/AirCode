package com.airbnb.airrequest;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;

public class CacheRevalidationInterceptor implements Interceptor {
    private static final String WARNING_RESPONSE_IS_STALE = "110";

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response originalResponse = chain.proceed(request);
        if (shouldSkipInterceptor(request, originalResponse)) {
            return originalResponse;
        }
        try {
            Response retriedResponse = chain.proceed(removeCacheHeaders(request));
            if (retriedResponse == null || !retriedResponse.isSuccessful()) {
                return originalResponse;
            }
            return retriedResponse;
        } catch (IOException e) {
            return originalResponse;
        }
    }

    private boolean shouldSkipInterceptor(Request request, Response response) {
        if (response == null) {
            return true;
        }
        String returnStrategyHeader = request.header("X-Return-Strategy");
        if (returnStrategyHeader != null && returnStrategyHeader.equals("double")) {
            return true;
        }
        for (String warningHeader : response.headers("Warning")) {
            if (warningHeader.startsWith(WARNING_RESPONSE_IS_STALE)) {
                return false;
            }
        }
        return true;
    }

    private Request removeCacheHeaders(Request request) {
        return request.newBuilder().headers(request.headers().newBuilder().removeAll("Cache-Control").build()).build();
    }
}
