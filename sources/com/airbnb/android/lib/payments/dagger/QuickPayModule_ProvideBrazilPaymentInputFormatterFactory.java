package com.airbnb.android.lib.payments.dagger;

import com.airbnb.android.lib.payments.creditcard.brazil.textinput.formatter.BrazilPaymentInputFormatter;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class QuickPayModule_ProvideBrazilPaymentInputFormatterFactory implements Factory<BrazilPaymentInputFormatter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!QuickPayModule_ProvideBrazilPaymentInputFormatterFactory.class.desiredAssertionStatus());
    private final QuickPayModule module;
    private final Provider<PhoneNumberUtil> phoneNumberUtilProvider;

    public QuickPayModule_ProvideBrazilPaymentInputFormatterFactory(QuickPayModule module2, Provider<PhoneNumberUtil> phoneNumberUtilProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || phoneNumberUtilProvider2 != null) {
                this.phoneNumberUtilProvider = phoneNumberUtilProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public BrazilPaymentInputFormatter get() {
        return (BrazilPaymentInputFormatter) Preconditions.checkNotNull(this.module.provideBrazilPaymentInputFormatter((PhoneNumberUtil) this.phoneNumberUtilProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<BrazilPaymentInputFormatter> create(QuickPayModule module2, Provider<PhoneNumberUtil> phoneNumberUtilProvider2) {
        return new QuickPayModule_ProvideBrazilPaymentInputFormatterFactory(module2, phoneNumberUtilProvider2);
    }

    public static BrazilPaymentInputFormatter proxyProvideBrazilPaymentInputFormatter(QuickPayModule instance, PhoneNumberUtil phoneNumberUtil) {
        return instance.provideBrazilPaymentInputFormatter(phoneNumberUtil);
    }
}
