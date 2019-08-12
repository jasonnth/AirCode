package com.airbnb.android.apprater;

import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.utils.DebugSettings;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AppRaterModule_ProvideAppRaterControllerFactory implements Factory<AppRaterController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AppRaterModule_ProvideAppRaterControllerFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbPreferences> airbnbPreferencesProvider;
    private final Provider<DebugSettings> debugSettingsProvider;
    private final AppRaterModule module;

    public AppRaterModule_ProvideAppRaterControllerFactory(AppRaterModule module2, Provider<DebugSettings> debugSettingsProvider2, Provider<AirbnbPreferences> airbnbPreferencesProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || debugSettingsProvider2 != null) {
                this.debugSettingsProvider = debugSettingsProvider2;
                if ($assertionsDisabled || airbnbPreferencesProvider2 != null) {
                    this.airbnbPreferencesProvider = airbnbPreferencesProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public AppRaterController get() {
        return (AppRaterController) Preconditions.checkNotNull(this.module.provideAppRaterController((DebugSettings) this.debugSettingsProvider.get(), (AirbnbPreferences) this.airbnbPreferencesProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AppRaterController> create(AppRaterModule module2, Provider<DebugSettings> debugSettingsProvider2, Provider<AirbnbPreferences> airbnbPreferencesProvider2) {
        return new AppRaterModule_ProvideAppRaterControllerFactory(module2, debugSettingsProvider2, airbnbPreferencesProvider2);
    }
}
