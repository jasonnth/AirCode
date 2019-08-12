package com.airbnb.android.core.net;

import com.airbnb.airrequest.BaseUrl;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;

public class ApiHostPlaceholderInterceptor implements Interceptor {
    private static final String HOST_PLACEHOLDER = "airbnbapi";
    private final BaseUrl apiBaseUrl;

    public ApiHostPlaceholderInterceptor(BaseUrl apiBaseUrl2) {
        this.apiBaseUrl = apiBaseUrl2;
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (request.url().host().equals(HOST_PLACEHOLDER)) {
            return chain.proceed(request.newBuilder().url(request.url().newBuilder().host(this.apiBaseUrl.url().host()).build()).build());
        }
        return chain.proceed(request);
    }
}
