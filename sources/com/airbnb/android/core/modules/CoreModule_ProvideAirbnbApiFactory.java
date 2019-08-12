package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.DeviceInfo;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.messaging.MessageStore;
import com.squareup.otto.Bus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Cache;

public final class CoreModule_ProvideAirbnbApiFactory implements Factory<AirbnbApi> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideAirbnbApiFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<Bus> busProvider;
    private final Provider<Cache> cacheProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DeviceInfo> deviceInfoProvider;
    private final Provider<MessageStore> messageStoreProvider;
    private final CoreModule module;
    private final Provider<AirbnbPreferences> sharedPreferencesProvider;

    public CoreModule_ProvideAirbnbApiFactory(CoreModule module2, Provider<Context> contextProvider2, Provider<AirbnbPreferences> sharedPreferencesProvider2, Provider<MessageStore> messageStoreProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<Bus> busProvider2, Provider<Cache> cacheProvider2, Provider<DeviceInfo> deviceInfoProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                if ($assertionsDisabled || sharedPreferencesProvider2 != null) {
                    this.sharedPreferencesProvider = sharedPreferencesProvider2;
                    if ($assertionsDisabled || messageStoreProvider2 != null) {
                        this.messageStoreProvider = messageStoreProvider2;
                        if ($assertionsDisabled || accountManagerProvider2 != null) {
                            this.accountManagerProvider = accountManagerProvider2;
                            if ($assertionsDisabled || busProvider2 != null) {
                                this.busProvider = busProvider2;
                                if ($assertionsDisabled || cacheProvider2 != null) {
                                    this.cacheProvider = cacheProvider2;
                                    if ($assertionsDisabled || deviceInfoProvider2 != null) {
                                        this.deviceInfoProvider = deviceInfoProvider2;
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
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public AirbnbApi get() {
        return (AirbnbApi) Preconditions.checkNotNull(this.module.provideAirbnbApi((Context) this.contextProvider.get(), (AirbnbPreferences) this.sharedPreferencesProvider.get(), (MessageStore) this.messageStoreProvider.get(), (AirbnbAccountManager) this.accountManagerProvider.get(), (Bus) this.busProvider.get(), (Cache) this.cacheProvider.get(), (DeviceInfo) this.deviceInfoProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AirbnbApi> create(CoreModule module2, Provider<Context> contextProvider2, Provider<AirbnbPreferences> sharedPreferencesProvider2, Provider<MessageStore> messageStoreProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<Bus> busProvider2, Provider<Cache> cacheProvider2, Provider<DeviceInfo> deviceInfoProvider2) {
        return new CoreModule_ProvideAirbnbApiFactory(module2, contextProvider2, sharedPreferencesProvider2, messageStoreProvider2, accountManagerProvider2, busProvider2, cacheProvider2, deviceInfoProvider2);
    }
}
