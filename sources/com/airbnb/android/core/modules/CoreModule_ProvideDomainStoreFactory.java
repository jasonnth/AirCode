package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.persist.DomainStore;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideDomainStoreFactory implements Factory<DomainStore> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideDomainStoreFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final CoreModule module;

    public CoreModule_ProvideDomainStoreFactory(CoreModule module2, Provider<Context> contextProvider2) {
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

    public DomainStore get() {
        return (DomainStore) Preconditions.checkNotNull(this.module.provideDomainStore((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<DomainStore> create(CoreModule module2, Provider<Context> contextProvider2) {
        return new CoreModule_ProvideDomainStoreFactory(module2, contextProvider2);
    }

    public static DomainStore proxyProvideDomainStore(CoreModule instance, Context context) {
        return instance.provideDomainStore(context);
    }
}
