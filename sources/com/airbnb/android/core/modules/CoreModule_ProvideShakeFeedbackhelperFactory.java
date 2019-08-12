package com.airbnb.android.core.modules;

import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.utils.ShakeFeedbackSensorListener;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideShakeFeedbackhelperFactory implements Factory<ShakeFeedbackSensorListener> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideShakeFeedbackhelperFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbPreferences> preferencesProvider;

    public CoreModule_ProvideShakeFeedbackhelperFactory(Provider<AirbnbPreferences> preferencesProvider2) {
        if ($assertionsDisabled || preferencesProvider2 != null) {
            this.preferencesProvider = preferencesProvider2;
            return;
        }
        throw new AssertionError();
    }

    public ShakeFeedbackSensorListener get() {
        return (ShakeFeedbackSensorListener) Preconditions.checkNotNull(CoreModule.provideShakeFeedbackhelper((AirbnbPreferences) this.preferencesProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ShakeFeedbackSensorListener> create(Provider<AirbnbPreferences> preferencesProvider2) {
        return new CoreModule_ProvideShakeFeedbackhelperFactory(preferencesProvider2);
    }

    public static ShakeFeedbackSensorListener proxyProvideShakeFeedbackhelper(AirbnbPreferences preferences) {
        return CoreModule.provideShakeFeedbackhelper(preferences);
    }
}
