package com.airbnb.android.core.modules;

import com.airbnb.rxgroups.ObservableManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class NetworkModule_ProvideObservableManagerFactory implements Factory<ObservableManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideObservableManagerFactory.class.desiredAssertionStatus());
    private final NetworkModule module;

    public NetworkModule_ProvideObservableManagerFactory(NetworkModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public ObservableManager get() {
        return (ObservableManager) Preconditions.checkNotNull(this.module.provideObservableManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ObservableManager> create(NetworkModule module2) {
        return new NetworkModule_ProvideObservableManagerFactory(module2);
    }

    public static ObservableManager proxyProvideObservableManager(NetworkModule instance) {
        return instance.provideObservableManager();
    }
}
