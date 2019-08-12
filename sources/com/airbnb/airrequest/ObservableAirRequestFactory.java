package com.airbnb.airrequest;

import com.airbnb.airrequest.AirRequest.RequestType;
import com.airbnb.airrequest.Interceptor.Factory;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.CacheControl;
import retrofit2.Method;
import retrofit2.ObservableRequest.Builder;
import retrofit2.Retrofit;

class ObservableAirRequestFactory {
    private final List<? extends Factory> interceptorFactories;
    private final Retrofit retrofit;

    ObservableAirRequestFactory(Retrofit retrofit3, List<? extends Factory> interceptorFactories2) {
        this.retrofit = retrofit3;
        this.interceptorFactories = interceptorFactories2;
    }

    /* access modifiers changed from: 0000 */
    public ObservableAirRequest newObservableRequest(AirRequest airRequest) {
        AirRequest newRequest = intercept(airRequest);
        Builder builder = new Builder(this.retrofit).headers(newRequest.getHeaders()).path(Utils.convertPathAndQueryParams(newRequest)).method(Method.valueOf(newRequest.getMethod().name())).responseType(Utils.responseType(newRequest.successResponseType())).body(Utils.convertBody(this.retrofit, newRequest));
        if (newRequest.getRequestType() == RequestType.FORM_URL) {
            builder.fields(Utils.convertFields(newRequest.getFields()));
        } else if (newRequest.getRequestType() == RequestType.MULTIPART) {
            builder.parts(newRequest.getParts());
        }
        return new ObservableAirRequest(airRequest, builder.build());
    }

    private AirRequest intercept(AirRequest request) {
        AirRequest interceptedRequest = Utils.intercept(this.interceptorFactories, request);
        return interceptedRequest.toBuilder().headers(Utils.sanitizeHeaders(addSpecialHeaders(interceptedRequest))).build();
    }

    private static AirRequest addSpecialHeaders(AirRequest airRequest) {
        String cacheControl = getCacheControl(airRequest);
        AirRequest.Builder builder = airRequest.toBuilder();
        if (cacheControl != null) {
            builder.addHeader("Cache-Control", cacheControl);
        }
        String responseCacheControl = getResponseCacheControl(airRequest);
        if (responseCacheControl != null) {
            builder.addHeader("X-Response-Cache-Control", responseCacheControl);
        }
        return builder.addHeader("X-Return-Strategy", airRequest.isDoubleResponse() ? "double" : "single").build();
    }

    private static String getCacheControl(AirRequest airRequest) {
        if (airRequest.isSkipCache()) {
            return CacheControl.FORCE_NETWORK.toString();
        }
        return getResponseCacheControl(airRequest);
    }

    private static String getResponseCacheControl(AirRequest immutableRequest) {
        CacheControl.Builder cacheControl = new CacheControl.Builder();
        int maxAgeSeconds = (int) (immutableRequest.getCacheOnlyTimeoutMs() / 1000);
        int maxStaleSeconds = (int) ((immutableRequest.getCacheTimeoutMs() - immutableRequest.getCacheOnlyTimeoutMs()) / 1000);
        if (maxAgeSeconds == 0 && maxStaleSeconds == 0) {
            return null;
        }
        cacheControl.maxAge(maxAgeSeconds, TimeUnit.SECONDS);
        if (maxStaleSeconds > 0) {
            cacheControl.maxStale(maxStaleSeconds, TimeUnit.SECONDS);
        }
        return cacheControl.build().toString();
    }
}
