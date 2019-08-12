package com.airbnb.android.core.modules;

import android.accounts.AccountManager;
import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideAndroidAccountManagerFactory implements Factory<AccountManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideAndroidAccountManagerFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;

    public CoreModule_ProvideAndroidAccountManagerFactory(Provider<Context> contextProvider2) {
        if ($assertionsDisabled || contextProvider2 != null) {
            this.contextProvider = contextProvider2;
            return;
        }
        throw new AssertionError();
    }

    public AccountManager get() {
        return (AccountManager) Preconditions.checkNotNull(CoreModule.provideAndroidAccountManager((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AccountManager> create(Provider<Context> contextProvider2) {
        return new CoreModule_ProvideAndroidAccountManagerFactory(contextProvider2);
    }

    public static AccountManager proxyProvideAndroidAccountManager(Context context) {
        return CoreModule.provideAndroidAccountManager(context);
    }
}
