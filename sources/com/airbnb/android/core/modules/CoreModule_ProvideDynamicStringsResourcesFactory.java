package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.DynamicStringsResources;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideDynamicStringsResourcesFactory implements Factory<DynamicStringsResources> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideDynamicStringsResourcesFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final Provider<AirbnbPreferences> sharedPreferencesProvider;

    public CoreModule_ProvideDynamicStringsResourcesFactory(Provider<Context> contextProvider2, Provider<AirbnbPreferences> sharedPreferencesProvider2) {
        if ($assertionsDisabled || contextProvider2 != null) {
            this.contextProvider = contextProvider2;
            if ($assertionsDisabled || sharedPreferencesProvider2 != null) {
                this.sharedPreferencesProvider = sharedPreferencesProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public DynamicStringsResources get() {
        return (DynamicStringsResources) Preconditions.checkNotNull(CoreModule.provideDynamicStringsResources((Context) this.contextProvider.get(), (AirbnbPreferences) this.sharedPreferencesProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<DynamicStringsResources> create(Provider<Context> contextProvider2, Provider<AirbnbPreferences> sharedPreferencesProvider2) {
        return new CoreModule_ProvideDynamicStringsResourcesFactory(contextProvider2, sharedPreferencesProvider2);
    }

    public static DynamicStringsResources proxyProvideDynamicStringsResources(Context context, AirbnbPreferences sharedPreferences) {
        return CoreModule.provideDynamicStringsResources(context, sharedPreferences);
    }
}
