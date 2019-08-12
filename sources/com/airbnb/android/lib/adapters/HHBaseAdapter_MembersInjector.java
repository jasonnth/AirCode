package com.airbnb.android.lib.adapters;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.utils.CurrencyFormatter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class HHBaseAdapter_MembersInjector implements MembersInjector<HHBaseAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!HHBaseAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> mAccountManagerProvider;
    private final Provider<CurrencyFormatter> mCurrencyHelperProvider;

    public HHBaseAdapter_MembersInjector(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<CurrencyFormatter> mCurrencyHelperProvider2) {
        if ($assertionsDisabled || mAccountManagerProvider2 != null) {
            this.mAccountManagerProvider = mAccountManagerProvider2;
            if ($assertionsDisabled || mCurrencyHelperProvider2 != null) {
                this.mCurrencyHelperProvider = mCurrencyHelperProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<HHBaseAdapter> create(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<CurrencyFormatter> mCurrencyHelperProvider2) {
        return new HHBaseAdapter_MembersInjector(mAccountManagerProvider2, mCurrencyHelperProvider2);
    }

    public void injectMembers(HHBaseAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAccountManager = (AirbnbAccountManager) this.mAccountManagerProvider.get();
        instance.mCurrencyHelper = (CurrencyFormatter) this.mCurrencyHelperProvider.get();
    }

    public static void injectMAccountManager(HHBaseAdapter instance, Provider<AirbnbAccountManager> mAccountManagerProvider2) {
        instance.mAccountManager = (AirbnbAccountManager) mAccountManagerProvider2.get();
    }

    public static void injectMCurrencyHelper(HHBaseAdapter instance, Provider<CurrencyFormatter> mCurrencyHelperProvider2) {
        instance.mCurrencyHelper = (CurrencyFormatter) mCurrencyHelperProvider2.get();
    }
}
