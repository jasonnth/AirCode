package com.airbnb.android.lib.fragments.verifiedid;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class VerifiedIdCompletedFragment_MembersInjector implements MembersInjector<VerifiedIdCompletedFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = (!VerifiedIdCompletedFragment_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> mAccountManagerProvider;
    private final Provider<Bus> mBusProvider;

    public VerifiedIdCompletedFragment_MembersInjector(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<Bus> mBusProvider2) {
        if ($assertionsDisabled || mAccountManagerProvider2 != null) {
            this.mAccountManagerProvider = mAccountManagerProvider2;
            if ($assertionsDisabled || mBusProvider2 != null) {
                this.mBusProvider = mBusProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<VerifiedIdCompletedFragment> create(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<Bus> mBusProvider2) {
        return new VerifiedIdCompletedFragment_MembersInjector(mAccountManagerProvider2, mBusProvider2);
    }

    public void injectMembers(VerifiedIdCompletedFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAccountManager = (AirbnbAccountManager) this.mAccountManagerProvider.get();
        instance.mBus = (Bus) this.mBusProvider.get();
    }

    public static void injectMAccountManager(VerifiedIdCompletedFragment instance, Provider<AirbnbAccountManager> mAccountManagerProvider2) {
        instance.mAccountManager = (AirbnbAccountManager) mAccountManagerProvider2.get();
    }

    public static void injectMBus(VerifiedIdCompletedFragment instance, Provider<Bus> mBusProvider2) {
        instance.mBus = (Bus) mBusProvider2.get();
    }
}
