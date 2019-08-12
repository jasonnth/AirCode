package com.airbnb.p027n2;

import android.content.Context;
import com.danikula.videocache.HttpProxyCacheServer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* renamed from: com.airbnb.n2.N2Module_ProvideVideoCacheFactory */
public final class N2Module_ProvideVideoCacheFactory implements Factory<HttpProxyCacheServer> {
    static final /* synthetic */ boolean $assertionsDisabled = (!N2Module_ProvideVideoCacheFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final N2Module module;

    public N2Module_ProvideVideoCacheFactory(N2Module module2, Provider<Context> contextProvider2) {
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

    public HttpProxyCacheServer get() {
        return (HttpProxyCacheServer) Preconditions.checkNotNull(this.module.provideVideoCache((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<HttpProxyCacheServer> create(N2Module module2, Provider<Context> contextProvider2) {
        return new N2Module_ProvideVideoCacheFactory(module2, contextProvider2);
    }
}
