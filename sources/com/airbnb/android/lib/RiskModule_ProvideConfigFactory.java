package com.airbnb.android.lib;

import android.content.Context;
import com.threatmetrix.TrustDefender.Config;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class RiskModule_ProvideConfigFactory implements Factory<Config> {
    static final /* synthetic */ boolean $assertionsDisabled = (!RiskModule_ProvideConfigFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final RiskModule module;

    public RiskModule_ProvideConfigFactory(RiskModule module2, Provider<Context> contextProvider2) {
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

    public Config get() {
        return (Config) Preconditions.checkNotNull(this.module.provideConfig((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Config> create(RiskModule module2, Provider<Context> contextProvider2) {
        return new RiskModule_ProvideConfigFactory(module2, contextProvider2);
    }

    public static Config proxyProvideConfig(RiskModule instance, Context context) {
        return instance.provideConfig(context);
    }
}
