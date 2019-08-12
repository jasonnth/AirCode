package com.airbnb.android.core.modules;

import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.controllers.SplashScreenController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideSplashScreenControllerFactory implements Factory<SplashScreenController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideSplashScreenControllerFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbPreferences> airbnbPreferencesProvider;

    public CoreModule_ProvideSplashScreenControllerFactory(Provider<AirbnbPreferences> airbnbPreferencesProvider2) {
        if ($assertionsDisabled || airbnbPreferencesProvider2 != null) {
            this.airbnbPreferencesProvider = airbnbPreferencesProvider2;
            return;
        }
        throw new AssertionError();
    }

    public SplashScreenController get() {
        return (SplashScreenController) Preconditions.checkNotNull(CoreModule.provideSplashScreenController((AirbnbPreferences) this.airbnbPreferencesProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SplashScreenController> create(Provider<AirbnbPreferences> airbnbPreferencesProvider2) {
        return new CoreModule_ProvideSplashScreenControllerFactory(airbnbPreferencesProvider2);
    }

    public static SplashScreenController proxyProvideSplashScreenController(AirbnbPreferences airbnbPreferences) {
        return CoreModule.provideSplashScreenController(airbnbPreferences);
    }
}
