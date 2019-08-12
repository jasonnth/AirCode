package com.airbnb.android.lib.views;

import com.airbnb.android.core.utils.CurrencyFormatter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class PricingQuotePricingDetails_MembersInjector implements MembersInjector<PricingQuotePricingDetails> {
    static final /* synthetic */ boolean $assertionsDisabled = (!PricingQuotePricingDetails_MembersInjector.class.desiredAssertionStatus());
    private final Provider<CurrencyFormatter> mCurrencyHelperProvider;

    public PricingQuotePricingDetails_MembersInjector(Provider<CurrencyFormatter> mCurrencyHelperProvider2) {
        if ($assertionsDisabled || mCurrencyHelperProvider2 != null) {
            this.mCurrencyHelperProvider = mCurrencyHelperProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<PricingQuotePricingDetails> create(Provider<CurrencyFormatter> mCurrencyHelperProvider2) {
        return new PricingQuotePricingDetails_MembersInjector(mCurrencyHelperProvider2);
    }

    public void injectMembers(PricingQuotePricingDetails instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mCurrencyHelper = (CurrencyFormatter) this.mCurrencyHelperProvider.get();
    }

    public static void injectMCurrencyHelper(PricingQuotePricingDetails instance, Provider<CurrencyFormatter> mCurrencyHelperProvider2) {
        instance.mCurrencyHelper = (CurrencyFormatter) mCurrencyHelperProvider2.get();
    }
}
