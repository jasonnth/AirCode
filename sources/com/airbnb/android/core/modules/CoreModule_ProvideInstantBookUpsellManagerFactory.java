package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.utils.InstantBookUpsellManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideInstantBookUpsellManagerFactory implements Factory<InstantBookUpsellManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideInstantBookUpsellManagerFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;

    public CoreModule_ProvideInstantBookUpsellManagerFactory(Provider<Context> contextProvider2) {
        if ($assertionsDisabled || contextProvider2 != null) {
            this.contextProvider = contextProvider2;
            return;
        }
        throw new AssertionError();
    }

    public InstantBookUpsellManager get() {
        return (InstantBookUpsellManager) Preconditions.checkNotNull(CoreModule.provideInstantBookUpsellManager((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<InstantBookUpsellManager> create(Provider<Context> contextProvider2) {
        return new CoreModule_ProvideInstantBookUpsellManagerFactory(contextProvider2);
    }

    public static InstantBookUpsellManager proxyProvideInstantBookUpsellManager(Context context) {
        return CoreModule.provideInstantBookUpsellManager(context);
    }
}
