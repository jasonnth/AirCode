package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.AirbnbPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideAirbnbPreferencesFactory implements Factory<AirbnbPreferences> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideAirbnbPreferencesFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;

    public CoreModule_ProvideAirbnbPreferencesFactory(Provider<Context> contextProvider2) {
        if ($assertionsDisabled || contextProvider2 != null) {
            this.contextProvider = contextProvider2;
            return;
        }
        throw new AssertionError();
    }

    public AirbnbPreferences get() {
        return (AirbnbPreferences) Preconditions.checkNotNull(CoreModule.provideAirbnbPreferences((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AirbnbPreferences> create(Provider<Context> contextProvider2) {
        return new CoreModule_ProvideAirbnbPreferencesFactory(contextProvider2);
    }

    public static AirbnbPreferences proxyProvideAirbnbPreferences(Context context) {
        return CoreModule.provideAirbnbPreferences(context);
    }
}
