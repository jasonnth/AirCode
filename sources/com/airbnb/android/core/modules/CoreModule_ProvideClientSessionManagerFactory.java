package com.airbnb.android.core.modules;

import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.utils.ClientSessionManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideClientSessionManagerFactory implements Factory<ClientSessionManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideClientSessionManagerFactory.class.desiredAssertionStatus());
    private final CoreModule module;
    private final Provider<AirbnbPreferences> preferencesProvider;

    public CoreModule_ProvideClientSessionManagerFactory(CoreModule module2, Provider<AirbnbPreferences> preferencesProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || preferencesProvider2 != null) {
                this.preferencesProvider = preferencesProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ClientSessionManager get() {
        return (ClientSessionManager) Preconditions.checkNotNull(this.module.provideClientSessionManager((AirbnbPreferences) this.preferencesProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ClientSessionManager> create(CoreModule module2, Provider<AirbnbPreferences> preferencesProvider2) {
        return new CoreModule_ProvideClientSessionManagerFactory(module2, preferencesProvider2);
    }
}
