package com.airbnb.android.lib.adapters;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class EditProfileDetailsAdapter_MembersInjector implements MembersInjector<EditProfileDetailsAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!EditProfileDetailsAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> mAccountManagerProvider;

    public EditProfileDetailsAdapter_MembersInjector(Provider<AirbnbAccountManager> mAccountManagerProvider2) {
        if ($assertionsDisabled || mAccountManagerProvider2 != null) {
            this.mAccountManagerProvider = mAccountManagerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<EditProfileDetailsAdapter> create(Provider<AirbnbAccountManager> mAccountManagerProvider2) {
        return new EditProfileDetailsAdapter_MembersInjector(mAccountManagerProvider2);
    }

    public void injectMembers(EditProfileDetailsAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAccountManager = (AirbnbAccountManager) this.mAccountManagerProvider.get();
    }

    public static void injectMAccountManager(EditProfileDetailsAdapter instance, Provider<AirbnbAccountManager> mAccountManagerProvider2) {
        instance.mAccountManager = (AirbnbAccountManager) mAccountManagerProvider2.get();
    }
}
