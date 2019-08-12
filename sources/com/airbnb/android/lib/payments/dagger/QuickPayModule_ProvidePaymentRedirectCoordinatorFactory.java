package com.airbnb.android.lib.payments.dagger;

import com.airbnb.android.lib.payments.quickpay.paymentredirect.PaymentRedirectCoordinator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class QuickPayModule_ProvidePaymentRedirectCoordinatorFactory implements Factory<PaymentRedirectCoordinator> {
    static final /* synthetic */ boolean $assertionsDisabled = (!QuickPayModule_ProvidePaymentRedirectCoordinatorFactory.class.desiredAssertionStatus());
    private final QuickPayModule module;

    public QuickPayModule_ProvidePaymentRedirectCoordinatorFactory(QuickPayModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public PaymentRedirectCoordinator get() {
        return (PaymentRedirectCoordinator) Preconditions.checkNotNull(this.module.providePaymentRedirectCoordinator(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<PaymentRedirectCoordinator> create(QuickPayModule module2) {
        return new QuickPayModule_ProvidePaymentRedirectCoordinatorFactory(module2);
    }

    public static PaymentRedirectCoordinator proxyProvidePaymentRedirectCoordinator(QuickPayModule instance) {
        return instance.providePaymentRedirectCoordinator();
    }
}
