package com.airbnb.android.lib.views;

import com.airbnb.android.core.utils.CurrencyFormatter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class PriceGroupedCell_MembersInjector implements MembersInjector<PriceGroupedCell> {
    static final /* synthetic */ boolean $assertionsDisabled = (!PriceGroupedCell_MembersInjector.class.desiredAssertionStatus());
    private final Provider<CurrencyFormatter> currencyFormatterProvider;

    public PriceGroupedCell_MembersInjector(Provider<CurrencyFormatter> currencyFormatterProvider2) {
        if ($assertionsDisabled || currencyFormatterProvider2 != null) {
            this.currencyFormatterProvider = currencyFormatterProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<PriceGroupedCell> create(Provider<CurrencyFormatter> currencyFormatterProvider2) {
        return new PriceGroupedCell_MembersInjector(currencyFormatterProvider2);
    }

    public void injectMembers(PriceGroupedCell instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.currencyFormatter = (CurrencyFormatter) this.currencyFormatterProvider.get();
    }

    public static void injectCurrencyFormatter(PriceGroupedCell instance, Provider<CurrencyFormatter> currencyFormatterProvider2) {
        instance.currencyFormatter = (CurrencyFormatter) currencyFormatterProvider2.get();
    }
}
