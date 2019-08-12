package com.airbnb.android.core.modules;

import com.squareup.otto.Bus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoreModule_ProvideBusFactory implements Factory<Bus> {
    private static final CoreModule_ProvideBusFactory INSTANCE = new CoreModule_ProvideBusFactory();

    public Bus get() {
        return (Bus) Preconditions.checkNotNull(CoreModule.provideBus(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Bus> create() {
        return INSTANCE;
    }

    public static Bus proxyProvideBus() {
        return CoreModule.provideBus();
    }
}
