package com.airbnb.android.lib.payments.dagger;

import com.airbnb.android.lib.payments.creditcard.validation.CreditCardValidator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class QuickPayModule_ProvideCreditCardValidatorFactory implements Factory<CreditCardValidator> {
    static final /* synthetic */ boolean $assertionsDisabled = (!QuickPayModule_ProvideCreditCardValidatorFactory.class.desiredAssertionStatus());
    private final QuickPayModule module;

    public QuickPayModule_ProvideCreditCardValidatorFactory(QuickPayModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public CreditCardValidator get() {
        return (CreditCardValidator) Preconditions.checkNotNull(this.module.provideCreditCardValidator(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<CreditCardValidator> create(QuickPayModule module2) {
        return new QuickPayModule_ProvideCreditCardValidatorFactory(module2);
    }

    public static CreditCardValidator proxyProvideCreditCardValidator(QuickPayModule instance) {
        return instance.provideCreditCardValidator();
    }
}
