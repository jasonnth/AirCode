package com.airbnb.android.lib.payments.dagger;

import com.airbnb.android.lib.payments.factories.PaymentOptionFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class QuickPayModule_ProvidePaymentOptionFactoryFactory implements Factory<PaymentOptionFactory> {
    static final /* synthetic */ boolean $assertionsDisabled = (!QuickPayModule_ProvidePaymentOptionFactoryFactory.class.desiredAssertionStatus());
    private final QuickPayModule module;

    public QuickPayModule_ProvidePaymentOptionFactoryFactory(QuickPayModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public PaymentOptionFactory get() {
        return (PaymentOptionFactory) Preconditions.checkNotNull(this.module.providePaymentOptionFactory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<PaymentOptionFactory> create(QuickPayModule module2) {
        return new QuickPayModule_ProvidePaymentOptionFactoryFactory(module2);
    }

    public static PaymentOptionFactory proxyProvidePaymentOptionFactory(QuickPayModule instance) {
        return instance.providePaymentOptionFactory();
    }
}
