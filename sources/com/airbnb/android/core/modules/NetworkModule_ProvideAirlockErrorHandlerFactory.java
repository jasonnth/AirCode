package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.requests.base.AirlockErrorHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class NetworkModule_ProvideAirlockErrorHandlerFactory implements Factory<AirlockErrorHandler> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideAirlockErrorHandlerFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<Context> contextProvider;
    private final NetworkModule module;
    private final Provider<ObjectMapper> objectMapperProvider;

    public NetworkModule_ProvideAirlockErrorHandlerFactory(NetworkModule module2, Provider<Context> contextProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<ObjectMapper> objectMapperProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                if ($assertionsDisabled || accountManagerProvider2 != null) {
                    this.accountManagerProvider = accountManagerProvider2;
                    if ($assertionsDisabled || objectMapperProvider2 != null) {
                        this.objectMapperProvider = objectMapperProvider2;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public AirlockErrorHandler get() {
        return (AirlockErrorHandler) Preconditions.checkNotNull(this.module.provideAirlockErrorHandler((Context) this.contextProvider.get(), (AirbnbAccountManager) this.accountManagerProvider.get(), (ObjectMapper) this.objectMapperProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AirlockErrorHandler> create(NetworkModule module2, Provider<Context> contextProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<ObjectMapper> objectMapperProvider2) {
        return new NetworkModule_ProvideAirlockErrorHandlerFactory(module2, contextProvider2, accountManagerProvider2, objectMapperProvider2);
    }

    public static AirlockErrorHandler proxyProvideAirlockErrorHandler(NetworkModule instance, Context context, AirbnbAccountManager accountManager, ObjectMapper objectMapper) {
        return instance.provideAirlockErrorHandler(context, accountManager, objectMapper);
    }
}
