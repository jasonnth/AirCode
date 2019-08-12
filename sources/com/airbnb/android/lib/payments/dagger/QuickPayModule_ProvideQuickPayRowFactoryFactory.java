package com.airbnb.android.lib.payments.dagger;

import android.content.Context;
import com.airbnb.android.lib.payments.quickpay.adapters.QuickPayRowFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class QuickPayModule_ProvideQuickPayRowFactoryFactory implements Factory<QuickPayRowFactory> {
    static final /* synthetic */ boolean $assertionsDisabled = (!QuickPayModule_ProvideQuickPayRowFactoryFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final QuickPayModule module;

    public QuickPayModule_ProvideQuickPayRowFactoryFactory(QuickPayModule module2, Provider<Context> contextProvider2) {
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

    public QuickPayRowFactory get() {
        return (QuickPayRowFactory) Preconditions.checkNotNull(this.module.provideQuickPayRowFactory((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<QuickPayRowFactory> create(QuickPayModule module2, Provider<Context> contextProvider2) {
        return new QuickPayModule_ProvideQuickPayRowFactoryFactory(module2, contextProvider2);
    }

    public static QuickPayRowFactory proxyProvideQuickPayRowFactory(QuickPayModule instance, Context context) {
        return instance.provideQuickPayRowFactory(context);
    }
}
