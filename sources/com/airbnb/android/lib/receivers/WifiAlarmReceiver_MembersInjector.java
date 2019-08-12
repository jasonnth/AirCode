package com.airbnb.android.lib.receivers;

import com.airbnb.android.core.AirbnbPreferences;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class WifiAlarmReceiver_MembersInjector implements MembersInjector<WifiAlarmReceiver> {
    static final /* synthetic */ boolean $assertionsDisabled = (!WifiAlarmReceiver_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbPreferences> preferencesProvider;

    public WifiAlarmReceiver_MembersInjector(Provider<AirbnbPreferences> preferencesProvider2) {
        if ($assertionsDisabled || preferencesProvider2 != null) {
            this.preferencesProvider = preferencesProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<WifiAlarmReceiver> create(Provider<AirbnbPreferences> preferencesProvider2) {
        return new WifiAlarmReceiver_MembersInjector(preferencesProvider2);
    }

    public void injectMembers(WifiAlarmReceiver instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.preferences = (AirbnbPreferences) this.preferencesProvider.get();
    }

    public static void injectPreferences(WifiAlarmReceiver instance, Provider<AirbnbPreferences> preferencesProvider2) {
        instance.preferences = (AirbnbPreferences) preferencesProvider2.get();
    }
}
