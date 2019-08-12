package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class CohostingInviteFriendEpoxyController_MembersInjector implements MembersInjector<CohostingInviteFriendEpoxyController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CohostingInviteFriendEpoxyController_MembersInjector.class.desiredAssertionStatus());
    private final Provider<CohostingManagementJitneyLogger> loggerProvider;

    public CohostingInviteFriendEpoxyController_MembersInjector(Provider<CohostingManagementJitneyLogger> loggerProvider2) {
        if ($assertionsDisabled || loggerProvider2 != null) {
            this.loggerProvider = loggerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<CohostingInviteFriendEpoxyController> create(Provider<CohostingManagementJitneyLogger> loggerProvider2) {
        return new CohostingInviteFriendEpoxyController_MembersInjector(loggerProvider2);
    }

    public void injectMembers(CohostingInviteFriendEpoxyController instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.logger = (CohostingManagementJitneyLogger) this.loggerProvider.get();
    }

    public static void injectLogger(CohostingInviteFriendEpoxyController instance, Provider<CohostingManagementJitneyLogger> loggerProvider2) {
        instance.logger = (CohostingManagementJitneyLogger) loggerProvider2.get();
    }
}
