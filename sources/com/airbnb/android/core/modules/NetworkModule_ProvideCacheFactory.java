package com.airbnb.android.core.modules;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Cache;

public final class NetworkModule_ProvideCacheFactory implements Factory<Cache> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideCacheFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final NetworkModule module;

    public NetworkModule_ProvideCacheFactory(NetworkModule module2, Provider<Context> contextProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public Cache get() {
        return (Cache) Preconditions.checkNotNull(this.module.provideCache((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Cache> create(NetworkModule module2, Provider<Context> contextProvider2) {
        return new NetworkModule_ProvideCacheFactory(module2, contextProvider2);
    }

    public static Cache proxyProvideCache(NetworkModule instance, Context context) {
        return instance.provideCache(context);
    }
}
