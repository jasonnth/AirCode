package com.airbnb.android.lib.china5a.photo;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class PhotoVerificationPresenter_MembersInjector implements MembersInjector<PhotoVerificationPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!PhotoVerificationPresenter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;

    public PhotoVerificationPresenter_MembersInjector(Provider<AirbnbAccountManager> accountManagerProvider2) {
        if ($assertionsDisabled || accountManagerProvider2 != null) {
            this.accountManagerProvider = accountManagerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<PhotoVerificationPresenter> create(Provider<AirbnbAccountManager> accountManagerProvider2) {
        return new PhotoVerificationPresenter_MembersInjector(accountManagerProvider2);
    }

    public void injectMembers(PhotoVerificationPresenter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
    }

    public static void injectAccountManager(PhotoVerificationPresenter instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }
}
