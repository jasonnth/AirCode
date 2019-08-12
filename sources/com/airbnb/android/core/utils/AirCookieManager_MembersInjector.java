package com.airbnb.android.core.utils;

import com.airbnb.android.core.persist.DomainStore;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AirCookieManager_MembersInjector implements MembersInjector<AirCookieManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AirCookieManager_MembersInjector.class.desiredAssertionStatus());
    private final Provider<DomainStore> domainStoreProvider;

    public AirCookieManager_MembersInjector(Provider<DomainStore> domainStoreProvider2) {
        if ($assertionsDisabled || domainStoreProvider2 != null) {
            this.domainStoreProvider = domainStoreProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<AirCookieManager> create(Provider<DomainStore> domainStoreProvider2) {
        return new AirCookieManager_MembersInjector(domainStoreProvider2);
    }

    public void injectMembers(AirCookieManager instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.domainStore = (DomainStore) this.domainStoreProvider.get();
    }

    public static void injectDomainStore(AirCookieManager instance, Provider<DomainStore> domainStoreProvider2) {
        instance.domainStore = (DomainStore) domainStoreProvider2.get();
    }
}
