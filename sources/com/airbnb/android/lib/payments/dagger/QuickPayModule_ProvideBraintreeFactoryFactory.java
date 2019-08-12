package com.airbnb.android.lib.payments.dagger;

import com.airbnb.android.lib.payments.braintree.BraintreeFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class QuickPayModule_ProvideBraintreeFactoryFactory implements Factory<BraintreeFactory> {
    static final /* synthetic */ boolean $assertionsDisabled = (!QuickPayModule_ProvideBraintreeFactoryFactory.class.desiredAssertionStatus());
    private final QuickPayModule module;

    public QuickPayModule_ProvideBraintreeFactoryFactory(QuickPayModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public BraintreeFactory get() {
        return (BraintreeFactory) Preconditions.checkNotNull(this.module.provideBraintreeFactory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<BraintreeFactory> create(QuickPayModule module2) {
        return new QuickPayModule_ProvideBraintreeFactoryFactory(module2);
    }

    public static BraintreeFactory proxyProvideBraintreeFactory(QuickPayModule instance) {
        return instance.provideBraintreeFactory();
    }
}
