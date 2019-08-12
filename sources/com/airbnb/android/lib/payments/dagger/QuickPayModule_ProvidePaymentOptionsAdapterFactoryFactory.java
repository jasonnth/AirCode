package com.airbnb.android.lib.payments.dagger;

import android.content.Context;
import com.airbnb.android.lib.payments.paymentoptions.adapters.PaymentOptionsAdapterFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class QuickPayModule_ProvidePaymentOptionsAdapterFactoryFactory implements Factory<PaymentOptionsAdapterFactory> {
    static final /* synthetic */ boolean $assertionsDisabled = (!QuickPayModule_ProvidePaymentOptionsAdapterFactoryFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final QuickPayModule module;

    public QuickPayModule_ProvidePaymentOptionsAdapterFactoryFactory(QuickPayModule module2, Provider<Context> contextProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public PaymentOptionsAdapterFactory get() {
        return (PaymentOptionsAdapterFactory) Preconditions.checkNotNull(this.module.providePaymentOptionsAdapterFactory((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<PaymentOptionsAdapterFactory> create(QuickPayModule module2, Provider<Context> contextProvider2) {
        return new QuickPayModule_ProvidePaymentOptionsAdapterFactoryFactory(module2, contextProvider2);
    }

    public static PaymentOptionsAdapterFactory proxyProvidePaymentOptionsAdapterFactory(QuickPayModule instance, Context context) {
        return instance.providePaymentOptionsAdapterFactory(context);
    }
}
