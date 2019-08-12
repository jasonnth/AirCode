package com.airbnb.android.core.modules;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.squareup.otto.Bus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideBusinessTravelAccountManagerFactory implements Factory<BusinessTravelAccountManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideBusinessTravelAccountManagerFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> airbnbAccountManagerProvider;
    private final Provider<Bus> busProvider;

    public CoreModule_ProvideBusinessTravelAccountManagerFactory(Provider<AirbnbAccountManager> airbnbAccountManagerProvider2, Provider<Bus> busProvider2) {
        if ($assertionsDisabled || airbnbAccountManagerProvider2 != null) {
            this.airbnbAccountManagerProvider = airbnbAccountManagerProvider2;
            if ($assertionsDisabled || busProvider2 != null) {
                this.busProvider = busProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public BusinessTravelAccountManager get() {
        return (BusinessTravelAccountManager) Preconditions.checkNotNull(CoreModule.provideBusinessTravelAccountManager((AirbnbAccountManager) this.airbnbAccountManagerProvider.get(), (Bus) this.busProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<BusinessTravelAccountManager> create(Provider<AirbnbAccountManager> airbnbAccountManagerProvider2, Provider<Bus> busProvider2) {
        return new CoreModule_ProvideBusinessTravelAccountManagerFactory(airbnbAccountManagerProvider2, busProvider2);
    }

    public static BusinessTravelAccountManager proxyProvideBusinessTravelAccountManager(AirbnbAccountManager airbnbAccountManager, Bus bus) {
        return CoreModule.provideBusinessTravelAccountManager(airbnbAccountManager, bus);
    }
}
