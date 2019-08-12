package com.airbnb.android.core;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ButtonPartnership_MembersInjector implements MembersInjector<ButtonPartnership> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ButtonPartnership_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<AirbnbPreferences> preferencesProvider;

    public ButtonPartnership_MembersInjector(Provider<AirbnbPreferences> preferencesProvider2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        if ($assertionsDisabled || preferencesProvider2 != null) {
            this.preferencesProvider = preferencesProvider2;
            if ($assertionsDisabled || accountManagerProvider2 != null) {
                this.accountManagerProvider = accountManagerProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ButtonPartnership> create(Provider<AirbnbPreferences> preferencesProvider2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        return new ButtonPartnership_MembersInjector(preferencesProvider2, accountManagerProvider2);
    }

    public void injectMembers(ButtonPartnership instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.preferences = (AirbnbPreferences) this.preferencesProvider.get();
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
    }

    public static void injectPreferences(ButtonPartnership instance, Provider<AirbnbPreferences> preferencesProvider2) {
        instance.preferences = (AirbnbPreferences) preferencesProvider2.get();
    }

    public static void injectAccountManager(ButtonPartnership instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }
}
