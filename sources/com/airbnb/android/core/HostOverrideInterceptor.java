package com.airbnb.android.core;

import com.airbnb.android.core.utils.NetworkUtil;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;

public final class HostOverrideInterceptor implements Interceptor {
    public static final String HOST_OVERRIDE_HEADER = "X-Host-Override";

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String hostOverride = request.header(HOST_OVERRIDE_HEADER);
        if (hostOverride != null) {
            request = NetworkUtil.overrideHost(request, hostOverride).newBuilder().removeHeader(HOST_OVERRIDE_HEADER).build();
        }
        return chain.proceed(request);
    }
}
