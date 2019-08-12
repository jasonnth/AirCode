package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.utils.CurrencyFormatter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideCurrencyHelperFactory implements Factory<CurrencyFormatter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideCurrencyHelperFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<AirbnbPreferences> preferencesProvider;

    public CoreModule_ProvideCurrencyHelperFactory(Provider<Context> contextProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<AirbnbPreferences> preferencesProvider2) {
        if ($assertionsDisabled || contextProvider2 != null) {
            this.contextProvider = contextProvider2;
            if ($assertionsDisabled || accountManagerProvider2 != null) {
                this.accountManagerProvider = accountManagerProvider2;
                if ($assertionsDisabled || preferencesProvider2 != null) {
                    this.preferencesProvider = preferencesProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public CurrencyFormatter get() {
        return (CurrencyFormatter) Preconditions.checkNotNull(CoreModule.provideCurrencyHelper((Context) this.contextProvider.get(), (AirbnbAccountManager) this.accountManagerProvider.get(), (AirbnbPreferences) this.preferencesProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<CurrencyFormatter> create(Provider<Context> contextProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<AirbnbPreferences> preferencesProvider2) {
        return new CoreModule_ProvideCurrencyHelperFactory(contextProvider2, accountManagerProvider2, preferencesProvider2);
    }

    public static CurrencyFormatter proxyProvideCurrencyHelper(Context context, AirbnbAccountManager accountManager, AirbnbPreferences preferences) {
        return CoreModule.provideCurrencyHelper(context, accountManager, preferences);
    }
}
