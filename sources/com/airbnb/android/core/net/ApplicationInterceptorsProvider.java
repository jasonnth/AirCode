package com.airbnb.android.core.net;

import com.airbnb.airrequest.BaseUrl;
import com.airbnb.airrequest.CacheRevalidationInterceptor;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.HostOverrideInterceptor;
import com.airbnb.android.core.HostSelectionInterceptor;
import com.airbnb.android.core.utils.BuildHelper;
import com.google.common.collect.ImmutableList;
import java.util.List;
import javax.inject.Provider;
import okhttp3.Interceptor;

public interface ApplicationInterceptorsProvider extends Provider<List<Interceptor>> {

    public static class Impl implements ApplicationInterceptorsProvider {
        private final BaseUrl apiBaseUrl;
        private final Interceptor queryParamsInterceptor;

        public Impl(BaseUrl apiBaseUrl2, Interceptor queryParamsInterceptor2) {
            this.apiBaseUrl = apiBaseUrl2;
            this.queryParamsInterceptor = queryParamsInterceptor2;
        }

        public List<Interceptor> get() {
            return ImmutableList.builder().add((Object) new ApiHostPlaceholderInterceptor(this.apiBaseUrl)).add((Object) new CacheRevalidationInterceptor()).add((Object) new HostOverrideInterceptor()).add((Object) new HostSelectionInterceptor(AirbnbApi.API_ENDPOINT_URL, this.apiBaseUrl, BuildHelper.isDebugFeaturesEnabled())).add((Object) this.queryParamsInterceptor).build();
        }
    }
}
