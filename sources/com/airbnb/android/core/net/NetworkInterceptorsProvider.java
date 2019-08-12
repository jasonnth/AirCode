package com.airbnb.android.core.net;

import com.airbnb.airrequest.CacheControlInterceptor;
import com.airbnb.android.core.utils.BuildHelper;
import com.google.common.collect.ImmutableList;
import java.util.List;
import javax.inject.Provider;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;

public interface NetworkInterceptorsProvider extends Provider<List<Interceptor>> {

    public static class Impl implements NetworkInterceptorsProvider {
        private final Interceptor requestHeadersInterceptor;

        public Impl(Interceptor requestHeadersInterceptor2) {
            this.requestHeadersInterceptor = requestHeadersInterceptor2;
        }

        public List<Interceptor> get() {
            return ImmutableList.builder().add((Object) this.requestHeadersInterceptor).add((Object) new CacheControlInterceptor()).add((Object) new HttpLoggingInterceptor().setLevel(BuildHelper.isDevelopmentBuild() ? Level.BASIC : Level.NONE)).build();
        }
    }
}
