package com.airbnb.android.core.modules;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import p030in.uncod.android.bypass.Bypass;

public final class CoreModule_ProvideBypassFactory implements Factory<Bypass> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideBypassFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;

    public CoreModule_ProvideBypassFactory(Provider<Context> contextProvider2) {
        if ($assertionsDisabled || contextProvider2 != null) {
            this.contextProvider = contextProvider2;
            return;
        }
        throw new AssertionError();
    }

    public Bypass get() {
        return (Bypass) Preconditions.checkNotNull(CoreModule.provideBypass((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Bypass> create(Provider<Context> contextProvider2) {
        return new CoreModule_ProvideBypassFactory(contextProvider2);
    }

    public static Bypass proxyProvideBypass(Context context) {
        return CoreModule.provideBypass(context);
    }
}
