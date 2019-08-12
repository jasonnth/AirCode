package com.airbnb.android.react;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ReactModule_ProvideReactNativeCoordinatorFactory implements Factory<ReactNavigationCoordinator> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ReactModule_ProvideReactNativeCoordinatorFactory.class.desiredAssertionStatus());
    private final ReactModule module;
    private final Provider<ObjectMapper> objectMapperProvider;

    public ReactModule_ProvideReactNativeCoordinatorFactory(ReactModule module2, Provider<ObjectMapper> objectMapperProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || objectMapperProvider2 != null) {
                this.objectMapperProvider = objectMapperProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ReactNavigationCoordinator get() {
        return (ReactNavigationCoordinator) Preconditions.checkNotNull(this.module.provideReactNativeCoordinator((ObjectMapper) this.objectMapperProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ReactNavigationCoordinator> create(ReactModule module2, Provider<ObjectMapper> objectMapperProvider2) {
        return new ReactModule_ProvideReactNativeCoordinatorFactory(module2, objectMapperProvider2);
    }
}
