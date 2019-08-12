package com.airbnb.android.sharing.referral;

import com.airbnb.android.core.AirbnbPreferences;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class SharingManager_MembersInjector implements MembersInjector<SharingManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!SharingManager_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbPreferences> preferencesProvider;

    public SharingManager_MembersInjector(Provider<AirbnbPreferences> preferencesProvider2) {
        if ($assertionsDisabled || preferencesProvider2 != null) {
            this.preferencesProvider = preferencesProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<SharingManager> create(Provider<AirbnbPreferences> preferencesProvider2) {
        return new SharingManager_MembersInjector(preferencesProvider2);
    }

    public void injectMembers(SharingManager instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.preferences = (AirbnbPreferences) this.preferencesProvider.get();
    }

    public static void injectPreferences(SharingManager instance, Provider<AirbnbPreferences> preferencesProvider2) {
        instance.preferences = (AirbnbPreferences) preferencesProvider2.get();
    }
}
