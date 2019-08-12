package com.airbnb.android.core.modules;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoreModule_ProvideContextFactory implements Factory<Context> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideContextFactory.class.desiredAssertionStatus());
    private final CoreModule module;

    public CoreModule_ProvideContextFactory(CoreModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public Context get() {
        return (Context) Preconditions.checkNotNull(this.module.provideContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Context> create(CoreModule module2) {
        return new CoreModule_ProvideContextFactory(module2);
    }

    public static Context proxyProvideContext(CoreModule instance) {
        return instance.provideContext();
    }
}
