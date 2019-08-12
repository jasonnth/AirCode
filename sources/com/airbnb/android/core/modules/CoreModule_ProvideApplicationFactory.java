package com.airbnb.android.core.modules;

import android.app.Application;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoreModule_ProvideApplicationFactory implements Factory<Application> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideApplicationFactory.class.desiredAssertionStatus());
    private final CoreModule module;

    public CoreModule_ProvideApplicationFactory(CoreModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public Application get() {
        return (Application) Preconditions.checkNotNull(this.module.provideApplication(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Application> create(CoreModule module2) {
        return new CoreModule_ProvideApplicationFactory(module2);
    }

    public static Application proxyProvideApplication(CoreModule instance) {
        return instance.provideApplication();
    }
}
