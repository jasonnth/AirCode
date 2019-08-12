package com.airbnb.android.core.modules;

import com.airbnb.p027n2.internal.ComponentManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoreModule_ProvideComponentManagerFactory implements Factory<ComponentManager> {
    private static final CoreModule_ProvideComponentManagerFactory INSTANCE = new CoreModule_ProvideComponentManagerFactory();

    public ComponentManager get() {
        return (ComponentManager) Preconditions.checkNotNull(CoreModule.provideComponentManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ComponentManager> create() {
        return INSTANCE;
    }

    public static ComponentManager proxyProvideComponentManager() {
        return CoreModule.provideComponentManager();
    }
}
