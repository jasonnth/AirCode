package com.airbnb.android.lib.receivers;

import com.airbnb.android.core.AirbnbPreferences;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class LocaleChangedReceiver_MembersInjector implements MembersInjector<LocaleChangedReceiver> {
    static final /* synthetic */ boolean $assertionsDisabled = (!LocaleChangedReceiver_MembersInjector.class.desiredAssertionStatus());
    private final Provider<Bus> busProvider;
    private final Provider<AirbnbPreferences> mPreferencesProvider;

    public LocaleChangedReceiver_MembersInjector(Provider<AirbnbPreferences> mPreferencesProvider2, Provider<Bus> busProvider2) {
        if ($assertionsDisabled || mPreferencesProvider2 != null) {
            this.mPreferencesProvider = mPreferencesProvider2;
            if ($assertionsDisabled || busProvider2 != null) {
                this.busProvider = busProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<LocaleChangedReceiver> create(Provider<AirbnbPreferences> mPreferencesProvider2, Provider<Bus> busProvider2) {
        return new LocaleChangedReceiver_MembersInjector(mPreferencesProvider2, busProvider2);
    }

    public void injectMembers(LocaleChangedReceiver instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mPreferences = (AirbnbPreferences) this.mPreferencesProvider.get();
        instance.bus = (Bus) this.busProvider.get();
    }

    public static void injectMPreferences(LocaleChangedReceiver instance, Provider<AirbnbPreferences> mPreferencesProvider2) {
        instance.mPreferences = (AirbnbPreferences) mPreferencesProvider2.get();
    }

    public static void injectBus(LocaleChangedReceiver instance, Provider<Bus> busProvider2) {
        instance.bus = (Bus) busProvider2.get();
    }
}
