package com.airbnb.android.lib.adapters;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ThreadAdapter_MembersInjector implements MembersInjector<ThreadAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ThreadAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<CurrencyFormatter> currencyHelperProvider;
    private final Provider<DebugSettings> debugSettingsProvider;
    private final Provider<SharedPrefsHelper> sharedPrefsHelperProvider;

    public ThreadAdapter_MembersInjector(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<CurrencyFormatter> currencyHelperProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        if ($assertionsDisabled || accountManagerProvider2 != null) {
            this.accountManagerProvider = accountManagerProvider2;
            if ($assertionsDisabled || currencyHelperProvider2 != null) {
                this.currencyHelperProvider = currencyHelperProvider2;
                if ($assertionsDisabled || debugSettingsProvider2 != null) {
                    this.debugSettingsProvider = debugSettingsProvider2;
                    if ($assertionsDisabled || sharedPrefsHelperProvider2 != null) {
                        this.sharedPrefsHelperProvider = sharedPrefsHelperProvider2;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ThreadAdapter> create(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<CurrencyFormatter> currencyHelperProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        return new ThreadAdapter_MembersInjector(accountManagerProvider2, currencyHelperProvider2, debugSettingsProvider2, sharedPrefsHelperProvider2);
    }

    public void injectMembers(ThreadAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
        instance.currencyHelper = (CurrencyFormatter) this.currencyHelperProvider.get();
        instance.debugSettings = (DebugSettings) this.debugSettingsProvider.get();
        instance.sharedPrefsHelper = (SharedPrefsHelper) this.sharedPrefsHelperProvider.get();
    }

    public static void injectAccountManager(ThreadAdapter instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }

    public static void injectCurrencyHelper(ThreadAdapter instance, Provider<CurrencyFormatter> currencyHelperProvider2) {
        instance.currencyHelper = (CurrencyFormatter) currencyHelperProvider2.get();
    }

    public static void injectDebugSettings(ThreadAdapter instance, Provider<DebugSettings> debugSettingsProvider2) {
        instance.debugSettings = (DebugSettings) debugSettingsProvider2.get();
    }

    public static void injectSharedPrefsHelper(ThreadAdapter instance, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        instance.sharedPrefsHelper = (SharedPrefsHelper) sharedPrefsHelperProvider2.get();
    }
}
