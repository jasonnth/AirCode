package com.airbnb.android.core.modules;

import com.airbnb.android.core.utils.geocoder.GeocoderBaseUrl;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class NetworkModule_ProvideGeocoderRequestBaseUrlFactory implements Factory<GeocoderBaseUrl> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideGeocoderRequestBaseUrlFactory.class.desiredAssertionStatus());
    private final NetworkModule module;

    public NetworkModule_ProvideGeocoderRequestBaseUrlFactory(NetworkModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public GeocoderBaseUrl get() {
        return (GeocoderBaseUrl) Preconditions.checkNotNull(this.module.provideGeocoderRequestBaseUrl(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<GeocoderBaseUrl> create(NetworkModule module2) {
        return new NetworkModule_ProvideGeocoderRequestBaseUrlFactory(module2);
    }

    public static GeocoderBaseUrl proxyProvideGeocoderRequestBaseUrl(NetworkModule instance) {
        return instance.provideGeocoderRequestBaseUrl();
    }
}
