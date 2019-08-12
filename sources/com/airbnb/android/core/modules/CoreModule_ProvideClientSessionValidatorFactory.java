package com.airbnb.android.core.modules;

import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.utils.ClientSessionValidator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideClientSessionValidatorFactory implements Factory<ClientSessionValidator> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideClientSessionValidatorFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final CoreModule module;
    private final Provider<AirbnbPreferences> preferencesProvider;

    public CoreModule_ProvideClientSessionValidatorFactory(CoreModule module2, Provider<AirbnbPreferences> preferencesProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || preferencesProvider2 != null) {
                this.preferencesProvider = preferencesProvider2;
                if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
                    this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ClientSessionValidator get() {
        return (ClientSessionValidator) Preconditions.checkNotNull(this.module.provideClientSessionValidator((AirbnbPreferences) this.preferencesProvider.get(), (LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ClientSessionValidator> create(CoreModule module2, Provider<AirbnbPreferences> preferencesProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new CoreModule_ProvideClientSessionValidatorFactory(module2, preferencesProvider2, loggingContextFactoryProvider2);
    }
}
