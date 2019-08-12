package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.ResourceManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideResourceManagerFactory implements Factory<ResourceManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideResourceManagerFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final CoreModule module;

    public CoreModule_ProvideResourceManagerFactory(CoreModule module2, Provider<Context> contextProvider2) {
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

    public ResourceManager get() {
        return (ResourceManager) Preconditions.checkNotNull(this.module.provideResourceManager((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ResourceManager> create(CoreModule module2, Provider<Context> contextProvider2) {
        return new CoreModule_ProvideResourceManagerFactory(module2, contextProvider2);
    }

    public static ResourceManager proxyProvideResourceManager(CoreModule instance, Context context) {
        return instance.provideResourceManager(context);
    }
}
