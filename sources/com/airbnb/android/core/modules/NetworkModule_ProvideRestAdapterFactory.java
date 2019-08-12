package com.airbnb.android.core.modules;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;

public final class NetworkModule_ProvideRestAdapterFactory implements Factory<Retrofit> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideRestAdapterFactory.class.desiredAssertionStatus());
    private final Provider<Builder> builderProvider;
    private final NetworkModule module;

    public NetworkModule_ProvideRestAdapterFactory(NetworkModule module2, Provider<Builder> builderProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || builderProvider2 != null) {
                this.builderProvider = builderProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public Retrofit get() {
        return (Retrofit) Preconditions.checkNotNull(this.module.provideRestAdapter((Builder) this.builderProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Retrofit> create(NetworkModule module2, Provider<Builder> builderProvider2) {
        return new NetworkModule_ProvideRestAdapterFactory(module2, builderProvider2);
    }

    public static Retrofit proxyProvideRestAdapter(NetworkModule instance, Builder builder) {
        return instance.provideRestAdapter(builder);
    }
}
