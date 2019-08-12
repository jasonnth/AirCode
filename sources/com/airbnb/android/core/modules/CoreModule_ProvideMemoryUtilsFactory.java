package com.airbnb.android.core.modules;

import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.utils.MemoryUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideMemoryUtilsFactory implements Factory<MemoryUtils> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideMemoryUtilsFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbPreferences> preferencesProvider;

    public CoreModule_ProvideMemoryUtilsFactory(Provider<AirbnbPreferences> preferencesProvider2) {
        if ($assertionsDisabled || preferencesProvider2 != null) {
            this.preferencesProvider = preferencesProvider2;
            return;
        }
        throw new AssertionError();
    }

    public MemoryUtils get() {
        return (MemoryUtils) Preconditions.checkNotNull(CoreModule.provideMemoryUtils((AirbnbPreferences) this.preferencesProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MemoryUtils> create(Provider<AirbnbPreferences> preferencesProvider2) {
        return new CoreModule_ProvideMemoryUtilsFactory(preferencesProvider2);
    }

    public static MemoryUtils proxyProvideMemoryUtils(AirbnbPreferences preferences) {
        return CoreModule.provideMemoryUtils(preferences);
    }
}
