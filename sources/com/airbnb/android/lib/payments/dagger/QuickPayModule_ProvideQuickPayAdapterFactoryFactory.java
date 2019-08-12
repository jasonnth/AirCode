package com.airbnb.android.lib.payments.dagger;

import android.content.Context;
import com.airbnb.android.lib.payments.quickpay.adapters.QuickPayAdapterFactory;
import com.airbnb.android.lib.payments.quickpay.adapters.QuickPayRowFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class QuickPayModule_ProvideQuickPayAdapterFactoryFactory implements Factory<QuickPayAdapterFactory> {
    static final /* synthetic */ boolean $assertionsDisabled = (!QuickPayModule_ProvideQuickPayAdapterFactoryFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final QuickPayModule module;
    private final Provider<QuickPayRowFactory> quickPayRowFactoryProvider;

    public QuickPayModule_ProvideQuickPayAdapterFactoryFactory(QuickPayModule module2, Provider<Context> contextProvider2, Provider<QuickPayRowFactory> quickPayRowFactoryProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                if ($assertionsDisabled || quickPayRowFactoryProvider2 != null) {
                    this.quickPayRowFactoryProvider = quickPayRowFactoryProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public QuickPayAdapterFactory get() {
        return (QuickPayAdapterFactory) Preconditions.checkNotNull(this.module.provideQuickPayAdapterFactory((Context) this.contextProvider.get(), (QuickPayRowFactory) this.quickPayRowFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<QuickPayAdapterFactory> create(QuickPayModule module2, Provider<Context> contextProvider2, Provider<QuickPayRowFactory> quickPayRowFactoryProvider2) {
        return new QuickPayModule_ProvideQuickPayAdapterFactoryFactory(module2, contextProvider2, quickPayRowFactoryProvider2);
    }

    public static QuickPayAdapterFactory proxyProvideQuickPayAdapterFactory(QuickPayModule instance, Context context, QuickPayRowFactory quickPayRowFactory) {
        return instance.provideQuickPayAdapterFactory(context, quickPayRowFactory);
    }
}
