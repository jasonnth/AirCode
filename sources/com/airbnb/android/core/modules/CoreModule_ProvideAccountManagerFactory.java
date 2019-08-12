package com.airbnb.android.core.modules;

import android.accounts.AccountManager;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideAccountManagerFactory implements Factory<AirbnbAccountManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideAccountManagerFactory.class.desiredAssertionStatus());
    private final Provider<AccountManager> accountManagerProvider;
    private final CoreModule module;
    private final Provider<AirbnbPreferences> preferencesProvider;

    public CoreModule_ProvideAccountManagerFactory(CoreModule module2, Provider<AccountManager> accountManagerProvider2, Provider<AirbnbPreferences> preferencesProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
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

    public AirbnbAccountManager get() {
        return (AirbnbAccountManager) Preconditions.checkNotNull(this.module.provideAccountManager((AccountManager) this.accountManagerProvider.get(), (AirbnbPreferences) this.preferencesProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AirbnbAccountManager> create(CoreModule module2, Provider<AccountManager> accountManagerProvider2, Provider<AirbnbPreferences> preferencesProvider2) {
        return new CoreModule_ProvideAccountManagerFactory(module2, accountManagerProvider2, preferencesProvider2);
    }
}
