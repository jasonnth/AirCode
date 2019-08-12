package com.airbnb.android.core.modules;

import com.airbnb.android.core.DeviceInfo;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.erf.ErfAnalytics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideErfAnalyticsFactory implements Factory<ErfAnalytics> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideErfAnalyticsFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<DeviceInfo> deviceInfoProvider;

    public CoreModule_ProvideErfAnalyticsFactory(Provider<DeviceInfo> deviceInfoProvider2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        if ($assertionsDisabled || deviceInfoProvider2 != null) {
            this.deviceInfoProvider = deviceInfoProvider2;
            if ($assertionsDisabled || accountManagerProvider2 != null) {
                this.accountManagerProvider = accountManagerProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ErfAnalytics get() {
        return (ErfAnalytics) Preconditions.checkNotNull(CoreModule.provideErfAnalytics((DeviceInfo) this.deviceInfoProvider.get(), (AirbnbAccountManager) this.accountManagerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ErfAnalytics> create(Provider<DeviceInfo> deviceInfoProvider2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        return new CoreModule_ProvideErfAnalyticsFactory(deviceInfoProvider2, accountManagerProvider2);
    }

    public static ErfAnalytics proxyProvideErfAnalytics(DeviceInfo deviceInfo, AirbnbAccountManager accountManager) {
        return CoreModule.provideErfAnalytics(deviceInfo, accountManager);
    }
}
