package com.airbnb.android.cohosting.adapters;

import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ListingManagerDetailsAdapter_MembersInjector implements MembersInjector<ListingManagerDetailsAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ListingManagerDetailsAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<CohostingManagementJitneyLogger> loggerProvider;

    public ListingManagerDetailsAdapter_MembersInjector(Provider<CohostingManagementJitneyLogger> loggerProvider2) {
        if ($assertionsDisabled || loggerProvider2 != null) {
            this.loggerProvider = loggerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<ListingManagerDetailsAdapter> create(Provider<CohostingManagementJitneyLogger> loggerProvider2) {
        return new ListingManagerDetailsAdapter_MembersInjector(loggerProvider2);
    }

    public void injectMembers(ListingManagerDetailsAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.logger = (CohostingManagementJitneyLogger) this.loggerProvider.get();
    }

    public static void injectLogger(ListingManagerDetailsAdapter instance, Provider<CohostingManagementJitneyLogger> loggerProvider2) {
        instance.logger = (CohostingManagementJitneyLogger) loggerProvider2.get();
    }
}
