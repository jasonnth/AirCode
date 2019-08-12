package com.airbnb.android.core.modules;

import com.airbnb.airrequest.BaseUrl;
import com.airbnb.android.core.AirbnbApi;
import okhttp3.HttpUrl;

final /* synthetic */ class NetworkModule$$Lambda$1 implements BaseUrl {
    private static final NetworkModule$$Lambda$1 instance = new NetworkModule$$Lambda$1();

    private NetworkModule$$Lambda$1() {
    }

    public static BaseUrl lambdaFactory$() {
        return instance;
    }

    public HttpUrl url() {
        return HttpUrl.parse(AirbnbApi.API_ENDPOINT_URL);
    }
}
