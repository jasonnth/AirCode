package com.airbnb.android.core.modules;

import com.airbnb.android.core.messaging.p007db.ThreadDataMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoreModule_ProvideThreadDataMapperFactory implements Factory<ThreadDataMapper> {
    private static final CoreModule_ProvideThreadDataMapperFactory INSTANCE = new CoreModule_ProvideThreadDataMapperFactory();

    public ThreadDataMapper get() {
        return (ThreadDataMapper) Preconditions.checkNotNull(CoreModule.provideThreadDataMapper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ThreadDataMapper> create() {
        return INSTANCE;
    }

    public static ThreadDataMapper proxyProvideThreadDataMapper() {
        return CoreModule.provideThreadDataMapper();
    }
}
