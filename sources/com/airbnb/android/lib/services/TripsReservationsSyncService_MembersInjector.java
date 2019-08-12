package com.airbnb.android.lib.services;

import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class TripsReservationsSyncService_MembersInjector implements MembersInjector<TripsReservationsSyncService> {
    static final /* synthetic */ boolean $assertionsDisabled = (!TripsReservationsSyncService_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbApi> airbnbApiProvider;
    private final Provider<AirbnbAccountManager> mAccountManagerProvider;
    private final Provider<AirbnbPreferences> mPreferencesProvider;

    public TripsReservationsSyncService_MembersInjector(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<AirbnbPreferences> mPreferencesProvider2, Provider<AirbnbApi> airbnbApiProvider2) {
        if ($assertionsDisabled || mAccountManagerProvider2 != null) {
            this.mAccountManagerProvider = mAccountManagerProvider2;
            if ($assertionsDisabled || mPreferencesProvider2 != null) {
                this.mPreferencesProvider = mPreferencesProvider2;
                if ($assertionsDisabled || airbnbApiProvider2 != null) {
                    this.airbnbApiProvider = airbnbApiProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<TripsReservationsSyncService> create(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<AirbnbPreferences> mPreferencesProvider2, Provider<AirbnbApi> airbnbApiProvider2) {
        return new TripsReservationsSyncService_MembersInjector(mAccountManagerProvider2, mPreferencesProvider2, airbnbApiProvider2);
    }

    public void injectMembers(TripsReservationsSyncService instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAccountManager = (AirbnbAccountManager) this.mAccountManagerProvider.get();
        instance.mPreferences = (AirbnbPreferences) this.mPreferencesProvider.get();
        instance.airbnbApi = (AirbnbApi) this.airbnbApiProvider.get();
    }

    public static void injectMAccountManager(TripsReservationsSyncService instance, Provider<AirbnbAccountManager> mAccountManagerProvider2) {
        instance.mAccountManager = (AirbnbAccountManager) mAccountManagerProvider2.get();
    }

    public static void injectMPreferences(TripsReservationsSyncService instance, Provider<AirbnbPreferences> mPreferencesProvider2) {
        instance.mPreferences = (AirbnbPreferences) mPreferencesProvider2.get();
    }

    public static void injectAirbnbApi(TripsReservationsSyncService instance, Provider<AirbnbApi> airbnbApiProvider2) {
        instance.airbnbApi = (AirbnbApi) airbnbApiProvider2.get();
    }
}
