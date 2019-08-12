package com.airbnb.android.core.services;

import com.airbnb.android.core.AirbnbPreferences;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class PushNotificationBuilder_MembersInjector implements MembersInjector<PushNotificationBuilder> {
    static final /* synthetic */ boolean $assertionsDisabled = (!PushNotificationBuilder_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbPreferences> preferencesProvider;

    public PushNotificationBuilder_MembersInjector(Provider<AirbnbPreferences> preferencesProvider2) {
        if ($assertionsDisabled || preferencesProvider2 != null) {
            this.preferencesProvider = preferencesProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<PushNotificationBuilder> create(Provider<AirbnbPreferences> preferencesProvider2) {
        return new PushNotificationBuilder_MembersInjector(preferencesProvider2);
    }

    public void injectMembers(PushNotificationBuilder instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.preferences = (AirbnbPreferences) this.preferencesProvider.get();
    }

    public static void injectPreferences(PushNotificationBuilder instance, Provider<AirbnbPreferences> preferencesProvider2) {
        instance.preferences = (AirbnbPreferences) preferencesProvider2.get();
    }
}
