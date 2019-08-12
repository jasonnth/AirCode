package com.airbnb.android.core;

import com.airbnb.airrequest.BaseUrl;
import com.airbnb.android.core.utils.NetworkUtil;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;

public class HostSelectionInterceptor implements Interceptor {
    private final BaseUrl baseUrl;
    private final boolean isDebugBuild;
    private final String prodApiEndpointUrl;

    public HostSelectionInterceptor(String prodApiEndpointUrl2, BaseUrl baseUrl2, boolean isDebugBuild2) {
        this.prodApiEndpointUrl = prodApiEndpointUrl2;
        this.baseUrl = baseUrl2;
        this.isDebugBuild = isDebugBuild2;
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!this.isDebugBuild || !this.prodApiEndpointUrl.equals(request.url().host())) {
            return chain.proceed(request);
        }
        return chain.proceed(NetworkUtil.overrideHost(request, this.baseUrl.url().toString()));
    }
}
