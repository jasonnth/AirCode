package com.airbnb.android.core.requests.base;

import android.util.Log;
import com.airbnb.airrequest.AirRequest;
import com.airbnb.airrequest.AirRequest.Builder;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.Interceptor;
import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.SharedPrefsHelper;

public final class AirRequestHeadersInterceptor implements Interceptor {
    private static final String TAG = AirRequestHeadersInterceptor.class.getSimpleName();
    private final SharedPrefsHelper sharedPrefsHelper;

    public AirRequestHeadersInterceptor(SharedPrefsHelper sharedPrefsHelper2) {
        this.sharedPrefsHelper = sharedPrefsHelper2;
    }

    public <T> void updateSID(AirResponse<T> airResponse) {
        if (airResponse.raw().networkResponse() != null) {
            String secureIdentifier = airResponse.headers() != null ? airResponse.headers().get(ApiRequestHeadersInterceptor.HEADER_SECURE_IDENTIFIER_KEY) : null;
            if (secureIdentifier != null && !secureIdentifier.equals(this.sharedPrefsHelper.getSecureIdentifier())) {
                this.sharedPrefsHelper.setSecureIdentifier(secureIdentifier);
                if (BuildHelper.isDevelopmentBuild()) {
                    Log.d(TAG, "X-Airbnb-SID changed to: " + secureIdentifier);
                }
            }
        }
    }

    public AirRequest intercept(AirRequest request) {
        Builder builder = request.toBuilder();
        if (request.isPrefetch()) {
            builder.addHeader(ApiRequestHeadersInterceptor.HEADER_PREFETCH, "true");
        }
        String contentType = request.getContentType();
        if (contentType != null) {
            builder.addHeader(ApiRequestHeadersInterceptor.HEADER_CONTENT_TYPE, contentType);
        }
        return builder.build();
    }
}
