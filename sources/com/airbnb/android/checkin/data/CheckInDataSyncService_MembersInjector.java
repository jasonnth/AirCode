package com.airbnb.android.checkin.data;

import com.airbnb.android.checkin.GuestCheckInJitneyLogger;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class CheckInDataSyncService_MembersInjector implements MembersInjector<CheckInDataSyncService> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CheckInDataSyncService_MembersInjector.class.desiredAssertionStatus());
    private final Provider<CheckInDataTableOpenHelper> dbHelperProvider;
    private final Provider<GuestCheckInJitneyLogger> jitneyLoggerProvider;
    private final Provider<AirbnbAccountManager> mAccountManagerProvider;

    public CheckInDataSyncService_MembersInjector(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<CheckInDataTableOpenHelper> dbHelperProvider2, Provider<GuestCheckInJitneyLogger> jitneyLoggerProvider2) {
        if ($assertionsDisabled || mAccountManagerProvider2 != null) {
            this.mAccountManagerProvider = mAccountManagerProvider2;
            if ($assertionsDisabled || dbHelperProvider2 != null) {
                this.dbHelperProvider = dbHelperProvider2;
                if ($assertionsDisabled || jitneyLoggerProvider2 != null) {
                    this.jitneyLoggerProvider = jitneyLoggerProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<CheckInDataSyncService> create(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<CheckInDataTableOpenHelper> dbHelperProvider2, Provider<GuestCheckInJitneyLogger> jitneyLoggerProvider2) {
        return new CheckInDataSyncService_MembersInjector(mAccountManagerProvider2, dbHelperProvider2, jitneyLoggerProvider2);
    }

    public void injectMembers(CheckInDataSyncService instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAccountManager = (AirbnbAccountManager) this.mAccountManagerProvider.get();
        instance.dbHelper = (CheckInDataTableOpenHelper) this.dbHelperProvider.get();
        instance.jitneyLogger = (GuestCheckInJitneyLogger) this.jitneyLoggerProvider.get();
    }

    public static void injectMAccountManager(CheckInDataSyncService instance, Provider<AirbnbAccountManager> mAccountManagerProvider2) {
        instance.mAccountManager = (AirbnbAccountManager) mAccountManagerProvider2.get();
    }

    public static void injectDbHelper(CheckInDataSyncService instance, Provider<CheckInDataTableOpenHelper> dbHelperProvider2) {
        instance.dbHelper = (CheckInDataTableOpenHelper) dbHelperProvider2.get();
    }

    public static void injectJitneyLogger(CheckInDataSyncService instance, Provider<GuestCheckInJitneyLogger> jitneyLoggerProvider2) {
        instance.jitneyLogger = (GuestCheckInJitneyLogger) jitneyLoggerProvider2.get();
    }
}
