package com.airbnb.android.core.modules;

import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideSharedPrefsHelperFactory implements Factory<SharedPrefsHelper> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideSharedPrefsHelperFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbPreferences> preferencesProvider;

    public CoreModule_ProvideSharedPrefsHelperFactory(Provider<AirbnbPreferences> preferencesProvider2) {
        if ($assertionsDisabled || preferencesProvider2 != null) {
            this.preferencesProvider = preferencesProvider2;
            return;
        }
        throw new AssertionError();
    }

    public SharedPrefsHelper get() {
        return (SharedPrefsHelper) Preconditions.checkNotNull(CoreModule.provideSharedPrefsHelper((AirbnbPreferences) this.preferencesProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SharedPrefsHelper> create(Provider<AirbnbPreferences> preferencesProvider2) {
        return new CoreModule_ProvideSharedPrefsHelperFactory(preferencesProvider2);
    }

    public static SharedPrefsHelper proxyProvideSharedPrefsHelper(AirbnbPreferences preferences) {
        return CoreModule.provideSharedPrefsHelper(preferences);
    }
}
