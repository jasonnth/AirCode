package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.localpushnotifications.LocalPushNotificationManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideLocalPushNotificationManagerFactory implements Factory<LocalPushNotificationManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideLocalPushNotificationManagerFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<AirbnbApi> airbnbApiProvider;
    private final Provider<Context> contextProvider;
    private final CoreModule module;
    private final Provider<AirbnbPreferences> sharedPrefProvider;

    public CoreModule_ProvideLocalPushNotificationManagerFactory(CoreModule module2, Provider<Context> contextProvider2, Provider<AirbnbPreferences> sharedPrefProvider2, Provider<AirbnbApi> airbnbApiProvider2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                if ($assertionsDisabled || sharedPrefProvider2 != null) {
                    this.sharedPrefProvider = sharedPrefProvider2;
                    if ($assertionsDisabled || airbnbApiProvider2 != null) {
                        this.airbnbApiProvider = airbnbApiProvider2;
                        if ($assertionsDisabled || accountManagerProvider2 != null) {
                            this.accountManagerProvider = accountManagerProvider2;
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

    public LocalPushNotificationManager get() {
        return (LocalPushNotificationManager) Preconditions.checkNotNull(this.module.provideLocalPushNotificationManager((Context) this.contextProvider.get(), (AirbnbPreferences) this.sharedPrefProvider.get(), (AirbnbApi) this.airbnbApiProvider.get(), (AirbnbAccountManager) this.accountManagerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<LocalPushNotificationManager> create(CoreModule module2, Provider<Context> contextProvider2, Provider<AirbnbPreferences> sharedPrefProvider2, Provider<AirbnbApi> airbnbApiProvider2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        return new CoreModule_ProvideLocalPushNotificationManagerFactory(module2, contextProvider2, sharedPrefProvider2, airbnbApiProvider2, accountManagerProvider2);
    }

    public static LocalPushNotificationManager proxyProvideLocalPushNotificationManager(CoreModule instance, Context context, AirbnbPreferences sharedPref, AirbnbApi airbnbApi, AirbnbAccountManager accountManager) {
        return instance.provideLocalPushNotificationManager(context, sharedPref, airbnbApi, accountManager);
    }
}
