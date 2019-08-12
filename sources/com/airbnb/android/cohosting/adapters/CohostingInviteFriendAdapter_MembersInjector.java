package com.airbnb.android.cohosting.adapters;

import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class CohostingInviteFriendAdapter_MembersInjector implements MembersInjector<CohostingInviteFriendAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CohostingInviteFriendAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<CohostingManagementJitneyLogger> loggerProvider;

    public CohostingInviteFriendAdapter_MembersInjector(Provider<CohostingManagementJitneyLogger> loggerProvider2) {
        if ($assertionsDisabled || loggerProvider2 != null) {
            this.loggerProvider = loggerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<CohostingInviteFriendAdapter> create(Provider<CohostingManagementJitneyLogger> loggerProvider2) {
        return new CohostingInviteFriendAdapter_MembersInjector(loggerProvider2);
    }

    public void injectMembers(CohostingInviteFriendAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.logger = (CohostingManagementJitneyLogger) this.loggerProvider.get();
    }

    public static void injectLogger(CohostingInviteFriendAdapter instance, Provider<CohostingManagementJitneyLogger> loggerProvider2) {
        instance.logger = (CohostingManagementJitneyLogger) loggerProvider2.get();
    }
}
