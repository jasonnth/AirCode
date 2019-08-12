package com.airbnb.android.core.modules;

import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.p027n2.C0977N2.Callbacks;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideN2CallbacksFactory implements Factory<Callbacks> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideN2CallbacksFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbPreferences> preferencesProvider;

    public CoreModule_ProvideN2CallbacksFactory(Provider<AirbnbPreferences> preferencesProvider2) {
        if ($assertionsDisabled || preferencesProvider2 != null) {
            this.preferencesProvider = preferencesProvider2;
            return;
        }
        throw new AssertionError();
    }

    public Callbacks get() {
        return (Callbacks) Preconditions.checkNotNull(CoreModule.provideN2Callbacks((AirbnbPreferences) this.preferencesProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Callbacks> create(Provider<AirbnbPreferences> preferencesProvider2) {
        return new CoreModule_ProvideN2CallbacksFactory(preferencesProvider2);
    }

    public static Callbacks proxyProvideN2Callbacks(AirbnbPreferences preferences) {
        return CoreModule.provideN2Callbacks(preferences);
    }
}
