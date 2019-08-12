package com.airbnb.android.core.requests;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class SetProfilePhotoRequest_MembersInjector implements MembersInjector<SetProfilePhotoRequest> {
    static final /* synthetic */ boolean $assertionsDisabled = (!SetProfilePhotoRequest_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<Bus> busProvider;

    public SetProfilePhotoRequest_MembersInjector(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<Bus> busProvider2) {
        if ($assertionsDisabled || accountManagerProvider2 != null) {
            this.accountManagerProvider = accountManagerProvider2;
            if ($assertionsDisabled || busProvider2 != null) {
                this.busProvider = busProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<SetProfilePhotoRequest> create(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<Bus> busProvider2) {
        return new SetProfilePhotoRequest_MembersInjector(accountManagerProvider2, busProvider2);
    }

    public void injectMembers(SetProfilePhotoRequest instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
        instance.bus = (Bus) this.busProvider.get();
    }

    public static void injectAccountManager(SetProfilePhotoRequest instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }

    public static void injectBus(SetProfilePhotoRequest instance, Provider<Bus> busProvider2) {
        instance.bus = (Bus) busProvider2.get();
    }
}
