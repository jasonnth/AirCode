package com.airbnb.android.core.modules;

import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.net.LowBandwidthManager;
import com.airbnb.android.core.net.NetworkMonitor;
import com.squareup.otto.Bus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class NetworkModule_ProvideLowBandwidthUtilsFactory implements Factory<LowBandwidthManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideLowBandwidthUtilsFactory.class.desiredAssertionStatus());
    private final Provider<Bus> busProvider;
    private final NetworkModule module;
    private final Provider<NetworkMonitor> networkMonitorProvider;
    private final Provider<AirbnbPreferences> preferencesProvider;

    public NetworkModule_ProvideLowBandwidthUtilsFactory(NetworkModule module2, Provider<AirbnbPreferences> preferencesProvider2, Provider<Bus> busProvider2, Provider<NetworkMonitor> networkMonitorProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || preferencesProvider2 != null) {
                this.preferencesProvider = preferencesProvider2;
                if ($assertionsDisabled || busProvider2 != null) {
                    this.busProvider = busProvider2;
                    if ($assertionsDisabled || networkMonitorProvider2 != null) {
                        this.networkMonitorProvider = networkMonitorProvider2;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public LowBandwidthManager get() {
        return (LowBandwidthManager) Preconditions.checkNotNull(this.module.provideLowBandwidthUtils((AirbnbPreferences) this.preferencesProvider.get(), (Bus) this.busProvider.get(), (NetworkMonitor) this.networkMonitorProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<LowBandwidthManager> create(NetworkModule module2, Provider<AirbnbPreferences> preferencesProvider2, Provider<Bus> busProvider2, Provider<NetworkMonitor> networkMonitorProvider2) {
        return new NetworkModule_ProvideLowBandwidthUtilsFactory(module2, preferencesProvider2, busProvider2, networkMonitorProvider2);
    }

    public static LowBandwidthManager proxyProvideLowBandwidthUtils(NetworkModule instance, AirbnbPreferences preferences, Bus bus, NetworkMonitor networkMonitor) {
        return instance.provideLowBandwidthUtils(preferences, bus, networkMonitor);
    }
}
