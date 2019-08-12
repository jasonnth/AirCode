package com.airbnb.android.payout.create.controllers;

import com.airbnb.android.payout.logging.AddPayoutMethodJitneyLogger;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AddPayoutMethodDataController_MembersInjector implements MembersInjector<AddPayoutMethodDataController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AddPayoutMethodDataController_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AddPayoutMethodJitneyLogger> addPayoutMethodJitneyLoggerProvider;

    public AddPayoutMethodDataController_MembersInjector(Provider<AddPayoutMethodJitneyLogger> addPayoutMethodJitneyLoggerProvider2) {
        if ($assertionsDisabled || addPayoutMethodJitneyLoggerProvider2 != null) {
            this.addPayoutMethodJitneyLoggerProvider = addPayoutMethodJitneyLoggerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<AddPayoutMethodDataController> create(Provider<AddPayoutMethodJitneyLogger> addPayoutMethodJitneyLoggerProvider2) {
        return new AddPayoutMethodDataController_MembersInjector(addPayoutMethodJitneyLoggerProvider2);
    }

    public void injectMembers(AddPayoutMethodDataController instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.addPayoutMethodJitneyLogger = (AddPayoutMethodJitneyLogger) this.addPayoutMethodJitneyLoggerProvider.get();
    }

    public static void injectAddPayoutMethodJitneyLogger(AddPayoutMethodDataController instance, Provider<AddPayoutMethodJitneyLogger> addPayoutMethodJitneyLoggerProvider2) {
        instance.addPayoutMethodJitneyLogger = (AddPayoutMethodJitneyLogger) addPayoutMethodJitneyLoggerProvider2.get();
    }
}
