package com.airbnb.android.lib.payments.dagger;

import com.airbnb.android.core.identity.IdentityClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class QuickPayModule_ProvideIdentityClientFactory implements Factory<IdentityClient> {
    static final /* synthetic */ boolean $assertionsDisabled = (!QuickPayModule_ProvideIdentityClientFactory.class.desiredAssertionStatus());
    private final QuickPayModule module;

    public QuickPayModule_ProvideIdentityClientFactory(QuickPayModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public IdentityClient get() {
        return (IdentityClient) Preconditions.checkNotNull(this.module.provideIdentityClient(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<IdentityClient> create(QuickPayModule module2) {
        return new QuickPayModule_ProvideIdentityClientFactory(module2);
    }

    public static IdentityClient proxyProvideIdentityClient(QuickPayModule instance) {
        return instance.provideIdentityClient();
    }
}
