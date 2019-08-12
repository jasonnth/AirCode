package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.net.NetworkMonitor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class NetworkModule_ProvideNetworkMonitorFactory implements Factory<NetworkMonitor> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideNetworkMonitorFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final NetworkModule module;

    public NetworkModule_ProvideNetworkMonitorFactory(NetworkModule module2, Provider<Context> contextProvider2) {
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

    public NetworkMonitor get() {
        return (NetworkMonitor) Preconditions.checkNotNull(this.module.provideNetworkMonitor((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<NetworkMonitor> create(NetworkModule module2, Provider<Context> contextProvider2) {
        return new NetworkModule_ProvideNetworkMonitorFactory(module2, contextProvider2);
    }

    public static NetworkMonitor proxyProvideNetworkMonitor(NetworkModule instance, Context context) {
        return instance.provideNetworkMonitor(context);
    }
}
